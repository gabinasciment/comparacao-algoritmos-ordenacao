package algoritmos;

public class SelectionSort {
    public static Resultado sort(int[] vetor){
        long trocas = 0;
        long comparacoes = 0;
        long inicio = System.nanoTime();
        
        //Selection sort O(N^2)
        int posicao_menor, aux;
        for(int i=0; i < vetor.length; i++){ //O(N)
            posicao_menor = i;
            for(int j = i+1; j < vetor.length; j++){ //O(N)
                comparacoes++;
                if (vetor[j] < vetor[posicao_menor]){
                    posicao_menor = j;
                }
            }
            aux = vetor[posicao_menor];
            vetor[posicao_menor] = vetor[i];
            vetor[i] = aux;
            trocas++;
        }
        
        long fim = System.nanoTime();
        return new Resultado(vetor, trocas, comparacoes, (fim - inicio) / 1000000.0);
    }
}
