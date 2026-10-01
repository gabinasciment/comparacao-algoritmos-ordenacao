# Comparação de Algoritmos de Ordenação

Este projeto é uma aplicação em Java desenvolvida para a disciplina de Pesquisa e Ordenação de Dados. Seu principal objetivo é implementar, testar e comparar diversos algoritmos clássicos de ordenação, analisando seu desempenho com base em três métricas principais:

- **Número de Comparações:** Quantas vezes os elementos do vetor são comparados entre si (comandos de decisão como `if` e `while`).
- **Número de Trocas (Movimentações):** Quantas vezes os elementos mudam de posição no vetor durante a ordenação.
- **Tempo de Execução (ms):** O tempo real de processamento do algoritmo, medido em milissegundos com precisão de casas decimais.

Desenvolvido por : Érika Pimenta, Gabriela Nascimento e Laysa
Professora responsável : Renata Laranja Lima

## 🚀 Algoritmos Implementados

Os algoritmos avaliados neste projeto são:
1. **Bubble Sort**
2. **Selection Sort**
3. **Insertion Sort**
4. **Shell Sort**
5. **Quick Sort**
6. **Heap Sort**

## 📂 Funcionalidades

- **Geração Automática de Massa de Dados:** O programa é capaz de gerar automaticamente arquivos `.txt` contendo `100`, `1.000` e `10.000` números inteiros.
- **Cenários de Teste:** Para cada tamanho, o sistema cria três variações, utilizando o exato mesmo intervalo de dados (ex: de 1 a 100):
  - **Crescente:** Números já ordenados do menor para o maior.
  - **Decrescente:** Números em ordem inversa (do maior para o menor).
  - **Aleatório:** Números perfeitamente embaralhados.
- **Menu Interativo:** Aplicação executada no terminal/console com um menu de opções dinâmico para leitura e execução.
- **Exportação de Resultados:** Opção para salvar o vetor ordenado final em um novo arquivo `.txt`.

## 🛠️ Como Executar

1. Tenha o **Java (JDK)** instalado na sua máquina.
2. Clone este repositório.
3. Importe o projeto na sua IDE favorita (Eclipse, IntelliJ, etc) ou compile via terminal.
4. Execute a classe `Main.java` localizada no pacote `algoritmos` (dentro da pasta `src`).
5. Siga as instruções do menu no console:
   - Selecione a **Opção 1** para gerar os arquivos de teste `.txt` iniciais.
   - Selecione a **Opção 2** para realizar as ordenações informando o nome do arquivo (ex: `numeros_100_aleatorio.txt`) e escolhendo um dos 6 métodos de ordenação.
