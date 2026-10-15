package algoritmos.busca;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Implementação de Tabela de Dispersão (Hash Table) com Encadeamento Separado.
 * Segue a abordagem apresentada no material de aula da Profa. Renata Laranja:
 * - Função Hash: Método da Divisão h(k) = k mod M (utilizando Math.floorMod para tratamento de negativos).
 * - Tratamento de Colisões: Encadeamento separado (cada índice da tabela guarda uma LinkedList<Integer>).
 * - Métricas de Busca: Conta comparações de chaves e tempo de execução.
 */
public class TabelaHashEncadeada {
    private final ArrayList<LinkedList<Integer>> tabela;
    private final int m; // Tamanho da tabela (idealmente número primo)
    private int totalElementos;

    /**
     * Construtor com tamanho explícito m.
     * @param m tamanho da tabela hash (número de slots/baldes)
     */
    public TabelaHashEncadeada(int m) {
        if (m <= 0) {
            m = 11; // Padrão mínimo seguro
        }
        this.m = m;
        this.tabela = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            tabela.add(new LinkedList<>()); // Inicializa lista vazia em cada slot
        }
        this.totalElementos = 0;
    }

    /**
     * Construtor que cria a tabela com base em um vetor de dados,
     * calculando automaticamente um número primo adequado para o tamanho da tabela.
     * @param dados vetor de dados inteiros
     */
    public TabelaHashEncadeada(int[] dados) {
        this(calcularTamanhoPrimoIdeal(dados != null ? dados.length : 11));
        if (dados != null) {
            for (int chave : dados) {
                inserir(chave);
            }
        }
    }

    /**
     * Função de Hash pelo Método da Divisão: h(chave) = chave mod M.
     * Utiliza Math.floorMod para garantir resultado sempre positivo no intervalo [0, M-1].
     */
    public int hash(int chave) {
        return Math.floorMod(chave, m);
    }

    /**
     * Insere uma chave na tabela hash.
     * Trata colisões adicionando o elemento na lista encadeada daquele índice.
     */
    public void inserir(int chave) {
        int indice = hash(chave);
        LinkedList<Integer> lista = tabela.get(indice);
        if (!lista.contains(chave)) { // Evita valores duplicados na mesma lista
            lista.add(chave);
            totalElementos++;
        }
    }

    /**
     * Busca simples por valor.
     */
    public boolean buscar(int chave) {
        int indice = hash(chave);
        return tabela.get(indice).contains(chave);
    }

    /**
     * Remove uma chave da tabela hash.
     */
    public boolean remover(int chave) {
        int indice = hash(chave);
        boolean removido = tabela.get(indice).remove(Integer.valueOf(chave));
        if (removido) {
            totalElementos--;
        }
        return removido;
    }

    /**
     * Realiza a busca detalhada coletando métricas de desempenho (tempo e comparações).
     *
     * @param chave valor a ser procurado
     * @return ResultadoBusca com detalhes sobre o bucket, comparações e tempo
     */
    public ResultadoBusca buscarComMetricas(int chave) {
        long inicio = System.nanoTime();
        int endereco = hash(chave);
        LinkedList<Integer> lista = tabela.get(endereco);

        long comparacoes = 0;
        boolean achou = false;
        int posicaoNaLista = -1;

        for (int i = 0; i < lista.size(); i++) {
            comparacoes++;
            if (lista.get(i) == chave) {
                achou = true;
                posicaoNaLista = i;
                break;
            }
        }

        long fim = System.nanoTime();
        double tempoMs = (fim - inicio) / 1_000_000.0;

        String detalhes = String.format("Slot (endereço hash h(k)): %d | Posição na lista de colisão: %s | Tamanho da lista no slot: %d | Total de elementos na tabela: %d (M = %d)",
                endereco,
                (achou ? String.valueOf(posicaoNaLista) : "N/A"),
                lista.size(),
                totalElementos,
                m);

        return new ResultadoBusca(endereco, achou, comparacoes, tempoMs, detalhes);
    }

    public int getM() {
        return m;
    }

    public int getTotalElementos() {
        return totalElementos;
    }

    /**
     * Auxiliar para encontrar o próximo número primo maior que N
     * (Recomendação dos slides: M primo para diminuir colisões).
     */
    public static int calcularTamanhoPrimoIdeal(int n) {
        int primo = Math.max(n + 1, 11);
        while (!ehPrimo(primo)) {
            primo++;
        }
        return primo;
    }

    private static boolean ehPrimo(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }
}
