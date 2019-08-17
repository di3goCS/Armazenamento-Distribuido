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

import model.Imagem;

/**
 * Classe para objetos do tipo Node, que contém seus atributos e métodos. Possui
 * informações sobre a chave, altura do nó, uma referência para o conteúdo do
 * nó, para os nós a esquerda e a direita.
 *
 * Exemplo de uso:
 *
 * Node node= new Node("conteudo");
 *
 * @autor Estéfane Carmo de Souza
 * @author Diego do Carmo Silva
 */
public class Node {

    private Imagem conteudo;
    private Node right;
    private Node left;
    private String key;
    private int height;

    public Node(Imagem conteudo) {
        this.conteudo = conteudo;
        key = conteudo.getNome();
        height = 1;
    }

    /**
     * Método que obtém o conteúdo do nó
     *
     * @return Imagem - conteúdo
     */
    public Imagem getConteudo() {
        return conteudo;
    }

    /**
     * Método que modifica o conteúdo do nó
     *
     * @param novoConteudo - O novo conteúdo do nó
     */
    public void setConteudo(Imagem novoConteudo) {
        conteudo = novoConteudo;
    }

    /**
     * Método que obtém o nó a direita
     *
     * @return Node - nó direito
     */
    public Node getRight() {
        return right;
    }

    /**
     * Método que altera a refêrencia do nó á direita
     *
     * @param right - novo nó direito
     */
    public void setRight(Node right) {
        this.right = right;
    }

    /**
     * Método que obtém o nó a esquerda
     *
     * @return Node - nó esquerdo
     */
    public Node getLeft() {
        return left;
    }

    /**
     * Método que altera a refêrencia do nó á esquerda
     *
     * @param left - novo nó esquerdo
     */
    public void setLeft(Node left) {
        this.left = left;
    }

    /**
     * Método que obtém a chave do nó
     *
     * @return String - a chave
     */
    public String getKey() {
        return key;
    }

    /**
     * Método que altera a chave do nó
     *
     * @param key - a nova chave
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Metodo que obtém a altura do nó
     *
     * @return int - a altura
     */
    public int getHeight() {
        return height;
    }

    /**
     * Método que altera a altura do nó
     *
     * @param height - nova altura
     */
    public void setHeight(int height) {
        this.height = height;
    }

}
