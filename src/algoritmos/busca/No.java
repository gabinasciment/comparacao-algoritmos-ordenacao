package unidade3;
public class No {
	private int info;
	private No esq, dir;

	public No(int _info) {
		this.info = _info;
	}

	public No getEsq() {
		return this.esq;
	}

	public No getDir() {
		return this.dir;
	}

	public int getInfo() {
		return this.info;
	}

	public void setEsq(No novo) {
		this.esq = novo;
	}

	public void setDir(No novo) {
		this.dir = novo;
	}

	public void setInfo(int novo) {
		this.info = novo;
	}
}
