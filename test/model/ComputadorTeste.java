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
package model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Classe teste para a análise na criação de objetos do tipo computador e
 * alterações em seus atributos
 *
 * @author Estéfane Carmo de Souza
 * @author Diego do Carmo Silva
 */
public class ComputadorTeste {

    private Computador computer, computer2;
    private Imagem img1, img2, img3, img4;

    @Before
    public void setUp() throws Exception {
        computer = new Computador("comps103", 500.00);
    }

    /**
     * O método testa se cada atributo está sendo inserido corretamente, se as
     * imagens estão sendo inseridas na arvóre do computador e se as alterações
     * são efetuadas com sucesso.
     */
    @Test
    public void Insercao() {
        assertEquals("comps103", computer.getNome());
        assertEquals(500.00, computer.getCapacidade(), .0001);
        assertTrue(computer.getImagens().isEmpty());

        img1 = new Imagem("mariaPhoto", 0.000480);
        computer.addImagem(img1);
        assertEquals(img1, computer.getImagens().getRoot());
        assertEquals(1, computer.getImagens().size());
        assertEquals(499.99952, computer.getEspacoDisponivel(), .0001);

        img2 = new Imagem("carlosPhoto", 0.00806);
        computer.addImagem(img2);
        assertEquals(img1, computer.getImagens().getRoot());
        assertEquals(2, computer.getImagens().size());
        assertEquals(499.99146, computer.getEspacoDisponivel(), .0001);

        img3 = new Imagem("mariaPhoto", 0.00124);
        computer.addImagem(img3);
        assertEquals(2, computer.getImagens().size());
        assertEquals(499.99146, computer.getEspacoDisponivel(), .0001);

        computer.removerImagem("mariaPhoto");
        assertEquals(1, computer.getImagens().size());
        assertEquals(499.99194, computer.getEspacoDisponivel(), .0001);

        computer.removerImagem("mariaPhoto");
        assertEquals(1, computer.getImagens().size());
        assertEquals(499.99194, computer.getEspacoDisponivel(), .0001);

        img4 = new Imagem("anaEnsaio", 0.00806);
        computer.addImagem(img4);
        assertEquals(img2, computer.getImagens().getRoot());
        assertEquals(2, computer.getImagens().size());
        assertEquals(499.98388, computer.getEspacoDisponivel(), .0001);

        computer.removerImagem("anaEnsaio");
        computer.removerImagem("carlosPhoto");
        assertEquals(0, computer.getImagens().size());
        assertEquals(500.00, computer.getEspacoDisponivel(), .0001);
    }

    /**
     * Método que testa se 2 objetos do tipo computador são iguais.
     */
    @Test
    public void testeEquals() {
        computer2 = new Computador("comps103", 900.00);
        assertFalse(computer.equals(computer2));

        computer2 = new Computador("comps103", 500.00);
        assertTrue(computer.equals(computer2));

        computer2 = new Computador("newComputer", 250.00);
        assertFalse(computer2.equals(computer));
    }

}
