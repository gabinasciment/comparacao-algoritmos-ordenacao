package algoritmos.busca;

/**
 * Implementação do Algoritmo de Pesquisa Binária:
 * O vetor DEVE estar ordenado de forma crescente.
 * O algoritmo adota a estratégia de divisão e conquista: divide o espaço de busca
 * ao meio a cada iteração, reduzindo a complexidade de tempo para O(log n).
 */
public class PesquisaBinaria {

    /**
     * Realiza a pesquisa binária no vetor ordenado.
     *
     * @param vetorOrdenado vetor previamente ordenado
     * @param chave elemento procurado
     * @return ResultadoBusca contendo posição, indicador booleano, comparações e tempo em ms
     */
    public static ResultadoBusca pesquisar(int[] vetorOrdenado, int chave) {
        long comparacoes = 0;
        long inicioTempo = System.nanoTime();

        int inicio = 0;
        int fim = vetorOrdenado.length - 1;
        int posicaoEncontrada = -1;
        boolean achou = false;

        while (inicio <= fim) {
            int meio = inicio + (fim - inicio) / 2; // Evita overflow para arrays gigantes
            
            comparacoes++;
            if (vetorOrdenado[meio] == chave) {
                posicaoEncontrada = meio;
                achou = true;
                break;
            } else {
                comparacoes++;
                if (vetorOrdenado[meio] < chave) {
                    inicio = meio + 1; // Busca na metade superior
                } else {
                    fim = meio - 1;    // Busca na metade inferior
                }
            }
        }

        long fimTempo = System.nanoTime();
        double tempoMs = (fimTempo - inicioTempo) / 1_000_000.0;
        return new ResultadoBusca(posicaoEncontrada, achou, comparacoes, tempoMs, "Pesquisa Binária");
    }
}
