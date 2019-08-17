/**
 * Componente Curricular: Módulo Integrado de Programação
 * Autores: Diego Silva e Estéfane Souza
 * Data:  09/0/2019
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
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import model.Computador;

/**
 * Classe teste para a fila de prioridade.
 *
 * @author Estéfane Carmo de Souza
 * @author Diego do Carmo Silva
 */
public class FilaPrioridadeTeste {

    FilaDeComputadores fila;
    Computador pc1;
    Computador pc2;
    Computador pc3;
    Computador pc4;
    Computador pc5;
    Computador pc6;
    Computador pc7;
    Computador pc8;
    Computador pc9;
    Computador pc10;

    /**
     * Este método é executado antes de cada teste de unidade (testes a seguir),
     * e serve para inicializar objetos que são utilizados nos testes.
     */
    @Before
    public void setUp() {
        fila = new FilaDeComputadores();
        pc2 = new Computador("PC-HOME", 110.0);
        pc7 = new Computador("PC-HOME", 290.0);
        pc10 = new Computador("PC-HOME", 320.0);
        pc9 = new Computador("PC-HOME", 410.0);
        pc5 = new Computador("PC-HOME", 412.0);
        pc3 = new Computador("PC-HOME", 499.0);
        pc1 = new Computador("PC-HOME", 500.0);
        pc4 = new Computador("PC-HOME", 501.0);
        pc6 = new Computador("PC-HOME", 723.0);
        pc8 = new Computador("PC-HOME", 800.0);
    }

    /**
     * Teste de unidade que verifica se a inserção de objetos na lista está
     * ocorrendo de forma correta.
     */
    @Test
    public void testInsert() {
        assertEquals(0, fila.size());

        fila.add(pc1);
        assertEquals(1, fila.size());

        fila.add(pc2);
        assertEquals(2, fila.size());

        fila.add(pc3);
        fila.add(pc4);
        fila.add(pc5);
        fila.add(pc6);
        fila.add(pc7);
        fila.add(pc8);
        assertEquals(8, fila.size());

        fila.add(pc9);
        fila.add(pc10);
        assertEquals(10, fila.size());

        assertEquals(pc8, fila.peek());
        assertEquals(pc6, fila.get(1));
        assertEquals(pc3, fila.get(4));
        assertEquals(pc5, fila.get(5));
        assertEquals(pc7, fila.get(8));
        assertEquals(pc2, fila.get(9));

        assertEquals(10, fila.size());
    }

    /**
     * Teste de unidade que verifica se os dados presentes na lista estão sendo
     * recuperados corretamente.
     */
    @Test
    public void testGet() {
        fila.add(pc1);
        fila.add(pc2);
        fila.add(pc3);
        fila.add(pc4);

        assertEquals(pc4, fila.peek());
        assertEquals(pc1, fila.get(1));
        assertEquals(pc3, fila.get(2));
        assertEquals(pc2, fila.get(3));
    }

    /**
     * Teste de unidade que verifica se a remoção de objetos na lista está sendo
     * feita corretamente.
     */
    @Test
    public void testDelete() {
        assertNull(fila.dequeue());
        assertNull(fila.dequeue());

        fila.add(pc1);
        fila.add(pc2);
        fila.add(pc3);
        fila.add(pc4);

        assertEquals(4, fila.size());

        assertEquals(pc4, fila.dequeue());
        assertEquals(3, fila.size());

        assertEquals(pc1, fila.dequeue());
        assertEquals(2, fila.size());

        assertEquals(pc3, fila.dequeue());
        assertEquals(1, fila.size());

        assertEquals(pc2, fila.dequeue());
        assertEquals(0, fila.size());

        assertTrue(fila.isEmpty());
    }

    /**
     * Teste de unidade que verifica se os métodos de inserção e remoção de
     * objetos na lista estão funcionando corretamente.
     */
    @Test
    public void testInsertDelete() {

        assertTrue(fila.isEmpty());

        fila.add(pc1);
        assertFalse(fila.isEmpty());
        assertEquals(pc1, fila.dequeue());
        assertTrue(fila.isEmpty());

        fila.add(pc10);
        assertFalse(fila.isEmpty());
        assertEquals(pc10, fila.dequeue());
        assertTrue(fila.isEmpty());

        fila.add(pc9);
        assertFalse(fila.isEmpty());
        fila.add(pc8);
        assertEquals(pc8, fila.dequeue());
        assertFalse(fila.isEmpty());
        assertEquals(pc9, fila.dequeue());
        assertTrue(fila.isEmpty());
    }

    /**
     * Teste de unidade que verifica se a lista está vazia ou não.
     */
    @Test
    public void testisEmpty() {
        assertTrue(fila.isEmpty());
        fila.add(pc3);
        assertFalse(fila.isEmpty());
        fila.dequeue();
        assertTrue(fila.isEmpty());
    }

    /**
     * Teste de unidade que verifica o tamanho da lista antes e após inserções e
     * remoções.
     */
    @Test
    public void testSize() {
        assertEquals(0, fila.size());

        fila.add(pc1);
        assertEquals(1, fila.size());

        fila.add(pc2);
        assertEquals(2, fila.size());
        fila.add(pc3);
        assertEquals(3, fila.size());
        fila.add(pc4);
        assertEquals(4, fila.size());

        fila.dequeue();
        assertEquals(3, fila.size());

        fila.dequeue();
        assertEquals(2, fila.size());
        fila.dequeue();
        assertEquals(1, fila.size());
        fila.dequeue();
        assertEquals(0, fila.size());
    }
    
    @Test
    public void testRemovePorObjeto(){
        assertEquals(0, fila.size());

        fila.add(pc1);
        assertEquals(1, fila.size());
        fila.add(pc2);
        assertEquals(2, fila.size());
        fila.add(pc3);
        assertEquals(3, fila.size());
        fila.add(pc4);
        assertEquals(4, fila.size());
        assertEquals(pc4, fila.peek());
        
        fila.remove(pc1);
        assertEquals(3, fila.size());
        fila.remove(pc4);
        assertEquals(2, fila.size());
        
        assertEquals(pc3, fila.peek());
        
        fila.remove(pc3);
        assertEquals(1, fila.size());
        fila.remove(pc2);
        assertEquals(0, fila.size());
    }

    /**
     * Teste de unidade que verifica se o método iterator está listando os
     * objetos corretamente.
     */
    @Test
    public void testIterator() {
        Iterator it = fila.iterator();
        assertFalse(it.hasNext());

        fila.add(pc4);
        fila.add(pc5);
        fila.add(pc6);
        fila.add(pc7);

        it = fila.iterator();
        assertTrue(it.hasNext());
        assertEquals(pc6, it.next());
        assertTrue(it.hasNext());
        assertEquals(pc4, it.next());
        assertTrue(it.hasNext());
        assertEquals(pc5, it.next());
        assertTrue(it.hasNext());
        assertEquals(pc7, it.next());
        assertFalse(it.hasNext());
        assertNull(it.next());
    }
}
