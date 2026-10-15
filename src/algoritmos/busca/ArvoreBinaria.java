package algoritmos.busca;
public class ArvoreBinaria {
	private No raiz;
	private int nElem;

	public ArvoreBinaria() {
		this.nElem = 0;
		this.raiz = null;
	}

	public boolean pesquisa(int elem) {
		// Se retornar diferente de null, é porque achou
		return pesquisa(elem, this.raiz) != null;
	}

	private No pesquisa(int elem, No no) {
		if (no == null) {
			return null;
		}

		if (elem < no.getInfo()) {
			return pesquisa(elem, no.getEsq());
		} else if (elem > no.getInfo()) {
			return pesquisa(elem, no.getDir());
		}

		// Se não é menor nem maior, é igual. Achamos o nó
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

	public No insere(int elem, No no) {
		if (no == null) {
			return new No(elem);
		}

		if (elem < no.getInfo()) {
			no.setEsq(insere(elem, no.getEsq()));
		} else {
			no.setDir(insere(elem, no.getDir()));
		}

		return no;
	}

	public boolean remove(int chave) {
		if (pesquisa(chave, this.raiz) != null) {
			this.raiz = remove(chave, this.raiz);
			this.nElem--;
			return true;
		}
		return false;
	}

	private No remove(int chave, No no) {
		if (no == null) {
			return null;
		}

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
				// Nó tem dois filhos, busca o maior nó da subárvore esquerda
				no.setEsq(arruma(no, no.getEsq()));
			}
		}
		return no;
	}

	private No arruma(No Q, No R) {
		if (R.getDir() != null) {
			R.setDir(arruma(Q, R.getDir()));
		} else {
			Q.setInfo(R.getInfo());
			R = R.getEsq();
		}
		return R;
	}

	

	public int[] CamCentral() {
		// Cria o vetor com o tamanho exato da quantidade de elementos na árvore
		int[] vetOrdenado = new int[this.nElem];
		// O array de tamanho 1 serve apenas para manter a referência do índice
		// durante as chamadas recursivas.
		int[] indice = { 0 };
		this.FazCamCentral(this.raiz, vetOrdenado, indice);
		return vetOrdenado;
	}

	private void FazCamCentral(No arv, int[] vetOrdenado, int[] indice) {
		if (arv != null) {
			this.FazCamCentral(arv.getEsq(), vetOrdenado, indice);

			// Insere o elemento na posição atual e incrementa o índice
			vetOrdenado[indice[0]] = arv.getInfo();
			indice[0]++;

			this.FazCamCentral(arv.getDir(), vetOrdenado, indice);
		}
	}

	public ArvoreBinaria ArvoreBalanceada() {
		// Primeiro, pega todos os elementos ordenados
		int[] vetOrdenado = this.CamCentral();

		ArvoreBinaria temp = new ArvoreBinaria();

		// Agora usamos o tamanho do vetor (.length) para saber o fim
		this.Balanceamento(vetOrdenado, temp, 0, vetOrdenado.length - 1);

		return temp;
	}

	private void Balanceamento(int[] vetOrdenado, ArvoreBinaria temp, int ini, int fim) {
		if (fim >= ini) {
			int meio = (ini + fim) / 2;
			temp.insere(vetOrdenado[meio]);
			this.Balanceamento(vetOrdenado, temp, ini, meio - 1);
			this.Balanceamento(vetOrdenado, temp, meio + 1, fim);
		}
	}



	static int maior(int v[]) {
		int maior = 0;
		for (int i = 1; i < v.length; i++) {
			if (v[i] > v[maior]) {
				maior = i;
			}
		}
		return maior;
	}

	static int menor(int v[]) {
		int menor = 0;
		for (int i = 1; i < v.length; i++) {
			if (v[i] < v[menor]) {
				menor = i;
			}
		}
		return menor;
	}
}