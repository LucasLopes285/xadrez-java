package xadrez;

import camadatabuleiro.Peca;
import camadatabuleiro.Tabuleiro;

public abstract class PecaDeXadrez extends Peca {
	
	private Cor cor;

	public PecaDeXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}
	
	public Cor getCor() {
		return cor;
	}

}
