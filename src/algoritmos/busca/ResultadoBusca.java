package algoritmos.busca;

/**
 * Classe responsável por armazenar os resultados de uma operação de busca.
 * Armazena a posição do elemento encontrado (ou -1), indicador booleano de sucesso,
 * quantidade de comparações realizadas, tempo de execução (em ms) e detalhes contextuais.
 */
public class ResultadoBusca {
    private final int posicao;
    private final boolean encontrado;
    private final long comparacoes;
    private final double tempoExecucaoMs;
    private final String detalhes;

    public ResultadoBusca(int posicao, boolean encontrado, long comparacoes, double tempoExecucaoMs) {
        this(posicao, encontrado, comparacoes, tempoExecucaoMs, "");
    }

    public ResultadoBusca(int posicao, boolean encontrado, long comparacoes, double tempoExecucaoMs, String detalhes) {
        this.posicao = posicao;
        this.encontrado = encontrado;
        this.comparacoes = comparacoes;
        this.tempoExecucaoMs = tempoExecucaoMs;
        this.detalhes = detalhes;
    }

    public int getPosicao() {
        return posicao;
    }

    public boolean isEncontrado() {
        return encontrado;
    }

    public long getComparacoes() {
        return comparacoes;
    }

    public double getTempoExecucaoMs() {
        return tempoExecucaoMs;
    }

    public String getDetalhes() {
        return detalhes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (encontrado) {
            sb.append("-> Elemento ENCONTRADO na posição (índice): ").append(posicao).append("\n");
        } else {
            sb.append("-> Elemento NÃO ENCONTRADO no vetor.\n");
        }
        sb.append(String.format("-> Comparações realizadas: %d\n", comparacoes));
        sb.append(String.format("-> Tempo de execução: %.6f ms", tempoExecucaoMs));
        if (detalhes != null && !detalhes.isEmpty()) {
            sb.append("\n-> Detalhes: ").append(detalhes);
        }
        return sb.toString();
    }
}
