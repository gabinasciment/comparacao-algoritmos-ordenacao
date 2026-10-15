package algoritmos.busca;

import algoritmos.FileHandler;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

/**
 * Classe responsável pelo Gerenciamento do Vetor em memória (Parte 1 do Edital):
 * - Criar Vetor com tamanho customizado (números aleatórios ou crescentes).
 * - Carregar Vetor a partir de arquivo texto (.txt), com tratamento de exceções.
 */
public class GerenciadorVetor {
    private int[] vetorAtual;

    public GerenciadorVetor() {
        this.vetorAtual = null;
    }

    public boolean temVetor() {
        return vetorAtual != null && vetorAtual.length > 0;
    }

    public int[] getVetorAtual() {
        return vetorAtual;
    }

    public void setVetorAtual(int[] vetor) {
        this.vetorAtual = vetor;
    }

    /**
     * Cria e preenche um vetor com o tamanho especificado.
     *
     * @param tamanho quantidade de elementos (ex: 10, 100, 10000)
     * @param crescente true para sequência crescente (1 a tamanho); false para aleatórios
     * @return novo vetor preenchido
     */
    public int[] criarVetor(int tamanho, boolean crescente) {
        if (tamanho <= 0) {
            throw new IllegalArgumentException("O tamanho do vetor deve ser maior que zero.");
        }

        int[] novoVetor = new int[tamanho];
        if (crescente) {
            for (int i = 0; i < tamanho; i++) {
                novoVetor[i] = i + 1;
            }
        } else {
            Random rand = new Random();
            for (int i = 0; i < tamanho; i++) {
                novoVetor[i] = rand.nextInt(tamanho * 5); // Distribuição aleatória de valores
            }
        }

        this.vetorAtual = novoVetor;
        return novoVetor;
    }

    /**
     * Carrega números de um arquivo .txt para o vetor com tratamento de erros.
     *
     * @param caminho caminho do arquivo (ex: numeros_100_aleatorio.txt)
     * @return vetor preenchido a partir do arquivo
     * @throws FileNotFoundException se o arquivo não for encontrado no disco
     * @throws IOException se houver erro durante a leitura
     */
    public int[] carregarArquivo(String caminho) throws FileNotFoundException, IOException {
        try {
            int[] carregado = FileHandler.lerArquivo(caminho);
            if (carregado.length == 0) {
                throw new IOException("O arquivo informado está vazio.");
            }
            this.vetorAtual = carregado;
            return carregado;
        } catch (FileNotFoundException e) {
            throw e; // Repassa especificamente FileNotFoundException para tratamento adequado
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * Exibe uma prévia dos primeiros e últimos elementos do vetor atual.
     */
    public void exibirPrevia() {
        if (!temVetor()) {
            System.out.println("Nenhum vetor carregado na memória no momento.");
            return;
        }

        int n = vetorAtual.length;
        System.out.printf("Vetor atual em memória: %d elementos.\n", n);
        System.out.print("Prévia: [ ");
        int limite = Math.min(n, 10);
        for (int i = 0; i < limite; i++) {
            System.out.print(vetorAtual[i] + (i < limite - 1 ? ", " : ""));
        }
        if (n > 10) {
            System.out.print(" ... (" + (n - 10) + " elementos restantes)");
        }
        System.out.println(" ]");
    }
}
