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

import java.util.Iterator;
import model.Computador;

/**
 * Classe para objetos do tipo Iterador que implementa a interface Iterator,
 * contendo seus métodos e referência para um nó. Exemplo de uso:
 *
 * Iterator iterador = fila.iterator();
 *
 * @author Estéfane Carmo de Souza
 * @author Diego do Carmo Silva
 */
public class Iterador implements Iterator {

    private No atual;

    public Iterador(No primeiro) {
        atual = primeiro;
    }

    /**
     * Método que analisa se existe um próximo nó na fila.
     *
     * @return true - Se o nó atual não for nulo
     */
    @Override
    public boolean hasNext() {
        return (atual != null);
    }

    /**
     * Método que retorna o nó atual, e altera a referência do atual para o
     * proximo nó da fila, desde que este não seja nulo.
     *
     * @return Object- O conteúdo do antigo nó atual
     */
    @Override
    public Object next() {
        if (hasNext()) {
            No next = atual;
            atual = atual.getNext();
            return next.getConteudo();
        } else {
            return null;
        }
    }
}
