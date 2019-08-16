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
 * uma referência para o conteúdo do nó, a referência para os nós a esquerda e a
 * direita e a altura do nó.
 *
 * Exemplo de uso:
 *
 * Node node= new Node("conteudo");
 *
 * @autor Estéfane Carmo de Souza
 * @author Diego do Carmo Silva
 */
public class Node {

    //mudar o nó para generics depois;
    
    private Imagem conteudo;
    private Node right;
    private Node left;
    private String key;
    private int height;
    
    public Node(Imagem conteudo) {
        this.conteudo = conteudo;
        key= conteudo.getNome();
        height = 1;
    }

    public Imagem getConteudo() {
        return conteudo;
    }

    public void setConteudo(Imagem conteudo) {
        this.conteudo = conteudo;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    public Node getPai(Node root, String chave) {
        if (root.getKey().equals(chave) || root == null) {
            return null;
        }
        if (root.getRight().getKey().equals(chave) || root.getLeft().getKey().equals(chave)) {
            return root;
        }
        else if (root.getKey().compareToIgnoreCase(chave) < 0) {
            return getPai(root.getRight(), chave);
        }
        else{
            return getPai(root.getLeft(), chave);
        }
    }
}
