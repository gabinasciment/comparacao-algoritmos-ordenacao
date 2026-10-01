package algoritmos;

public class ShellSort {
    public static Resultado sort(int[] vetor){
        long trocas = 0;
        long comparacoes = 0;
        long inicio = System.nanoTime();
        
        int h = 1;
        int n = vetor.length;
        while(h < n){
            h = h * 3 + 1;
        }
        h = (int) Math.floor(h / 3);
        
        int elemento, j;
        while(h > 0){
            for(int i = h; i < n; i++){
                elemento = vetor[i];
                j = i;
                while(j >= h){
                    comparacoes++;
                    if(vetor[j - h] > elemento) {
                        vetor[j] = vetor[j - h];
                        j = j - h;
                        trocas++;
                    } else {
                        break;
                    }
                }
                vetor[j] = elemento;
                trocas++;
            }
            h = h / 2;
        }
        
        long fim = System.nanoTime();
        return new Resultado(vetor, trocas, comparacoes, (fim - inicio) / 1000000.0);
    }
}
