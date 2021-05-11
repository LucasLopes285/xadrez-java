package xadrez;

import camadatabuleiro.Peca;
import camadatabuleiro.Posicao;
import camadatabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaDeXadrez {

	private Tabuleiro tabuleiro;

	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		iniciarPartida();
	}

	public PecaDeXadrez[][] getPecas() {
		PecaDeXadrez[][] mat = new PecaDeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				mat[i][j] = (PecaDeXadrez) tabuleiro.peca(i, j);
			}
		}
		return mat;
	}
	
	public PecaDeXadrez movimentarPeca(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.posicionar();
		Posicao destino = posicaoDestino.posicionar();
		validarPosicaoOrigem(origem);
		Peca pecaCapturada = mover(origem, destino);
		return (PecaDeXadrez)pecaCapturada;
	}
	
	private Peca mover(Posicao origem, Posicao destino) {
		Peca p = tabuleiro.removerPeca(origem);
		Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.colocarPeca(p, destino);
		return pecaCapturada;
	}
	
	private void validarPosicaoOrigem(Posicao posicao) {
		if(!tabuleiro.existeUmaPeca(posicao)) {
			throw new ExcecaoXadrez("Não existe peça na posição de origem");
		}
		
	}

	private void coloqueUmaNovaPeca(char coluna, int linha, PecaDeXadrez peca) {
		tabuleiro.colocarPeca(peca, new PosicaoXadrez(coluna, linha).posicionar());
	}

	public void iniciarPartida() {
		coloqueUmaNovaPeca('c', 1, new Torre(tabuleiro, Cor.WHITE));
		coloqueUmaNovaPeca('c', 2, new Torre(tabuleiro, Cor.WHITE));
		coloqueUmaNovaPeca('d', 2, new Torre(tabuleiro, Cor.WHITE));
		coloqueUmaNovaPeca('e', 2, new Torre(tabuleiro, Cor.WHITE));
		coloqueUmaNovaPeca('e', 1, new Torre(tabuleiro, Cor.WHITE));
		coloqueUmaNovaPeca('d', 1, new Rei(tabuleiro, Cor.WHITE));


		coloqueUmaNovaPeca('c', 7, new Torre(tabuleiro, Cor.BLACK));
		coloqueUmaNovaPeca('c', 8, new Torre(tabuleiro, Cor.BLACK));
		coloqueUmaNovaPeca('d', 7, new Torre(tabuleiro, Cor.BLACK));
		coloqueUmaNovaPeca('e', 7, new Torre(tabuleiro, Cor.BLACK));
		coloqueUmaNovaPeca('e', 8, new Torre(tabuleiro, Cor.BLACK));
		coloqueUmaNovaPeca('d', 8, new Rei(tabuleiro, Cor.BLACK));
	}
}
