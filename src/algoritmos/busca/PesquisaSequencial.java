package algoritmos.busca;

/**
 * Implementação dos Algoritmos de Pesquisa Sequencial:
 * 1. Pesquisa Sequencial Simples (em vetor qualquer):
 *    Percorre elemento a elemento até encontrar ou percorrer todo o vetor.
 * 2. Pesquisa Sequencial Otimizada (em vetor previamente ordenado):
 *    Interrompe a busca imediatamente caso o elemento corrente seja estritamente
 *    maior que o elemento buscado, evitando iterações desnecessárias.
 */
public class PesquisaSequencial {

    /**
     * Realiza a pesquisa sequencial simples em um vetor qualquer.
     *
     * @param vetor vetor onde o valor será buscado
     * @param chave valor inteiro a ser procurado
     * @return ResultadoBusca contendo posição, sucesso, comparações e tempo em ms
     */
    public static ResultadoBusca pesquisarSimples(int[] vetor, int chave) {
        long comparacoes = 0;
        long inicio = System.nanoTime();
        int posicaoEncontrada = -1;
        boolean achou = false;

        for (int i = 0; i < vetor.length; i++) {
            comparacoes++;
            if (vetor[i] == chave) {
                posicaoEncontrada = i;
                achou = true;
                break;
            }
        }

        long fim = System.nanoTime();
        double tempoMs = (fim - inicio) / 1_000_000.0;
        return new ResultadoBusca(posicaoEncontrada, achou, comparacoes, tempoMs, "Pesquisa Sequencial Simples");
    }

    /**
     * Realiza a pesquisa sequencial otimizada em um vetor que DEVE estar ordenado de forma crescente.
     * Como o vetor está ordenado, se encontrarmos um elemento maior que a chave procurada,
     * temos a certeza de que a chave não está presente nas posições seguintes.
     *
     * @param vetorOrdenado vetor previamente ordenado em ordem crescente
     * @param chave valor inteiro a ser procurado
     * @return ResultadoBusca contendo posição, sucesso, comparações e tempo em ms
     */
    public static ResultadoBusca pesquisarOtimizada(int[] vetorOrdenado, int chave) {
        long comparacoes = 0;
        long inicio = System.nanoTime();
        int posicaoEncontrada = -1;
        boolean achou = false;

        for (int i = 0; i < vetorOrdenado.length; i++) {
            comparacoes++;
            if (vetorOrdenado[i] == chave) {
                posicaoEncontrada = i;
                achou = true;
                break;
            }
            // Critério de parada antecipada da busca otimizada em vetor ordenado
            comparacoes++;
            if (vetorOrdenado[i] > chave) {
                break; // Não adianta continuar pois todos os próximos são ainda maiores
            }
        }

        long fim = System.nanoTime();
        double tempoMs = (fim - inicio) / 1_000_000.0;
        return new ResultadoBusca(posicaoEncontrada, achou, comparacoes, tempoMs, "Pesquisa Sequencial Otimizada");
    }
}
