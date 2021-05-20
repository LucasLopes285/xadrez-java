package camadatabuleiro;

public abstract class Peca {

	protected Posicao posicao;
	private Tabuleiro tabuleiro;

	public Peca(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		posicao = null;
	}

	protected Tabuleiro getTabuleiro() {
		return this.tabuleiro;
	}

	public abstract boolean[][] movimentos();

	public boolean movimento(Posicao posicao) {
		return movimentos()[posicao.getLinha()][posicao.getColuna()];
	}

	public boolean existeMovimentoPossivel() {
		boolean[][] mat = movimentos();
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				if (mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}

}
