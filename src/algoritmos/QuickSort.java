package algoritmos;

public class QuickSort {
    
    static long trocas = 0;
    static long comparacoes = 0;
    
    // Método principal simplificado para chamar no Menu
    public static Resultado sort(int[] vetor){
        trocas = 0;
        comparacoes = 0;
        long inicio = System.nanoTime();
        
        if (vetor.length > 0) {
            realizarSort(vetor, 0, vetor.length - 1);
        }
        
        long fim = System.nanoTime();
        return new Resultado(vetor, trocas, comparacoes, (fim - inicio) / 1000000.0);
    }    
    
    private static void realizarSort(int[] vetor, int esquerda, int direita){
        if (esquerda < direita){
            int p = particao(vetor, esquerda, direita);
            realizarSort(vetor, esquerda, p);
            realizarSort(vetor, p + 1, direita);
        }
    }
    
    private static int particao(int[] vetor, int esquerda, int direita){
        int meio = (esquerda + direita) / 2;
        int pivot = vetor[meio];
        int i = esquerda - 1;
        int j = direita + 1; 
        
        while(true){
            do{
                i++;
                comparacoes++;
            }while(vetor[i] < pivot);
            do{
                j--;
                comparacoes++;
            }while(vetor[j] > pivot);
            
            if (i >= j){
                return j;
            }
            int aux = vetor[i];
            vetor[i] = vetor[j];
            vetor[j] = aux;
            trocas++;
        }
    }
}
