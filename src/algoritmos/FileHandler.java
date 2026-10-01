package algoritmos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class FileHandler {

    // Lê um arquivo contendo inteiros separados por linha e retorna um array
    public static int[] lerArquivo(String caminho) throws IOException {
        List<Integer> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linha = linha.trim();
                if (!linha.isEmpty()) {
                    lista.add(Integer.parseInt(linha));
                }
            }
        }
        int[] vetor = new int[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            vetor[i] = lista.get(i);
        }
        return vetor;
    }

    // Salva o vetor em um arquivo
    public static void salvarArquivo(String caminho, int[] vetor) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho))) {
            for (int j : vetor) {
                bw.write(String.valueOf(j));
                bw.newLine();
            }
        }
    }

    // Gera os arquivos de teste: 100, 1000, 10000 números
    // Cada tamanho terá 3 tipos: aleatório, crescente, decrescente
    public static void gerarArquivosDeTeste() throws IOException {
        int[] tamanhos = {100, 1000, 10000};
        
        for (int tamanho : tamanhos) {
            gerarArquivoVariacao(tamanho, "aleatorio");
            gerarArquivoVariacao(tamanho, "crescente");
            gerarArquivoVariacao(tamanho, "decrescente");
        }
        System.out.println("-> Arquivos de teste (100, 1000, 10000) gerados com sucesso na raiz do projeto!");
    }
    
    private static void gerarArquivoVariacao(int tamanho, String tipo) throws IOException {
        List<Integer> lista = new ArrayList<>();
        
        // Primeiro, gera os números ordenados de 1 até o "tamanho" (ex: 1 a 100)
        for (int i = 1; i <= tamanho; i++) {
            lista.add(i);
        }
        
        // Aplica a variação solicitada
        if (tipo.equals("aleatorio")) {
            Collections.shuffle(lista); // Embaralha a lista mantendo os números do intervalo
        } else if (tipo.equals("decrescente")) {
            Collections.reverse(lista); // Inverte para decrescente (ex: 100 até 1)
        }
        
        // Converte pra vetor para reutilizar salvarArquivo
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = lista.get(i);
        }
        
        String nomeArquivo = "numeros_" + tamanho + "_" + tipo + ".txt";
        salvarArquivo(nomeArquivo, vetor);
    }
}
