/**
 * Componente Curricular: Módulo Integrado de Programação
 * Autores: Diego Silva e Estéfane Souza
 * Data: 10/08/2019
 *
 * Declaramos que este código foi elaborado de forma coletiva pelos autores
 * e não contém nenhum trecho de código de outro autor, tais como provindos
 * de livros e apostilas, e páginas ou documentos eletrônicos da Internet.
 * Qualquer trecho de código de outra autoria que uma citação para o mesmo
 * não a minha está destacado com autor e a fonte do código, e estou ciente
 * que estes trechos não serão considerados para fins de avaliação.
 */
package util;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Classe para objetos do tipo Iterador que implementa a interface Iterator,
 * contendo seus métodos e referência para um nó. Exemplo de uso:
 *
 * Iterator iterador = fila.iterator();
 *
 * @author Estéfane Carmo de Souza
 * @author Diego do Carmo Silva
 */
public class IteratorTree implements Iterator {

    private Node atual;

    public IteratorTree(Node root) {
        atual = root;

        if (atual == null) {
            return;
        }

        while (atual.getLeft() != null) {
            atual = atual.getLeft();
        }
    }

    /**
     * Método que analisa se existe um próximo nó na árvore.
     *
     * @return true - Se o nó atual não for nulo
     */
    @Override
    public boolean hasNext() {
        return (atual != null);
    }

    /**
     * Método que retorna o nó atual, e altera a referência do atual para o
     * proximo nó da lista, desde que este não seja nulo.
     *
     * @return Node - O antigo nó atual
     */
    @Override
    public Object next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Node r = atual;

        if (atual.getRight() != null) {
            atual = atual.getRight();
            while (atual.getLeft() != null) {
                atual = atual.getLeft();
            }
            return r.getConteudo();
        }

        while (true) {
            if (atual.getPai(atual, atual.getKey()) == null) {
                atual = null;
                return r.getConteudo();
            }
            if (atual.getPai(atual, atual.getKey()).getLeft() == atual) {
                atual = atual.getPai(atual, atual.getKey());
                return r.getConteudo();
            }
            atual = atual.getPai(atual, atual.getKey());
        }
    }
}
