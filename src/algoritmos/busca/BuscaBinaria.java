package algoritmos.busca;

/**
 * Classe utilitária para Busca Binária.
 * Mantém compatibilidade com os exercícios práticos de sala de aula e
 * integra com a classe especializada PesquisaBinaria.
 */
public class BuscaBinaria {

    public static boolean pesquisaBinaria(int x, int[] numeros) {
        ResultadoBusca res = PesquisaBinaria.pesquisar(numeros, x);
        return res.isEncontrado();
    }

    public static ResultadoBusca pesquisarComMetricas(int[] vetorOrdenado, int chave) {
        return PesquisaBinaria.pesquisar(vetorOrdenado, chave);
    }
}
