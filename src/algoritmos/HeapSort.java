package algoritmos;

public class HeapSort {
    
    static long trocas = 0;
    static long comparacoes = 0;
    
    public static Resultado sort(int[] vetor){
        trocas = 0;
        comparacoes = 0;
        long inicio = System.nanoTime();
        
        //Heap Sort
        int n = vetor.length;
        
        for(int i = n / 2 - 1; i >= 0; i--){
            aplicarHeap(vetor, n, i);
        }
        
        for(int j = n-1; j > 0; j--){
            int aux = vetor[0];
            vetor[0] = vetor[j];
            vetor[j] = aux;
            trocas++;
            
            aplicarHeap(vetor, j, 0);
        }
        
        long fim = System.nanoTime();
        return new Resultado(vetor, trocas, comparacoes, (fim - inicio) / 1000000.0);
    }
    
    private static void aplicarHeap(int[] vetor, int n, int i){
        int raiz = i;
        int esquerda = 2*i + 1;
        int direita = 2*i + 2;
        
        if (esquerda < n) {
            comparacoes++;
            if(vetor[esquerda] > vetor[raiz]){
                raiz = esquerda;
            }
        }
        if (direita < n) {
            comparacoes++;
            if(vetor[direita] > vetor[raiz]){
                raiz = direita;
            }
        }
        
        if (raiz != i){
            int aux = vetor[i];
            vetor[i] = vetor[raiz];
            vetor[raiz] = aux;
            trocas++;
            
            aplicarHeap(vetor, n, raiz);
        }
    }
}
