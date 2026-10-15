package algoritmos.busca;

import java.util.*;

public class TabelaHashEncadeada {
	private final ArrayList<LinkedList<Integer>> tabela;
	private final int m; // tamanho da tabela (primo)

	public TabelaHashEncadeada(int m) {
		this.m = m;
		this.tabela = new ArrayList<>(m);
		for (int i = 0; i < m; i++) {
			tabela.add(new LinkedList<>()); // lista vazia
		}
	}

	private int hash(int chave) {
		return Math.floorMod(chave, m); // nunca negativo
	}

	public void inserir(int chave) {
		LinkedList<Integer> lista = tabela.get(hash(chave));
		if (!lista.contains(chave)) { // evita duplicata
			lista.add(chave);
		}
	}

	public boolean buscar(int chave) {
		return tabela.get(hash(chave)).contains(chave);
	}

	public boolean remover(int chave) {
		// remove(Object), NÃO remove(int índice)!
		return tabela.get(hash(chave)).remove(Integer.valueOf(chave));
	}
	
	
}
