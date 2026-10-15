package algoritmos.busca;

import java.util.Arrays;

public class ComparadorDesempenho {

    public static class RelatorioComparativo {
        private final int chaveBuscada;
        private final ResultadoBusca resultadoSequencial;
        private final ResultadoBusca resultadoBinario;
        private final int tamanhoVetor;

        public RelatorioComparativo(int chaveBuscada, int tamanhoVetor,
                                   ResultadoBusca resultadoSequencial,
                                   ResultadoBusca resultadoBinario) {
            this.chaveBuscada = chaveBuscada;
            this.tamanhoVetor = tamanhoVetor;
            this.resultadoSequencial = resultadoSequencial;
            this.resultadoBinario = resultadoBinario;
        }

        public void exibirRelatorio() {
            System.out.println("\n==========================================================================");
            System.out.println("                   RELATÓRIO COMPARATIVO DE DESEMPENHO                    ");
            System.out.println("==========================================================================");
            System.out.printf("Elemento buscado: %d  |  Tamanho do vetor: %d elementos\n", chaveBuscada, tamanhoVetor);
            System.out.println("--------------------------------------------------------------------------");
            System.out.printf("%-30s | %-12s | %-15s | %-15s\n",
                    "Algoritmo", "Encontrado?", "Comparações", "Tempo (ms)");
            System.out.println();

            System.out.printf("%-30s | %-12s | %-15d | %-15.6f\n",
                    "Pesquisa Sequencial Simples",
                    (resultadoSequencial.isEncontrado() ? "SIM (idx " + resultadoSequencial.getPosicao() + ")" : "NÃO"),
                    resultadoSequencial.getComparacoes(),
                    resultadoSequencial.getTempoExecucaoMs());

            System.out.printf("%-30s | %-12s | %-15d | %-15.6f\n",
                    "Pesquisa Binária (vetor ord.)",
                    (resultadoBinario.isEncontrado() ? "SIM (idx " + resultadoBinario.getPosicao() + ")" : "NÃO"),
                    resultadoBinario.getComparacoes(),
                    resultadoBinario.getTempoExecucaoMs());

            System.out.println();
            System.out.println("VEREDITO DE EFICIÊNCIA:");

            long diffComp = resultadoSequencial.getComparacoes() - resultadoBinario.getComparacoes();
            if (diffComp > 0) {
                System.out.printf(" A Pesquisa Binária foi mais em comparações, economizando %d comparações!\n", diffComp);
                System.out.println("   Justificativa teórica: A Pesquisa Binária tem complexidade O(log n), enquanto a");
                System.out.println("   Pesquisa Sequencial tem complexidade O(n) no caso médio/pior caso.");
            } else if (diffComp < 0) {
                System.out.println("-A Pesquisa Sequencial realizou menos comparações nesta execução específica");
            } else {
                System.out.println("Ambos os algoritmos realizaram o mesmo número de comparações neste caso pontual.");
            }

            if (resultadoBinario.getTempoExecucaoMs() < resultadoSequencial.getTempoExecucaoMs()) {
                System.out.println("A Pesquisa Binária concluiu a execução em menor tempo.");
            } else if (resultadoBinario.getTempoExecucaoMs() > resultadoSequencial.getTempoExecucaoMs()) {
                System.out.println("A Pesquisa Sequencial Simples concluiu em menor tempo neste caso.");
            } else {
                System.out.println("Os tempos foram equivalentes para a precisão medida.");
            }
            System.out.println("==========================================================================\n");
            System.out.println();
        }
    }

    /**
     * Executa a comparação entre Pesquisa Sequencial Simples e Pesquisa Binária.
     *
     * @param vetor vetor atual de dados
     * @param chave valor procurado
     * @return RelatorioComparativo formatado
     */
    public static RelatorioComparativo comparar(int[] vetor, int chave) {
        if (vetor == null || vetor.length == 0) {
            throw new IllegalArgumentException("O vetor não pode ser nulo ou vazio para a comparação.");
        }

        // Executa a busca sequencial no vetor original
        ResultadoBusca resSeq = PesquisaSequencial.pesquisarSimples(vetor, chave);

        // Para a busca binária, precisamos garantir que o vetor esteja ordenado.
        // Criamos uma cópia ordenada para não desestruturar o vetor original do usuário.
        int[] vetorCopia = Arrays.copyOf(vetor, vetor.length);
        Arrays.sort(vetorCopia);

        ResultadoBusca resBin = PesquisaBinaria.pesquisar(vetorCopia, chave);

        return new RelatorioComparativo(chave, vetor.length, resSeq, resBin);
    }
}
