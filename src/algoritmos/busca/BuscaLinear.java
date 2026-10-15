package algoritmos.busca;

/**
 * Classe utilitária para Busca Linear / Sequencial.
 * Mantém compatibilidade com os exercícios práticos de sala de aula e
 * integra com a classe especializada PesquisaSequencial.
 */
public class BuscaLinear {

    // Vetores desordenados - pesquisa sequencial simples
    public static boolean pesquisaSeq(int x, int[] numero) {
        ResultadoBusca res = PesquisaSequencial.pesquisarSimples(numero, x);
        return res.isEncontrado();
    }

    // Funciona para vetores ordenados - pesquisa sequencial otimizada
    public static boolean pesquisaSeqMelhor(int x, int[] numeros) {
        ResultadoBusca res = PesquisaSequencial.pesquisarOtimizada(numeros, x);
        return res.isEncontrado();
    }

    public static ResultadoBusca pesquisarSimplesComMetricas(int[] vetor, int chave) {
        return PesquisaSequencial.pesquisarSimples(vetor, chave);
    }

    public static ResultadoBusca pesquisarOtimizadaComMetricas(int[] vetorOrdenado, int chave) {
        return PesquisaSequencial.pesquisarOtimizada(vetorOrdenado, chave);
    }
}
