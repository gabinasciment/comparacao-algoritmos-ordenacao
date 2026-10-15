package algoritmos.busca;

/**
 * Implementação de Tabela de Dispersão (Hash Table) com Endereçamento Aberto (Sondagem Linear).
 * Segue a abordagem apresentada no slide 17 da Profa. Renata Laranja:
 * - Função Hash: Método da Divisão h(k) = k mod M.
 * - Tratamento de Colisões: Sondagem Linear circular i = (i + 1) % M.
 */
public class TabelaHashLinear {
    private final Integer[] tabela; // null = posição livre
    private final int m;
    private int totalElementos;

    public TabelaHashLinear(int m) {
        if (m <= 0) {
            m = 11;
        }
        this.m = m;
        this.tabela = new Integer[m];
        this.totalElementos = 0;
    }

    public TabelaHashLinear(int[] dados) {
        // Para endereçamento aberto, a tabela não deve ficar cheia (fator de carga ideal < 0.7)
        // Portanto multiplicamos o tamanho por um fator e buscamos um primo
        this(TabelaHashEncadeada.calcularTamanhoPrimoIdeal((int) (dados != null ? dados.length * 1.5 : 11)));
        if (dados != null) {
            for (int chave : dados) {
                inserir(chave);
            }
        }
    }

    private int hash(int chave) {
        return Math.floorMod(chave, m);
    }

    public boolean inserir(int chave) {
        int i = hash(chave);
        for (int t = 0; t < m; t++) { // no máximo m tentativas
            if (tabela[i] == null) {
                tabela[i] = chave;
                totalElementos++;
                return true;
            }
            if (tabela[i].equals(chave)) {
                return false; // já existe
            }
            i = (i + 1) % m; // próxima, circular
        }
        return false; // tabela cheia
    }

    public boolean buscar(int chave) {
        int i = hash(chave);
        for (int t = 0; t < m; t++) {
            if (tabela[i] == null) {
                return false;
            }
            if (tabela[i].equals(chave)) {
                return true;
            }
            i = (i + 1) % m;
        }
        return false;
    }

    public ResultadoBusca buscarComMetricas(int chave) {
        long inicio = System.nanoTime();
        int hashOriginal = hash(chave);
        int i = hashOriginal;

        long comparacoes = 0;
        boolean achou = false;
        int posicaoFinal = -1;

        for (int t = 0; t < m; t++) {
            comparacoes++;
            if (tabela[i] == null) {
                break; // Encontrou slot vazio, o elemento não está na tabela
            }
            comparacoes++;
            if (tabela[i].equals(chave)) {
                achou = true;
                posicaoFinal = i;
                break;
            }
            i = (i + 1) % m;
        }

        long fim = System.nanoTime();
        double tempoMs = (fim - inicio) / 1_000_000.0;

        String detalhes = String.format("Hash inicial: %d | Slot final encontrado: %s | Total na tabela: %d (M = %d)",
                hashOriginal,
                (achou ? String.valueOf(posicaoFinal) : "N/A"),
                totalElementos,
                m);

        return new ResultadoBusca(posicaoFinal, achou, comparacoes, tempoMs, detalhes);
    }

    public int getM() {
        return m;
    }

    public int getTotalElementos() {
        return totalElementos;
    }
}