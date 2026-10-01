package algoritmos;

public class Resultado {
    private int[] vetor;
    private long trocas;
    private long comparacoes;
    private double tempoExecucao;
    
    public Resultado(int[] vetor, long trocas, long comparacoes, double tempoExecucao){
        this.vetor = vetor;
        this.trocas = trocas;
        this.comparacoes = comparacoes;
        this.tempoExecucao = tempoExecucao;
    }

    public int[] getVetor() {
        return vetor;
    }

    public long getTrocas() {
        return trocas;
    }

    public long getComparacoes() {
        return comparacoes;
    }

    public double getTempoExecucao() {
        return tempoExecucao;
    }
}
