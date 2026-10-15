package algoritmos.busca;

public class ArvoreAvl {
	private Noavl raiz;
	private int nElem;

	public ArvoreAvl() {
		this.nElem = 0;
		this.raiz = null;
	}



	private int altura(Noavl no) {
		return no == null ? -1 : no.getAltura();
	}

	private void atualizarAltura(Noavl no) {
		int altEsq = altura(no.getEsq());
		int altDir = altura(no.getDir());
		no.setAltura(Math.max(altEsq, altDir) + 1);
	}

	private int fatorBalanceamento(Noavl no) {
		return no == null ? 0 : altura(no.getDir()) - altura(no.getEsq());
	}

	private Noavl rotacionarDireita(Noavl no) {
		Noavl esq = no.getEsq();
		no.setEsq(esq.getDir());
		esq.setDir(no);

		atualizarAltura(no);
		atualizarAltura(esq);

		return esq;
	}

	private Noavl rotacionarEsquerda(Noavl no) {
		Noavl dir = no.getDir();
		no.setDir(dir.getEsq());
		dir.setEsq(no);

		atualizarAltura(no);
		atualizarAltura(dir);

		return dir;
	}

	private Noavl rebalancear(Noavl no) {
		atualizarAltura(no);
		int fator = fatorBalanceamento(no);

		// Árvore pesada para a esquerda
		if (fator < -1) {
			if (fatorBalanceamento(no.getEsq()) <= 0) {
				// Rotação simples à direita
				no = rotacionarDireita(no);
			} else {
				// Rotação dupla (Esquerda-Direita)
				no.setEsq(rotacionarEsquerda(no.getEsq()));
				no = rotacionarDireita(no);
			}
		}
		// Árvore pesada para a direita
		else if (fator > 1) {
			if (fatorBalanceamento(no.getDir()) >= 0) {
				// Rotação simples à esquerda
				no = rotacionarEsquerda(no);
			} else {
				// Rotação dupla (Direita-Esquerda)
				no.setDir(rotacionarDireita(no.getDir()));
				no = rotacionarEsquerda(no);
			}
		}

		return no;
	}



	public boolean pesquisa(int elem) {
		return pesquisa(elem, this.raiz) != null;
	}

	private Noavl pesquisa(int elem, Noavl no) {
		if (no == null)
			return null;

		if (elem < no.getInfo()) {
			return pesquisa(elem, no.getEsq());
		} else if (elem > no.getInfo()) {
			return pesquisa(elem, no.getDir());
		}

		return no;
	}



	public boolean insere(int elem) {
		if (pesquisa(elem)) {
			return false; // Elemento já existe
		}
		this.raiz = insere(elem, this.raiz);
		this.nElem++;
		return true;
	}

	private Noavl insere(int elem, Noavl no) {
		if (no == null) {
			return new Noavl(elem);
		}

		if (elem < no.getInfo()) {
			no.setEsq(insere(elem, no.getEsq()));
		} else {
			no.setDir(insere(elem, no.getDir()));
		}

		// Na volta da recursão, rebalanceia o nó
		return rebalancear(no);
	}




	public boolean remove(int chave) {
		if (!pesquisa(chave)) {
			return false;
		}
		this.raiz = remove(chave, this.raiz);
		this.nElem--;
		return true;
	}

	private Noavl remove(int chave, Noavl no) {
		if (no == null)
			return null;

		if (chave < no.getInfo()) {
			no.setEsq(remove(chave, no.getEsq()));
		} else if (chave > no.getInfo()) {
			no.setDir(remove(chave, no.getDir()));
		} else {
			// Encontrou o nó a ser removido
			if (no.getDir() == null) {
				return no.getEsq();
			} else if (no.getEsq() == null) {
				return no.getDir();
			} else {
				// Tem dois filhos: substitui pelo maior da subárvore esquerda
				Noavl maiorEsq = buscaMaior(no.getEsq());
				no.setInfo(maiorEsq.getInfo());
				// Remove o nó que acabamos de copiar
				no.setEsq(remove(maiorEsq.getInfo(), no.getEsq()));
			}
		}

		// Na volta da recursão, rebalanceia o nó
		return rebalancear(no);
	}

	private Noavl buscaMaior(Noavl no) {
		while (no.getDir() != null) {
			no = no.getDir();
		}
		return no;
	}

	

	public int[] CamCentral() {
		int[] vetOrdenado = new int[this.nElem];
		int[] indice = { 0 };
		this.FazCamCentral(this.raiz, vetOrdenado, indice);
		return vetOrdenado;
	}

	private void FazCamCentral(Noavl arv, int[] vetOrdenado, int[] indice) {
		if (arv != null) {
			this.FazCamCentral(arv.getEsq(), vetOrdenado, indice);
			vetOrdenado[indice[0]] = arv.getInfo();
			indice[0]++;
			this.FazCamCentral(arv.getDir(), vetOrdenado, indice);
		}
	}

	public int getnElem() {
		return nElem;
	}
}