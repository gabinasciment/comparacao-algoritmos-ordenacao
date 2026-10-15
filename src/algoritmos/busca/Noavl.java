package algoritmos.busca;

public class Noavl {
	private int info;
	private Noavl esq;
	private Noavl dir;
	private int altura;

	public Noavl(int info) {
		this.info = info;
		this.esq = null;
		this.dir = null;
		this.altura = 0; //
	}

	public int getInfo() {
		return info;
	}

	public void setInfo(int info) {
		this.info = info;
	}

	public Noavl getEsq() {
		return esq;
	}

	public void setEsq(Noavl esq) {
		this.esq = esq;
	}

	public Noavl getDir() {
		return dir;
	}

	public void setDir(Noavl dir) {
		this.dir = dir;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}
}