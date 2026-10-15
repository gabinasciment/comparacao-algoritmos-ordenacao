# Sistema de Pesquisa e Ordenação de Dados

Este projeto é uma aplicação robusta em **Java** desenvolvida para a disciplina de **Pesquisa e Ordenação de Dados** (Prof. Me. Renata Cristina Laranja Leite - FAESA).
Ele contempla duas grandes áreas de estudo:
1. **Algoritmos de Ordenação de Dados (Trabalho C1)**
2. **Algoritmos de Pesquisa de Dados (Trabalho C2.A2)**

---

**Desenvolvido por:** Érika Pimenta, Gabriela Nascimento e Laysa  
**Professora responsável:** Profa. Me. Renata Cristina Laranja Leite  

## 🔍 Parte 1 & 2: Algoritmos de Pesquisa de Dados (C2.A2)

O módulo de pesquisa foi desenvolvido para atender integralmente ao edital do trabalho para nota, contendo menu interativo e as seguintes etapas:

### 1. Preparação e Análise dos Dados
- **Criar Vetor Dinâmico:** Usuário define o tamanho do vetor e escolhe entre preenchimento com números **Aleatórios** ou **Crescentes**.
- **Carregar Arquivo (.txt):** Leitura de arquivos texto contendo números inteiros com tratamento de exceções (`FileNotFoundException`, `IOException`).
- **Análise do Vetor:**
  - **Maior e Menor Valor:** Identifica os valores extremos e seus índices em complexidade linear $O(n)$.
  - **Cálculo da Moda Estatística:** Identifica o(s) elemento(s) com maior frequência no conjunto (suporta distribuições unimodais, multimodais e amodais).

### 2. Métodos de Busca Implementados
- **Pesquisa Sequencial Simples:** Varredura linear padrão que informa a posição do elemento encontrado ou mensagem de "não encontrado", contabilizando comparações e tempo.
- **Pesquisa Sequencial Otimizada:** Varredura em vetor previamente ordenado que interrompe a busca antecipadamente caso encontre um elemento estritamente maior que o valor procurado.
- **Pesquisa Binária:** Algoritmo de divisão e conquista em vetor ordenado com complexidade $O(\log n)$.
- **Pesquisa com Hashing (Tabela de Dispersão):**
  - **Função Hash:** Método da divisão $h(k) = k \pmod M$ utilizando `Math.floorMod` para suporte a números negativos e $M$ número primo.
  - **Tratamento de Colisões:** Implementação de duas estratégias:
    - *Encadeamento Separado (`TabelaHashEncadeada`)* com `LinkedList<Integer>` em cada bucket.
    - *Endereçamento Aberto com Sondagem Linear (`TabelaHashLinear`)* circular $i = (i + 1) \pmod M$.
  - Medição de tempo e comparações de colisões na busca.

### 3. Análise Comparativa de Desempenho
- Executa a **Pesquisa Sequencial Simples** e a **Pesquisa Binária** para o mesmo elemento e tamanho de vetor.
- Mede o tempo com precisão de nanosegundos convertido para milissegundos (`System.nanoTime() / 1.000.000.0`).
- Exibe um relatório tabular comparativo com o veredito de eficiência baseado nas complexidades $O(n)$ vs $O(\log n)$.

---

## ⚡ Algoritmos de Ordenação de Dados (C1)

Implementação dos 6 algoritmos clássicos de ordenação com contagem de trocas (movimentações), comparações e tempo de execução:
1. **Bubble Sort**
2. **Selection Sort**
3. **Insertion Sort**
4. **Shell Sort**
5. **Quick Sort**
6. **Heap Sort**

---

## 🛠️ Como Executar

### Pré-requisitos
- **Java JDK 8** ou superior instalado.
- Terminal, Prompt de Comando, PowerShell ou uma IDE (Eclipse, IntelliJ IDEA, VS Code).

### Execução via Terminal / Console
1. Clone o repositório ou navegue até o diretório raiz do projeto.
2. Compile os arquivos Java:
   ```bash
   cd src
   javac -encoding UTF-8 algoritmos/*.java algoritmos/busca/*.java
   ```
3. Execute o programa principal:
   ```bash
   java algoritmos.Main
   ```
   *(Ou execute diretamente o menu de pesquisa com `java algoritmos.busca.MenuPesquisa`)*

### Execução no Eclipse
1. Abra o Eclipse e importe o projeto (`File -> Open Projects from File System...`).
2. Pressione `F5` sobre o projeto para atualizar os arquivos.
3. Clique com o botão direito em `Main.java` (ou `MenuPesquisa.java`) -> **Run As** -> **Java Application**.

---

## 📁 Estrutura do Projeto

```
src/
├── algoritmos/
│   ├── BubbleSort.java
│   ├── SelectionSort.java
│   ├── InsertionSort.java
│   ├── ShellSort.java
│   ├── QuickSort.java
│   ├── HeapSort.java
│   ├── Resultado.java
│   ├── FileHandler.java
│   ├── Main.java
│   │
│   └── busca/
│       ├── ResultadoBusca.java
│       ├── AnaliseVetor.java
│       ├── GerenciadorVetor.java
│       ├── PesquisaSequencial.java
│       ├── PesquisaBinaria.java
│       ├── TabelaHashEncadeada.java
│       ├── TabelaHashLinear.java
│       ├── ComparadorDesempenho.java
│       ├── MenuPesquisa.java
│       ├── BuscaLinear.java
│       ├── BuscaBinaria.java
│       ├── ArvoreBinaria.java
│       ├── ArvoreAvl.java
│       ├── No.java
│       └── Noavl.java
```
