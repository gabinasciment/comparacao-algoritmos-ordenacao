package algoritmos;

public class BubbleSort {
    public static Resultado sort(int[] vetor){
        long trocas = 0;
        long comparacoes = 0;
        long inicio = System.nanoTime();
        
        //BUBBLE SORT O(N^2)
        int aux;
        for(int i = 0; i < vetor.length; i++){ // O(N)
            for(int j = i + 1; j < vetor.length; j++){ //O(N)
                comparacoes++;
                if (vetor[i] > vetor[j]){
                    aux = vetor[j];
                    vetor[j] = vetor[i];
                    vetor[i] = aux;
                    trocas++;
                }
            }
        }
        
        long fim = System.nanoTime();
        return new Resultado(vetor, trocas, comparacoes, (fim - inicio) / 1000000.0);
    }
}
