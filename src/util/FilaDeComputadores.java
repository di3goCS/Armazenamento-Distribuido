/**
 * Componente Curricular: Módulo Integrado de Programação
 * Autores: Diego Silva e Estéfane Souza
 * Data: 01/08/2019
 *
 * Declaramos que este código foi elaborado de forma coletiva pelos autores
 * e não contém nenhum trecho de código de outro autor, tais como provindos
 * de livros e apostilas, e páginas ou documentos eletrônicos da Internet.
 * Qualquer trecho de código de outra autoria que uma citação para o mesmo
 * não a minha está destacado com autor e a fonte do código, e estou ciente
 * que estes trechos não serão considerados para fins de avaliação.
 */
package util;

import java.io.*;
import java.util.Iterator;
import model.Computador;

/**
 * Esta classe armazena todos os computadores por ordem de espaços disponíveis,
 * constituindo uma fila de prioridade.
 *
 * Exemplo de uso:
 *
 * FilaEspera fila= new FilaEspera();
 *
 * @author Estéfane Carmo de Souza
 * @author Diego do Carmo Silva
 */
public class FilaDeComputadores {

    private No first;

    public FilaDeComputadores() {
    }

    /**
     * Método que retorna o primeiro elemento da fila.
     *
     * @return Object - Conteúdo do primeiro nó
     */
    public Computador peek() {
        return first.getConteudo();
    }

    public No getFirst() {
        return first;
    }

    /**
     * Método que altera a referência do primeiro elemento da fila
     *
     * @param first - Novo primeiro
     */
    public void setFirst(No first) {
        this.first = first;
    }

    /**
     * Método que retorna o tamanho da fila.
     *
     * @return int - O tamanho
     */
    public int size() {
        int tamanho = 0; //Váriavel para armazenar o tamanho da fila
        No auxiliar = first; //Variável para percorrer 
        while (auxiliar != null) { //Enquanto não for o fim da fila
            tamanho++; //Incrementa-se 1 ao tamanho
            auxiliar = auxiliar.getNext(); //Passa para o próximo nó
        }
        return tamanho;
    }

    /**
     * Método que retorna um nó da fila, que está na posição indicada
     *
     * @param index - Posição do nó a ser buscado
     * @return Object - O objeto encontrado
     */
    public Computador get(int index) {
        int posicao = 0; //indica a posição atual
        No aux = first; //variável para percorrer a fila
        if (isEmpty() || index < 0 || index > this.size()) {
            return null;
        } else { //Se o index for um número dentro do tamanho da fila 
            while (posicao != index) { //Percorre até encontrar a posição
                aux = aux.getNext();
                posicao++;
            }
            return aux.getConteudo();
        }
    }

    /**
     * Método que verifica se a fila está vazia
     *
     * @return true - Se estiver vazia
     */
    public boolean isEmpty() {
        return (first == null);
    }

    /**
     * Método para adicionar um novo computador à fila. Ao ser adicionado, é
     * adicionado a um arquivo .ascii
     *
     * @param computador - computador a ser adicionado
     */
    public void addComputador(Computador computador) {
        add(computador);
        gravarArquivo();
    }

    /**
     * Método que adiciona um computador na fila por ordem de prioridade. A
     * prioridade corresponde ao espaço disponível dos computadores.
     *
     * @param computador - computador a ser adicionado
     */
    public void add(Computador computador) {
        No novo = new No(computador);
        //Se a fila estiver vazia, ou o computador possuir maior prioridade que o primeiro nó, adiciona no início
        if (this.isEmpty() || computador.getEspacoDisponivel() > first.getConteudo().getEspacoDisponivel()) {
            novo.setNext(first);
            first = novo;
        } else { //Se não for vazia
            No auxiliar = first;
            No auxiliar2 = first;
            //Pecorre a fista até que encontre o ultimo elemento e o nó atual tenha prioridade superior ao do novo nó
            while (auxiliar2.getNext() != null && auxiliar2.getConteudo().getEspacoDisponivel() > computador.getEspacoDisponivel()) {
                auxiliar = auxiliar2;
                auxiliar2 = auxiliar2.getNext();
            }
            //Se a prioridade do nó atual ser menor que a do novo, o nó é adicionado antes do nó atual
            if (auxiliar2.getConteudo().getEspacoDisponivel() < computador.getEspacoDisponivel()) {
                novo.setNext(auxiliar2);
                auxiliar.setNext(novo);
            }
            //Se a prioridade do nó atual for maior ou igual, o novo nó é adicionado depois do nó atual
            if (auxiliar2.getConteudo().getEspacoDisponivel() >= computador.getEspacoDisponivel()) {
                novo.setNext(auxiliar2.getNext());
                auxiliar2.setNext(novo);
            }
        }
    }

    /**
     * Método que remove o primeiro nó da fila.
     *
     * @return Computador - O nó que foi removido
     */
    public Computador dequeue() {
        if (first != null) {
            No auxiliar = first;
            first = first.getNext();
            return auxiliar.getConteudo();
        }
        return null;
    }

    /**
     * Método para remoção de um determinado computador do sistema.
     *
     * @param pc - computador a ser removido.
     * @return Computador - computador que foi removido.
     */
    public Computador remove(Computador pc) {
        No auxiliar = first;
        if (auxiliar.getConteudo().equals(pc)) { //Se o primeiro nó for igual ao computador
            first = first.getNext();
            return auxiliar.getConteudo();
        } else { //Caso não seja
            No auxiliar2 = first;
            //Pecorre a fila até o final ou até encontrar o computador informado
            while (auxiliar2.getNext() != null && !auxiliar2.getConteudo().equals(pc)) {
                auxiliar = auxiliar2;
                auxiliar2 = auxiliar2.getNext();
            }
            if (auxiliar2 != null) { //Se encontrou o computador, remove
                auxiliar.setNext(auxiliar2.getNext());
                return auxiliar2.getConteudo();
            } else {
                return null;
            }
        }
    }

    /**
     * Método que lista os espaços disponíveis em cada computador cadastrado no
     * sistema.
     *
     * @return int - contador de quantos computadores foram listados.
     */
    public int ListarEspacoDisponivel() {
        int contador = 0;
        No aux = first;
        int i = 1;
        System.out.println("\n\nESPAÇOS DISPONÍVEIS DOS COMPUTADORES CADASTRADOS");
        while (aux != null) {
            System.out.println(i + "º computador: " + aux.getConteudo().getEspacoDisponivel());
            aux = aux.getNext();
            contador++;
            i++;
        }
        return contador;
    }

    /**
     * Método que lista o nome e a capacidade de cada computador cadastrado no
     * sistema.
     *
     * @return int - contador de quantos computadores foram listados.
     */
    public int ListarComputadores() {
        int contador = 0;
        No aux = first;
        System.out.println("\n\nCOMPUTADORES E CAPACIDADE DE DISCO:");
        while (aux != null) {
            System.out.println(" -Nome: " + aux.getConteudo().getNome()+"  -Capacidade: " + aux.getConteudo().getCapacidade());
            contador++;
            aux = aux.getNext();
        }
        return contador;
    }

    /**
     * Método que lista as imagens armazenadas em cada computador.
     *
     * @return int - contador de imagens listadas.
     */
    public int listarImagensDeComputadores() {
        int contador = 0;
        No aux = first;
        System.out.println("\n\nRELAÇÃO DE IMAGENS DE CADA COMPUTADOR");
        while (aux != null) {
            Node root = aux.getConteudo().getImagens().getRootNode();
            System.out.println("\n\n-Computador: " + aux.getConteudo().getNome());
            System.out.print("  Imagens: ");
            contador += aux.getConteudo().getImagens().listarImagem(root);
            aux = aux.getNext();
        }
        return contador;
    }

    /**
     * Método para listagens dos nomes das imgens em um determinado computador.
     *
     * @param computador - computador que terá as imagens listadas
     * @return int - contador com as imagens listadas
     */
    public int listarNomeImagem(Computador computador) {
        Node root = computador.getImagens().getRootNode();
        System.out.println("\n\nIMAGENS DO COMPUTADOR "+computador.getNome());
        int contador = computador.getImagens().listar(root);
        return contador;
    }

    /**
     * Método que retorna o Iterador da fila
     *
     * @return Iterator
     */
    public Iterator iterator() {
        Iterador it = new Iterador(first);
        return it;
    }

    /**
     * Método para gravar o arquivo de computadores, ao adicionar novos
     * computadores ao sistema.
     */
    public void gravarArquivo() {
        try {
            FileWriter arquivoEscritor = new FileWriter("files//novosComputadores.ascii");
            BufferedWriter reescrever = new BufferedWriter(arquivoEscritor);
            No auxiliar = first;
            while (auxiliar != null) {
                reescrever.write(auxiliar.getConteudo().getNome()); //escreve o nome do computador
                reescrever.newLine();//vai para a proxima linha
                //escreve a capacidade, através da conversão de double para string
                reescrever.write(Double.toString(auxiliar.getConteudo().getCapacidade()));
                reescrever.newLine();
                reescrever.flush();
                auxiliar = auxiliar.getNext();//Auxiliar recebe a referência do novo nó da fila
            }
            reescrever.close(); //fecha o arquivo
        } catch (IOException e) {//Informa se o arquivo não for encontrado
            System.out.println("Arquivo não encontrado");
        }
    }
}
