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

import model.Computador;

/**
 * Classe para objetos do tipo Node, que contém seus atributos e métodos. Possui
 * uma referência para o conteúdo do nó e a referência para o nó seguinte.
 *
 * Exemplo de uso:
 *
 * Node node= new Node("conteudo");
 *
 * @autor Estéfane Carmo de Souza
 * @author Diego do Carmo Silva
 */
public class No {

    private Computador conteudo;
    private No next;

    public No() {
    }

    public No(Computador conteudo) {
        this.conteudo = conteudo;
    }

    /**
     * Método que obtém o próximo Nó
     *
     * @return Node - O próximo nó
     */
    public No getNext() {
        return next;
    }

    /**
     * Método que obtém o conteúdo do Nó
     *
     * @return Object - O conteúdo
     */
    public Computador getConteudo() {
        return conteudo;
    }

    /**
     * Método que modifica a referência do próximo, para um outro nó.
     *
     * @param newNext - O novo próximo nó
     */
    public void setNext(No newNext) {
        next = newNext;
    }

    /**
     * Método que modifica o conteúdo do nó
     *
     * @param novoConteudo - O novo conteúdo do nó
     */
    public void setConteudo(Computador novoConteudo) {
        conteudo = novoConteudo;
    }
}
