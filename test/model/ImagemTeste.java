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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class ImagemTeste {

    private Computador computer, computer2;
    private Imagem imagem1, imagem2;

    @Before
    public void setUp() throws Exception {
        computer = new Computador("comps103", 500.00);
        computer2 = new Computador("comptd14", 700.00);
        imagem1 = new Imagem("carlosPhoto", 0.0004806519);

    }

    @Test
    public void testeBasico() {
        assertEquals("carlosPhoto", imagem1.getNome());
        assertEquals(0.0004806519, imagem1.getTamanho(), .0001);
        assertNull(imagem1.getComputador());

        imagem1.setComputador(computer);
        assertEquals(computer, imagem1.getComputador());
    }

    @Test
    public void testeEquals() {
        imagem2 = new Imagem("imag001", 0.0004806519);
        assertFalse(imagem2.equals(imagem1));

        imagem2.setNome("carlosPhoto");
        assertTrue(imagem2.equals(imagem1));

        imagem2 = new Imagem("09811", 0.0000651621);
        assertFalse(imagem2.equals(imagem1));
    }

}
