package algoritmos.busca;

public class BuscaLinear {

    public static boolean pesquisaSeq(int x, int[] numero) {
        ResultadoBusca res = PesquisaSequencial.pesquisarSimples(numero, x);
        return res.isEncontrado();
    }

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
