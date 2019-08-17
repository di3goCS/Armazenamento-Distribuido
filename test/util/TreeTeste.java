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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Classe teste para a estrutura de dados árvore
 *
 * @author Estéfane Carmo de Souza
 * @author Diego do Carmo Silva
 */
public class TreeTeste {

    private Tree tree;
    private Imagem img1, img2, img3, img4, img5, img6, img7, img8, img9, img10;

    @Before
    public void setUp() throws Exception {
        tree = new Tree();
        img1 = new Imagem("img001", 0.00678);
        img2 = new Imagem("img002", 0.00475);
        img3 = new Imagem("img003", 0.00273);
        img4 = new Imagem("img004", 0.00071);
        img5 = new Imagem("img001", 0.00975);
        img6 = new Imagem("img006", 0.03608);
        img7 = new Imagem("img007", 0.01345);
        img8 = new Imagem("img010", 0.00987);
        img9 = new Imagem("img009", 0.00234);
        img10 = new Imagem("img010", 0.00123);
    }

    /**
     * Método que testa se a árvore está vazia
     */
    @Test
    public void isEmpty() {
        assertTrue(tree.isEmpty());
        tree.add(img1);
        assertFalse(tree.isEmpty());
        tree.remover("img001");
        assertTrue(tree.isEmpty());
    }

    /**
     * Método que testa as inserções de imagens na árvore
     */
    @Test
    public void testeInsercao() {
        assertEquals(0, tree.size());

        tree.add(img8);
        assertEquals(1, tree.size());

        tree.add(img2);
        assertEquals(2, tree.size());

        tree.add(img4);
        assertEquals(3, tree.size());

        tree.add(img6);
        assertEquals(4, tree.size());
    }

    /**
     * Método que testa se o tamanho da árvore é atualizado corretamente.
     */
    @Test
    public void testeSize() {
        assertEquals(0, tree.size());

        tree.add(img1);
        assertEquals(1, tree.size());

        tree.add(img2);
        assertEquals(2, tree.size());

        tree.add(img3);
        tree.add(img4);
        tree.add(img5);
        assertEquals(4, tree.size());

        tree.remover("img001");
        assertEquals(3, tree.size());

        tree.remover("img002");
        tree.remover("img003");
        tree.remover("img004");
        assertEquals(0, tree.size());
    }

    /**
     * Método que testa se a busca por uma imagem é realizada corretamente.
     */
    @Test
    public void TesteBusca() {
        tree.add(img3);
        tree.add(img4);
        tree.add(img5);
        tree.add(img10);
        tree.add(img9);
        assertEquals(5, tree.size());

        assertEquals(img3, tree.procurar("img003"));
        assertEquals(img5, tree.procurar("img001"));
    }

    /**
     * Método que testa se a captura pelo elemento que possui a maior chave e
     * menor chave é efetuada com sucesso.
     */
    @Test
    public void TesteMaiorChaveMenorChave() {
        assertEquals(0, tree.size());
        tree.add(img5);
        tree.add(img7);
        tree.add(img6);
        tree.add(img4);

        assertEquals(4, tree.size());
        assertEquals(img7, tree.getMaiorChave());
        assertEquals(img5, tree.getMenorChave());

        tree.remover("img001");
        tree.add(img10);
        tree.add(img9);
        assertEquals(5, tree.size());

        assertEquals(img10, tree.getMaiorChave());
        assertEquals(img4, tree.getMenorChave());

    }

    /**
     * Método para o teste do balanceamento da árvore
     */
    @Test
    public void TesteBalanceamento() {
        tree.add(img9);
        tree.add(img3);

        assertEquals(1, tree.getBalanceamento(tree.getRootNode()));

        tree.add(img1);
        tree.add(img6);

        assertEquals(-1, tree.getBalanceamento(tree.getRootNode()));
    }

    /**
     * Método que testa se as alturas de cada nó são calculadas corretamente.
     */
    @Test
    public void TesteAlturaMaximaEAltura() {
        tree.add(img3);
        tree.add(img4);
        tree.add(img5);
        tree.add(img10);
        tree.add(img9);

        assertEquals(img3, tree.getRoot());

        int alturaDireita = tree.altura(tree.getRootNode().getRight());
        int alturaEsquerda = tree.altura(tree.getRootNode().getLeft());

        assertEquals(2, tree.alturaMaxima(alturaDireita, alturaEsquerda));

        assertEquals(1, tree.altura(tree.getRootNode().getLeft()));
        assertEquals(2, tree.altura(tree.getRootNode().getRight()));

        assertEquals(3, tree.altura(tree.getRootNode()));
    }

    /**
     * Testa se as remorções na árvore ocorrem corretamente.
     */
    @Test
    public void testeRemorcao() {
        tree.add(img8);
        tree.add(img1);
        tree.add(img7);
        tree.add(img9);
        tree.add(img2);
        tree.add(img6);
        tree.add(img4);

        assertEquals(img7, tree.getRoot());
        assertEquals(img2, tree.getRootNode().getLeft().getConteudo());
        assertEquals(img8, tree.getRootNode().getRight().getConteudo());
        assertEquals(7, tree.size());

        tree.remover(img9.getNome());
        assertEquals(6, tree.size());
        assertEquals(img6, tree.getRoot());
        assertEquals(img2, tree.getRootNode().getLeft().getConteudo());
        assertEquals(img7, tree.getRootNode().getRight().getConteudo());

        tree.remover(img7.getNome());
        assertEquals(img6, tree.getRoot());
        assertEquals(img8, tree.getRootNode().getRight().getConteudo());
        assertEquals(5, tree.size());

        tree.remover(img2.getNome());
        assertEquals(img6, tree.getRoot());
        assertEquals(img2, tree.getRootNode().getLeft().getConteudo());
        assertEquals(4, tree.size());
    }

}
