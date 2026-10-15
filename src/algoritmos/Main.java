package algoritmos;

import algoritmos.busca.MenuPesquisa;
import java.io.IOException;
import java.util.Scanner;

/**
 * Ponto de entrada principal da aplicação integrado.
 * Permite alternar entre os módulos de:
 * 1. Algoritmos de Pesquisa de Dados (Trabalho C2.A2 - Novo Edital)
 * 2. Algoritmos de Ordenação de Dados (Trabalho C1 - Anterior)
 * 3. Geração de Arquivos de Teste (.txt)
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n==============================================================");
            System.out.println("   SISTEMA DE PESQUISA E ORDENAÇÃO DE DADOS - FAESA");
            System.out.println("==============================================================");
            System.out.println("1. [NOVO] Algoritmos de Pesquisa de Dados (Trabalho C2.A2)");
            System.out.println("2. Algoritmos de Ordenação de Dados (Trabalho C1)");
            System.out.println("3. Gerar Arquivos de Teste (100, 1000, 10000 números)");
            System.out.println("0. Sair do Programa");
            System.out.println("==============================================================");
            System.out.print("Escolha uma opção: ");
            
            String opcao = scanner.nextLine().trim();
            
            if (opcao.equals("0")) {
                System.out.println("Encerrando o programa. Até logo!");
                break;
            } else if (opcao.equals("1")) {
                MenuPesquisa menuPesquisa = new MenuPesquisa(scanner);
                menuPesquisa.iniciar();
            } else if (opcao.equals("2")) {
                executarOrdenacao(scanner);
            } else if (opcao.equals("3")) {
                try {
                    FileHandler.gerarArquivosDeTeste();
                } catch (IOException e) {
                    System.out.println("❌ Erro ao gerar arquivos: " + e.getMessage());
                }
            } else {
                System.out.println("❌ Opção inválida. Tente novamente.");
            }
        }
        
        scanner.close();
    }
    
    private static void executarOrdenacao(Scanner scanner) {
        System.out.println("\n--- EXECUÇÃO DE ORDENAÇÃO ---");
        System.out.print("Digite o nome do arquivo de entrada (ex: numeros_100_aleatorio.txt): ");
        String arquivoEntrada = scanner.nextLine().trim();
        
        int[] vetorOriginal;
        try {
            vetorOriginal = FileHandler.lerArquivo(arquivoEntrada);
            System.out.println("-> Arquivo lido com sucesso! Total de elementos: " + vetorOriginal.length);
        } catch (IOException e) {
            System.out.println("❌ Erro ao ler o arquivo: " + e.getMessage());
            return;
        }
        
        System.out.println("\nEscolha o algoritmo de ordenação:");
        System.out.println("1. Bubble Sort");
        System.out.println("2. Selection Sort");
        System.out.println("3. Insertion Sort");
        System.out.println("4. Shell Sort");
        System.out.println("5. Quick Sort");
        System.out.println("6. Heap Sort");
        System.out.print("Opção: ");
        
        String alg = scanner.nextLine().trim();
        
        Resultado resultado = null;
        String nomeAlgoritmo = "";
        
        switch (alg) {
            case "1":
                resultado = BubbleSort.sort(vetorOriginal);
                nomeAlgoritmo = "Bubble Sort";
                break;
            case "2":
                resultado = SelectionSort.sort(vetorOriginal);
                nomeAlgoritmo = "Selection Sort";
                break;
            case "3":
                resultado = InsertionSort.sort(vetorOriginal);
                nomeAlgoritmo = "Insertion Sort";
                break;
            case "4":
                resultado = ShellSort.sort(vetorOriginal);
                nomeAlgoritmo = "Shell Sort";
                break;
            case "5":
                resultado = QuickSort.sort(vetorOriginal);
                nomeAlgoritmo = "Quick Sort";
                break;
            case "6":
                resultado = HeapSort.sort(vetorOriginal);
                nomeAlgoritmo = "Heap Sort";
                break;
            default:
                System.out.println("❌ Opção inválida.");
                return;
        }
        
        System.out.println("\n===========================================");
        System.out.println("   RESULTADOS - " + nomeAlgoritmo.toUpperCase());
        System.out.println("===========================================");
        System.out.println("Número de Comparações: " + resultado.getComparacoes());
        System.out.println("Número de Trocas (Movimentações): " + resultado.getTrocas());
        System.out.println("Tempo de Execução: " + String.format("%.6f", resultado.getTempoExecucao()) + " ms");
        System.out.println("===========================================");
        
        System.out.print("\nDeseja salvar o resultado ordenado em um arquivo? (S/N): ");
        String salvar = scanner.nextLine().trim();
        if (salvar.equalsIgnoreCase("S")) {
            System.out.print("Digite o nome do arquivo de saída (ex: resultado.txt): ");
            String arquivoSaida = scanner.nextLine().trim();
            try {
                FileHandler.salvarArquivo(arquivoSaida, resultado.getVetor());
                System.out.println("-> Arquivo salvo com sucesso!");
            } catch (IOException e) {
                System.out.println("❌ Erro ao salvar o arquivo: " + e.getMessage());
            }
        }
    }
}
