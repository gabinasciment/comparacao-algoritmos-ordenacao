package algoritmos.busca;

import algoritmos.QuickSort;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


public class MenuPesquisa {

    private final GerenciadorVetor gerenciador;
    private final Scanner scanner;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MenuPesquisa menu = new MenuPesquisa(scanner);
        menu.iniciar();
        scanner.close();
    }

    public MenuPesquisa(Scanner scanner) {
        this.gerenciador = new GerenciadorVetor();
        this.scanner = scanner;
    }

    public void iniciar() {
        while (true) {
            System.out.println("\n==============================================================");
            System.out.println("       MÓDULO DE ALGORITMOS DE PESQUISA DE DADOS       ");
            System.out.println("==============================================================");
            if (gerenciador.temVetor()) {
                System.out.printf("Vetor Atual: %d elementos carregados em memória.\n",
                        gerenciador.getVetorAtual().length);
            } else {
                System.out.println("Nenhum vetor carregado na memória. Comece pela Opção 1!");
                System.out.println();
            }
            System.out.println("--------------------------------------------------------------");
            System.out.println();
            System.out.println("--- PARTE 1: PREPARAÇÃO E ANÁLISE DOS DADOS ---");
            System.out.println(" 1. Criar Novo Vetor (Aleatório ou Crescente)");
            System.out.println(" 2. Carregar Vetor de Arquivo de Texto (.txt)");
            System.out.println(" 3. Análise: Encontrar Maior e Menor Valor");
            System.out.println(" 4. Análise: Calcular a Moda Estatística");
            System.out.println(" 5. Exibir Prévia do Vetor Atual");
            System.out.println();
            System.out.println("--- PARTE 2: ALGORITMOS DE PESQUISA ---");
            System.out.println(" 6. Pesquisa Sequencial Simples");
            System.out.println(" 7. Pesquisa Sequencial Otimizada (Vetor Ordenado)");
            System.out.println(" 8. Pesquisa Binária (Vetor Ordenado)");
            System.out.println(" 9. Pesquisa com Hashing (Tabela de Dispersão)");
            System.out.println();
            System.out.println("--- PARTE 3: ANÁLISE DE DESEMPENHO ---");
            System.out.println("10. Comparar Desempenho (Sequencial Simples vs Binária)");
            System.out.println(" 0. Voltar ao Menu Principal");
            System.out.println("==============================================================");
     

            int opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    menuCriarVetor();
                    break;
                case 2:
                    menuCarregarArquivo();
                    break;
                case 3:
                    menuMaiorMenor();
                    break;
                case 4:
                    menuModa();
                    break;
                case 5:
                    gerenciador.exibirPrevia();
                    break;
                case 6:
                    menuPesquisaSequencialSimples();
                    break;
                case 7:
                    menuPesquisaSequencialOtimizada();
                    break;
                case 8:
                    menuPesquisaBinaria();
                    break;
                case 9:
                    menuPesquisaHashing();
                    break;
                case 10:
                    menuCompararDesempenho();
                    break;
                case 0:
                    System.out.println("Retornando...");
                    return;
                default:
                    System.out.println("❌ Opção inválida! Escolha uma opção do menu.");
                    break;
            }
        }
    }

    

    private void menuCriarVetor() {
        System.out.println("\n---CRIAÇÃO DO  VETOR ---");
        int tamanho = lerInteiroPositivo("Informe o tamanho desejado para o vetor: ");
        
        System.out.println("Escolha o tipo de preenchimento:");
        System.out.println("1. Aleatório");
        System.out.println("2. Crescente (1 até " + tamanho + ")");
        int tipo = lerInteiroIntervalo("Opção: ", 1, 2);

        boolean crescente = (tipo == 2);
        gerenciador.criarVetor(tamanho, crescente);
        System.out.printf(" Vetor de %d elementos (%s) criado com sucesso!\n",
                tamanho, (crescente ? "Crescente" : "Aleatório"));
        gerenciador.exibirPrevia();
        System.out.println();
    }

    private void menuCarregarArquivo() {
        System.out.println("\n---  CARREGAR ARQUIVO (.TXT) ---");
        System.out.print("Digite o nome ou caminho do arquivo .txt (ex: numeros_100_aleatorio.txt): ");
        String caminho = scanner.nextLine().trim();

        try {
            int[] vetor = gerenciador.carregarArquivo(caminho);
            System.out.printf("Sucesso! Arquivo lido e vetor de %d números carregado para a memória.\n",
                    vetor.length);
            gerenciador.exibirPrevia();
        } catch (FileNotFoundException e) {
            System.out.println(" ERRO (FileNotFoundException): Arquivo não encontrado no caminho especificado: " + caminho);
            System.out.println(" Certifique-se de que o arquivo existe na pasta do projeto ou use a opção 1 para criar.");
        } catch (IOException e) {
            System.out.println("ERRO de leitura: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("ERRO de formatação: O arquivo contém caracteres inválidos que não são números inteiros.");
        }
    }
   

    private void menuMaiorMenor() {
        if (!validarVetorCarregado()) return;

        System.out.println("\n--- MAIOR E MENOR VALOR ---");
        try {
            AnaliseVetor.Extremos extremos = AnaliseVetor.encontrarMaiorEMenor(gerenciador.getVetorAtual());
            System.out.println("Resultado da análise linear em O(n):");
            System.out.println( "Resultado: " + extremos);
        } catch (Exception e) {
            System.out.println(" Erro ao analisar extremos: " + e.getMessage());
        }
    }

    private void menuModa() {
        if (!validarVetorCarregado()) return;

        System.out.println("\n--- CÁLCULO DA MODA ESTATÍSTICA ---");
        try {
            AnaliseVetor.ResultadoModa resultadoModa = AnaliseVetor.calcularModa(gerenciador.getVetorAtual());
            System.out.println("Resultado do cálculo estatístico:");
            System.out.println("Resultado: " + resultadoModa);
        } catch (Exception e) {
            System.out.println("❌ Erro ao calcular a moda: " + e.getMessage());
        }
    }

    //pesquisas

    private void menuPesquisaSequencialSimples() {
        if (!validarVetorCarregado()) return;

        System.out.println("\n---PESQUISA SEQUENCIAL SIMPLES ---");
        int chave = lerInteiro("Digite o valor inteiro que deseja buscar: ");

        ResultadoBusca res = PesquisaSequencial.pesquisarSimples(gerenciador.getVetorAtual(), chave);
        System.out.println("\n" + res);
    }

    private void menuPesquisaSequencialOtimizada() {
        if (!validarVetorCarregado()) return;
        
        System.out.println("\n--- PESQUISA SEQUENCIAL OTIMIZADA ---");
        System.out.println(" A pesquisa otimizada necessita de um vetor ORDENADO crescentemente.");
        
        int[] vetorParaBusca = obterOuOrdenarVetor();
        int chave = lerInteiro("Digite o valor inteiro que deseja buscar: ");

        ResultadoBusca res = PesquisaSequencial.pesquisarOtimizada(vetorParaBusca, chave);
        System.out.println("\n" + res);
    }

    private void menuPesquisaBinaria() {
        if (!validarVetorCarregado()) return;

        System.out.println("\n---PESQUISA BINÁRIA ---");
        System.out.println(" A pesquisa binária necessita de um vetor ORDENADO crescentemente.");

        int[] vetorParaBusca = obterOuOrdenarVetor();
        int chave = lerInteiro("Digite o valor inteiro que deseja buscar: ");

        ResultadoBusca res = PesquisaBinaria.pesquisar(vetorParaBusca, chave);
        System.out.println("\n" + res);
    }

    private void menuPesquisaHashing() {
        if (!validarVetorCarregado()) return;

        System.out.println("\n--- PESQUISA COM HASHING  ---");
        System.out.println("Escolha a estratégia para tratamento de colisões:");
        System.out.println("1. Encadeamento Separado (Listas encadeadas em cada slot)");
        System.out.println("2. Endereçamento Aberto (Sondagem linear circular )");
        int opcaoHash = lerInteiroIntervalo("Opção: ", 1, 2);

        int[] vetorAtual = gerenciador.getVetorAtual();
        int chave = lerInteiro("Digite o valor inteiro que deseja buscar na Tabela Hash: ");

        if (opcaoHash == 1) {
            System.out.println("Construindo Tabela Hash com Encadeamento...");
            TabelaHashEncadeada th = new TabelaHashEncadeada(vetorAtual);
            ResultadoBusca res = th.buscarComMetricas(chave);
            System.out.println("\n" + res);
        } else {
            System.out.println("Construindo Tabela Hash com Sondagem Linear...");
            TabelaHashLinear th = new TabelaHashLinear(vetorAtual);
            ResultadoBusca res = th.buscarComMetricas(chave);
            System.out.println("\n" + res);
        }
    }

  

    private void menuCompararDesempenho() {
        if (!validarVetorCarregado()) return;

        System.out.println("\n--- COMPARAR DESEMPENHO  ---");
        int chave = lerInteiro("Digite o valor a ser buscado por ambos os algoritmos: ");

        ComparadorDesempenho.RelatorioComparativo relatorio =
                ComparadorDesempenho.comparar(gerenciador.getVetorAtual(), chave);

        relatorio.exibirRelatorio();
    }


    private boolean validarVetorCarregado() {
        if (!gerenciador.temVetor()) {
            System.out.println("Operação impossível: Nenhum vetor carregado na memória!");
            System.out.println("  Por favor, utilize a Opção 1 (Criar Vetor) ou a Opção 2 (Carregar Arquivo) primeiro.");
            System.out.println();
            return false;
        }
        return true;
    }

    
    private int[] obterOuOrdenarVetor() {
        int[] original = gerenciador.getVetorAtual();
        if (estaOrdenado(original)) {
            return original;
        }

        System.out.println("O vetor atual NÃO está ordenado em ordem crescente!");
        System.out.println("1. Ordenar o vetor atual agora mesmo em memória");
        System.out.println("2. Manter vetor original e usar cópia temporária ordenada");
        int op = lerInteiroIntervalo("Escolha: ", 1, 2);

        if (op == 1) {
            System.out.println("Ordenando vetor com QuickSort...");
            QuickSort.sort(original);
            System.out.println("Vetor ordenado!");
            gerenciador.exibirPrevia();
            return original;
        } else {
            int[] copia = Arrays.copyOf(original, original.length);
            Arrays.sort(copia);
            return copia;
        }
    }

    private boolean estaOrdenado(int[] vet) {
        for (int i = 0; i < vet.length - 1; i++) {
            if (vet[i] > vet[i + 1]) {
                return false;
            }
        }
        return true;
    }

   
    public int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String linha = scanner.nextLine().trim();
            try {
                return Integer.parseInt(linha);
            } catch (NumberFormatException e) {
                System.out.println(" Digite apenas números inteiros.");
            }
        }
    }

    public int lerInteiroPositivo(String mensagem) {
        while (true) {
            int val = lerInteiro(mensagem);
            if (val > 0) {
                return val;
            }
            System.out.println("O número deve ser estritamente positivo");
        }
    }

    public int lerInteiroIntervalo(String mensagem, int min, int max) {
        while (true) {
            int val = lerInteiro(mensagem);
            if (val >= min && val <= max) {
                return val;
            }
            System.out.printf("❌Opção fora da faixa permitida! Digite um valor entre %d e %d.\n", min, max);
        }
    }
}
