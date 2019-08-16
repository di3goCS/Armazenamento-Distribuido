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
import java.io.File;
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
     * Método para adicionar um novo computador à fila e adicioná-lo ao arquivo.
     *
     * @param computador
     */
    public void addComputador(Computador computador) {
        add(computador);
        gravarArquivo();
    }

    public void add(Computador computador) {
        No novo = new No(computador);
        if (this.isEmpty() || computador.getEspacoDisponivel() > first.getConteudo().getEspacoDisponivel()) {
            novo.setNext(first);
            first = novo;
        } else { //Se não for vazia
            No auxiliar = first;
            No auxiliar2 = first;
            //Pecorre a lista até que encontre o ultimo elemento e o nó atual tenha prioridade superior ao do novo nó
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
     * Método que remove um nó da fila.
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
     * Método que retorna o Iterador da Lista
     *
     * @return Iterator
     */
    public Iterator iterator() {
        Iterador it = new Iterador(first);
        return it;
    }

    public int ListarEspacoDisponivel() {
        int contador = 0;
        No aux = first;
        int i = 1;
        System.out.println("Espaços Disponível dos Computadores cadastrados");
        while (aux != null) {
            System.out.println(i + " computador: " + aux.getConteudo().getEspacoDisponivel());
            aux = aux.getNext();
            contador++;
            i++;
        }
        return contador;
    }

    public int ListarComputadores() {
        int contador = 0;
        No aux = first;
        while (aux != null) {
            System.out.println("Nome: " + aux.getConteudo().getNome());
            System.out.print("\nCapacidade: " + aux.getConteudo().getCapacidade());
            contador++;
            aux = aux.getNext();
        }
        return contador;
    }
    
    public int ListarImagens(){
        int contador = 0;
        No aux = first;
        while (aux != null) {
            Node root = aux.getConteudo().getImagens().getRootNode();
            System.out.println("Computador: " + aux.getConteudo().getNome());
            System.out.println("Imagens: ");
            aux.getConteudo().getImagens().listarImagem(root);
        }
        return contador;
    }

    public void gravarArquivo() {
        try {
            FileWriter arquivoEscritor = new FileWriter("files//novosComputadores.ascii");
            BufferedWriter reescrever = new BufferedWriter(arquivoEscritor);
            No auxiliar = first;
            while (auxiliar != null) {
                reescrever.write(auxiliar.getConteudo().getNome());
                reescrever.newLine();
                reescrever.write(Double.toString(auxiliar.getConteudo().getCapacidade()));
                reescrever.newLine();
                reescrever.flush();
                auxiliar = auxiliar.getNext();
            }
            reescrever.close();
        } catch (IOException e) {
            System.out.println("Arquivo não encontrado");
        }
    }

    public Computador remove(Computador pc) {
        No auxiliar = first;
        if (auxiliar.getConteudo().equals(pc)) {
            first = first.getNext();
            return auxiliar.getConteudo();
        } else {
            No auxiliar2 = first;
            while (auxiliar2.getNext() != null && !auxiliar2.getConteudo().equals(pc)) {
                auxiliar = auxiliar2;
                auxiliar2 = auxiliar2.getNext();
            }
            if (auxiliar2 != null) {
                auxiliar.setNext(auxiliar2.getNext());
                return auxiliar2.getConteudo();
            } else {
                return null;
            }
        }
    }

}
