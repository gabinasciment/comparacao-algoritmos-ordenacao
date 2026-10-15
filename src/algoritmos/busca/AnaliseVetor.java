package algoritmos.busca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe responsável pela análise estatística e exploratória dos dados do vetor.
 * Atende aos requisitos da Parte 1 do trabalho:
 * - Encontrar Maior e Menor Valor em O(n).
 * - Calcular a Moda Estatística (número(s) com maior frequência de repetição).
 */
public class AnaliseVetor {

    public static class Extremos {
        private final int menor;
        private final int maior;
        private final int indiceMenor;
        private final int indiceMaior;

        public Extremos(int menor, int maior, int indiceMenor, int indiceMaior) {
            this.menor = menor;
            this.maior = maior;
            this.indiceMenor = indiceMenor;
            this.indiceMaior = indiceMaior;
        }

        public int getMenor() {
            return menor;
        }

        public int getMaior() {
            return maior;
        }

        public int getIndiceMenor() {
            return indiceMenor;
        }

        public int getIndiceMaior() {
            return indiceMaior;
        }

        @Override
        public String toString() {
            return String.format("Menor Valor: %d (índice %d) | Maior Valor: %d (índice %d)",
                    menor, indiceMenor, maior, indiceMaior);
        }
    }

    public static class ResultadoModa {
        private final List<Integer> modas;
        private final int frequencia;
        private final boolean amodal;

        public ResultadoModa(List<Integer> modas, int frequencia, boolean amodal) {
            this.modas = modas;
            this.frequencia = frequencia;
            this.amodal = amodal;
        }

        public List<Integer> getModas() {
            return modas;
        }

        public int getFrequencia() {
            return frequencia;
        }

        public boolean isAmodal() {
            return amodal;
        }

        @Override
        public String toString() {
            if (amodal) {
                return "O vetor é AMODAL (nenhum elemento se repete; todos possuem frequência 1).";
            }
            if (modas.size() == 1) {
                return String.format("Moda: %d (aparece %d vezes)", modas.get(0), frequencia);
            }
            return String.format("Multimodal (%d modas com frequência %d): %s",
                    modas.size(), frequencia, modas.toString());
        }
    }

    /**
     * Percorre o vetor linearmente em O(n) e encontra os valores extremos (menor e maior).
     *
     * @param vetor vetor de inteiros
     * @return objeto Extremos contendo o menor e o maior valor com seus respectivos índices
     * @throws IllegalArgumentException se o vetor estiver nulo ou vazio
     */
    public static Extremos encontrarMaiorEMenor(int[] vetor) {
        if (vetor == null || vetor.length == 0) {
            throw new IllegalArgumentException("O vetor não pode ser nulo ou vazio para análise.");
        }

        int menor = vetor[0];
        int maior = vetor[0];
        int idxMenor = 0;
        int idxMaior = 0;

        for (int i = 1; i < vetor.length; i++) {
            if (vetor[i] < menor) {
                menor = vetor[i];
                idxMenor = i;
            }
            if (vetor[i] > maior) {
                maior = vetor[i];
                idxMaior = i;
            }
        }

        return new Extremos(menor, maior, idxMenor, idxMaior);
    }

    /**
     * Calcula a moda estatística do vetor (o elemento com maior frequência).
     * Utiliza uma tabela de dispersão (HashMap) para contagem eficiente em O(n).
     * Identifica casos unimodais, multimodais e amodais.
     *
     * @param vetor vetor de inteiros
     * @return objeto ResultadoModa com os elementos mais frequentes e sua frequência
     * @throws IllegalArgumentException se o vetor estiver nulo ou vazio
     */
    public static ResultadoModa calcularModa(int[] vetor) {
        if (vetor == null || vetor.length == 0) {
            throw new IllegalArgumentException("O vetor não pode ser nulo ou vazio para análise.");
        }

        Map<Integer, Integer> contagem = new HashMap<>();
        int maxFrequencia = 0;

        // Contagem de frequências
        for (int num : vetor) {
            int freq = contagem.getOrDefault(num, 0) + 1;
            contagem.put(num, freq);
            if (freq > maxFrequencia) {
                maxFrequencia = freq;
            }
        }

        // Se a frequência máxima for 1 e o vetor tiver mais de um elemento, é amodal
        if (maxFrequencia == 1 && vetor.length > 1) {
            return new ResultadoModa(new ArrayList<>(), 1, true);
        }

        List<Integer> modas = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : contagem.entrySet()) {
            if (entry.getValue() == maxFrequencia) {
                modas.add(entry.getKey());
            }
        }

        return new ResultadoModa(modas, maxFrequencia, false);
    }
}
