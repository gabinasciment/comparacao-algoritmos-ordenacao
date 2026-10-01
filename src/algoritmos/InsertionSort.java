package algoritmos;

public class InsertionSort {
    public static Resultado sort(int[] vetor){
        long trocas = 0;
        long comparacoes = 0;
        long inicio = System.nanoTime();
        
        int aux, j;
        for(int i=1; i < vetor.length; i++){
            aux = vetor[i];
            j = i-1; 
            
            while(j >= 0){
                comparacoes++;
                if(vetor[j] > aux) {
                    vetor[j+1] = vetor[j];
                    j--;
                    trocas++;
                } else {
                    break;
                }
            }
            vetor[j+1] = aux;
            trocas++;
        }
        
        long fim = System.nanoTime();
        return new Resultado(vetor, trocas, comparacoes, (fim - inicio) / 1000000.0);
    }
}
