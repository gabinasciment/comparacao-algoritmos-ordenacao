package unidade3;

import java.util.Scanner;

public class BuscaBinaria {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int[] vetor = new int[8];
		// o vetor precisa estar ordenado
		for (int i = 0; i < vetor.length; i++) {
			vetor[i] = i * 2;
			System.out.println(vetor[i]);
		}

		System.out.println("Qual número vc deseja buscar ? ");
		int n = scan.nextInt();
		// divide o vetor ao meio, elimina metade
		// pior caso - número não existe, e se estive no meio ou no final
		// O(log n)
		boolean achou = false;
		int inicio = 0;
		int fim = vetor.length - 1;
		int meio;
		while (inicio <= fim) {
			meio = (int) ((inicio + fim) / 2);
			if (vetor[meio] == n) {
				achou = true;
				break;
			} else if (vetor[meio] < n) {
				inicio = meio + 1;

			} else {
				fim = meio - 1;
			}
		}

		if (achou) {
			System.out.println("Achou");
		} else {
			System.out.println("Não achou");
		}
	}

	public static boolean pesquisaBinaria(int x, int numeros[]) {
		int inicio = 0;
		int fim = numeros.length - 1;
		int meio;
		while (inicio <= fim) {
			meio = (int) ((inicio + fim) / 2);
			if (numeros[meio] == x) {
				return true;
			} else if (numeros[meio] < x) {
				inicio = meio + 1;

			} else {
				fim = meio - 1;
			}
		}
		return false;

	}

}
