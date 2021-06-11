package aplicacao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.ExcecaoXadrez;
import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoXadrez;

public class Programa {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partida = new PartidaDeXadrez();
		List<PecaDeXadrez> capturada = new ArrayList<>();

		while (true) {
			try {	
				UI.limparTela();
				UI.imprimirPartida(partida, capturada);
				System.out.println();
				System.out.print("Origem: ");
				PosicaoXadrez origem = UI.lerPosicao(sc);

				boolean[][] possiveisMovimentos = partida.possiveisMovimentos(origem);
				UI.limparTela();
				UI.imprimirTabuleiro(partida.getPecas(), possiveisMovimentos);

				System.out.println();
				System.out.print("Destino: ");
				PosicaoXadrez destino = UI.lerPosicao(sc);

				PecaDeXadrez pecaCapturada = partida.movimentarPeca(origem, destino);

				if(pecaCapturada != null) {
					capturada.add(pecaCapturada);
				}
			}
			catch(ExcecaoXadrez e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}

		}
	}

}
