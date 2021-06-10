package xadrez;

import camadatabuleiro.Peca;
import camadatabuleiro.Posicao;
import camadatabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaDeXadrez {

	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;

	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual = Cor.WHITE;
		iniciarPartida();
	}

	public int getTurno() {
		return this.turno;
	}

	public Cor getJogadorAtual() {
		return this.jogadorAtual;
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

	public boolean[][] possiveisMovimentos(PosicaoXadrez origem){
		Posicao posicao = origem.posicionar();
		validarPosicaoOrigem(posicao);
		return tabuleiro.peca(posicao).movimentos();
	}

	public PecaDeXadrez movimentarPeca(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.posicionar();
		Posicao destino = posicaoDestino.posicionar();
		validarPosicaoOrigem(origem);
		validarPosicaoDestino(origem, destino);
		Peca pecaCapturada = mover(origem, destino);
		proximoTurno();
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
		
		if(jogadorAtual != ((PecaDeXadrez)tabuleiro.peca(posicao)).getCor()) {
			throw new ExcecaoXadrez("A peça escolhida não é sua");
		}

		if(!tabuleiro.peca(posicao).existeMovimentoPossivel()) {
			throw new ExcecaoXadrez("Não existe movimento possível para a peça escolhida");

		}

	}

	private void validarPosicaoDestino(Posicao origem, Posicao destino) {
		if(!tabuleiro.peca(origem).movimento(destino)) {
			throw new ExcecaoXadrez("A peça escolhida não pode se mover para a posição de destino");
		}
	}

	private void proximoTurno() {
		this.turno++;
        jogadorAtual = (jogadorAtual == Cor.WHITE)? Cor.BLACK : Cor.WHITE;

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
