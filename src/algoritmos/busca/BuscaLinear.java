package unidade3;

import java.util.Scanner;

public class BuscaLinear {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int[] vetor = new int[8];

		/* preencher o vetor */
		boolean achou = false;
		for (int i = 0; i < vetor.length; i++) {
			vetor[i] = (int) (Math.random() * 10);
			System.out.println(vetor[i]);
		}
		// busca linear - O(n)
		System.out.println("Escreve o número que deseja buscar:");
		int n = scan.nextInt();
		for (int i = 0; i < vetor.length; i++) {
			if (vetor[i] == n) {
				achou = true;
				break;
			}
		}

		if (achou) {
			System.out.println("Achou!");
		} else {
			System.out.println("Não achou");
		}

	}

	// vetores desordenados
	public static boolean pesquisaSeq(int x, int numero[]) {
		for (int i = 0; i < numero.length; i++) {
			if (x == numero[i]) {
				return true;
			}
		}
		return false;

	}

	// funciona para vetores ordenados
	public static boolean pesquisaSeqMelhor(int x, int numeros[]) {
		for (int i = 0; i < numeros.length && x <= numeros[i]; i++) {
			if (x == numeros[i]) {
				return true;
			}
		}
		return false;
	}

}
