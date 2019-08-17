/**
 * Componente Curricular: Módulo Integrado de Programação
 * Autores: Diego Silva e Estéfane Souza
 * Data: 16/08/2019
 *
 * Declaramos que este código foi elaborado de forma coletiva pelos autores
 * e não contém nenhum trecho de código de outro autor, tais como provindos
 * de livros e apostilas, e páginas ou documentos eletrônicos da Internet.
 * Qualquer trecho de código de outra autoria que uma citação para o mesmo
 * não a minha está destacado com autor e a fonte do código, e estou ciente
 * que estes trechos não serão considerados para fins de avaliação.
 */
package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe teste para cada operação que o sistema deve realizar.
 *
 * @author Estéfane Carmo de Souza
 * @author Diego do Carmo Silva
 */
public class SystemTeste {

    System sistema;
    Computador computador1, computador2, computador3, computador4, computador5;
    Imagem imagem1, imagem2, imagem3, imagem4, imagem5, imagem6;

    @Before
    public void setUp() throws Exception {
        sistema = new System();
        computador1 = new Computador("hospital", 600);
        computador2 = new Computador("pcRecepcao", 598);
        computador3 = new Computador("computerCMMA", 450);
        computador4 = new Computador("solutions", 602);
        computador5 = new Computador("redeHst", 275);
        imagem1 = new Imagem("Photo1", 5);
        imagem2 = new Imagem("anaBook", 4);
        imagem3 = new Imagem("Photo1", 2);

    }

    /**
     * Testa se um computador e a sua capacidade de disco é cadastrado no
     * sistema corretamente.
     *
     * @throws FileNotFoundException
     */
    @Test
    public void testeCadastroComputadores() throws FileNotFoundException {
        assertTrue(sistema.getComputadores().isEmpty());

        sistema.importarComputadores("files//computadores.ascii");
        assertEquals(10, sistema.getComputadores().size());

        sistema.getComputadores().addComputador(computador1);
        assertEquals(11, sistema.getComputadores().size());

        sistema.getComputadores().addComputador(computador2);
        assertEquals(12, sistema.getComputadores().size());

        Computador computer = new Computador("pc", 1220.00);

        sistema.getComputadores().addComputador(computer);
        assertEquals(13, sistema.getComputadores().size());

        assertEquals(computer, sistema.getComputadores().peek());
    }

    /**
     * Testa se novas imagens e seus tamanhos são armazenadas corretamente nos
     * computadores com mais espaço disponível
     *
     * @throws IOException
     */
    @Test
    public void testeInsercaoImagem() throws IOException {
        //testa se a fila de computadores está vazia
        assertTrue(sistema.getComputadores().isEmpty());

        //adiciona os computadores
        sistema.getComputadores().addComputador(computador1);
        sistema.getComputadores().addComputador(computador2);
        sistema.getComputadores().addComputador(computador4);
        sistema.getComputadores().addComputador(computador3);

        assertEquals(4, sistema.getComputadores().size());

        assertTrue(sistema.getRegistro().isEmpty());

        assertEquals(computador4, sistema.getComputadores().get(0));

        //adiciona a imagem ao sistema e verifica se foi adicionada ao computador de maior espaço
        sistema.addImagem(imagem1);
        assertEquals(imagem1, sistema.getComputadores().get(2).getImagens().getRoot());
        assertEquals(computador4, sistema.getComputadores().get(2));

        //adiciona a imagem ao sistema e verifica se foi adicionada ao computador de maior espaço
        assertEquals(computador1, sistema.getComputadores().get(0));
        sistema.addImagem(imagem2);
        assertEquals(imagem2, sistema.getComputadores().get(2).getImagens().getRoot());
        assertEquals(computador1, sistema.getComputadores().get(2));

        Imagem picture = new Imagem("Photo24", 4);

        //adiciona a imagem ao sistema e verifica se foi adicionada ao computador de maior espaço
        assertEquals(computador2, sistema.getComputadores().get(0));
        sistema.addImagem(picture);
        assertEquals(computador2, sistema.getComputadores().get(2));
        assertEquals(1, sistema.getComputadores().get(2).getImagens().size());

        //verifica se foi adicionada na árvore de registro
        assertEquals(3, sistema.getRegistro().size());
    }

    /**
     * Testa se um arquivo de computadores é importado corretamente e adicionado
     * na fila de computadores. Testa se o arquivo foi escrito corretamente.
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test(expected = FileNotFoundException.class)
    public void testeAbrirGravarArquivosDeComputadores() throws FileNotFoundException, IOException {

        sistema.importarComputadores("files//computadores.ascii");
        assertEquals(10, sistema.getComputadores().size());

        assertEquals(700.0, sistema.getComputadores().peek().getEspacoDisponivel(), .0001);

        assertEquals(502.0, sistema.getComputadores().get(3).getEspacoDisponivel(), .0001);

        assertEquals("computadorHsp", sistema.getComputadores().get(6).getNome());

        assertEquals("hospitalPc", sistema.getComputadores().get(9).getNome());

        System novoSistema = new System();

        sistema.getComputadores().addComputador(computador1);
        sistema.getComputadores().addComputador(computador2);

        novoSistema.importarComputadores("files//novosComputadores");

        assertEquals(12, novoSistema.getComputadores().size());
        assertEquals("pcRecepcao", novoSistema.getComputadores().get(5).getNome());
        assertEquals("hospital", novoSistema.getComputadores().get(4).getNome());
    }

    /**
     * Método que testa se um arquivo de imagens foi importado corretamente, e
     * se as imagens foram adicionadas no computadores de maior espaço.
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test
    public void testeImportarImagens() throws FileNotFoundException, IOException {
        sistema.importarComputadores("files//computadores.ascii");
        assertEquals(10, sistema.getComputadores().size());

        sistema.importarImagens("files//imagens.ascii");
        assertEquals(1000, sistema.getRegistro().size());

        assertEquals(187, sistema.getComputadores().listarNomeImagem(sistema.getComputadores().get(0)));

        assertEquals(29, sistema.getComputadores().listarNomeImagem(sistema.getComputadores().get(1)));

        assertEquals(29, sistema.getComputadores().listarNomeImagem(sistema.getComputadores().get(2)));

        assertEquals(16, sistema.getComputadores().listarNomeImagem(sistema.getComputadores().get(3)));

        assertEquals(111, sistema.getComputadores().listarNomeImagem(sistema.getComputadores().get(4)));

        assertEquals(23, sistema.getComputadores().listarNomeImagem(sistema.getComputadores().get(5)));

        assertEquals(114, sistema.getComputadores().listarNomeImagem(sistema.getComputadores().get(6)));

        assertEquals(190, sistema.getComputadores().listarNomeImagem(sistema.getComputadores().get(7)));

        assertEquals(181, sistema.getComputadores().listarNomeImagem(sistema.getComputadores().get(8)));

        assertEquals(120, sistema.getComputadores().listarNomeImagem(sistema.getComputadores().get(9)));
    }

    /**
     * Testa se a listagem dos computadores com suas capacidades foi efetuada
     * corretamente;
     *
     * @throws FileNotFoundException
     */
    @Test
    public void testeListarComputadoresECapacidade() throws FileNotFoundException {
        sistema.importarComputadores("files//computadores.ascii");
        assertEquals(10, sistema.getComputadores().ListarComputadores());
    }

    /**
     * Testa se a listagem das imagens/tamanho de cada computador foi efetuada
     * corretamente.
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test
    public void testeListarImagensETamanhosDeCadaComputador() throws FileNotFoundException, IOException {
        sistema.importarComputadores("files//computadores.ascii");
        sistema.importarImagens("files//imagens.ascii");

        assertEquals(1000, sistema.getComputadores().listarImagensDeComputadores());
    }

    /**
     * Testa se a listagem do espaço disponível de cada computador foi realizada
     * corretamente.
     *
     * @throws FileNotFoundException
     */
    @Test
    public void testeListarEspacoDisponivelEmComputadores() throws FileNotFoundException {
        sistema.importarComputadores("files//computadores.ascii");
        assertEquals(10, sistema.getComputadores().ListarEspacoDisponivel());
    }

    /**
     * Testa se a listagem dos nomes das imagens de um determinado computador
     * foi realizada corretamente.
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test
    public void testeListarImagens() throws FileNotFoundException, IOException {
        sistema.importarComputadores("files//computadores.ascii");
        assertEquals(10, sistema.getComputadores().size());

        sistema.importarImagens("files//imagens.ascii");
        assertEquals(1000, sistema.getRegistro().size());

        assertEquals(187, sistema.getComputadores().listarNomeImagem(sistema.getComputadores().get(0)));
        assertEquals("computerCmma", sistema.getComputadores().get(0).getNome());

        assertEquals(16, sistema.getComputadores().listarNomeImagem(sistema.getComputadores().get(3)));
        assertEquals("hospitalPc", sistema.getComputadores().get(3).getNome());

        assertEquals(114, sistema.getComputadores().listarNomeImagem(sistema.getComputadores().get(6)));
        assertEquals("computer", sistema.getComputadores().get(6).getNome());

        assertEquals(120, sistema.getComputadores().listarNomeImagem(sistema.getComputadores().get(9)));
        assertEquals("cmmaSolutions", sistema.getComputadores().get(9).getNome());
    }

    /**
     * Testa a busca de uma imagem no sistema e em que computador ela está
     * armazenada.
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test
    public void testeBuscarImagem() throws FileNotFoundException, IOException {
        sistema.importarComputadores("files//computadores.ascii");
        assertEquals(10, sistema.getComputadores().size());

        sistema.importarImagens("files//imagens.ascii");
        assertEquals(1000, sistema.getRegistro().size());

        assertEquals(sistema.getComputadores().get(7), sistema.getRegistro().buscarImagem("aaf"));
        assertEquals("computadorcm", sistema.getComputadores().get(7).getNome());

        assertEquals(sistema.getComputadores().get(0), sistema.getRegistro().buscarImagem("acn"));
        assertEquals("computerCmma", sistema.getComputadores().get(0).getNome());

        assertEquals(sistema.getComputadores().get(8), sistema.getRegistro().buscarImagem("acw"));
        assertEquals("solutions", sistema.getComputadores().get(8).getNome());

        assertEquals(sistema.getComputadores().get(6), sistema.getRegistro().buscarImagem("ato"));
        assertEquals("computer", sistema.getComputadores().get(6).getNome());

        assertEquals(sistema.getComputadores().get(3), sistema.getRegistro().buscarImagem("bia"));
        assertEquals("hospitalPc", sistema.getComputadores().get(3).getNome());

        assertEquals(sistema.getComputadores().get(9), sistema.getRegistro().buscarImagem("ana"));
        assertEquals("cmmaSolutions", sistema.getComputadores().get(9).getNome());

        assertEquals(sistema.getComputadores().get(5), sistema.getRegistro().buscarImagem("blc"));
        assertEquals("solutionsComputer", sistema.getComputadores().get(5).getNome());
    }

    /**
     * Testa a exclusão de uma imagem no sistema e se o espaço disonível do
     * computador hospedeiro aumenta após a remorção.
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Test
    public void testeExcluirImagem() throws FileNotFoundException, IOException {
        sistema.importarComputadores("files//computadores.ascii");
        assertEquals(10, sistema.getComputadores().size());

        sistema.importarImagens("files//imagens.ascii");
        assertEquals(1000, sistema.getRegistro().size());

        assertEquals(216.5999999999994, sistema.getComputadores().get(7).getEspacoDisponivel(), .0001);
        assertEquals("computadorcm", sistema.getComputadores().get(7).getNome());
        sistema.removerImagem("aaa");
        assertEquals(219.5999999999994, sistema.getComputadores().get(4).getEspacoDisponivel(), .0001);
        assertEquals("computadorcm", sistema.getComputadores().get(4).getNome());

        assertEquals(221.29999999999936, sistema.getComputadores().get(0).getEspacoDisponivel(), .0001);
        assertEquals("computerCmma", sistema.getComputadores().get(0).getNome());
        sistema.removerImagem("aka");
        assertEquals(221.39999999999935, sistema.getComputadores().get(0).getEspacoDisponivel(), .0001);
        assertEquals("computerCmma", sistema.getComputadores().get(0).getNome());

        assertEquals(212.2000000000001, sistema.getComputadores().get(9).getEspacoDisponivel(), .0001);
        assertEquals("cmmaSolutions", sistema.getComputadores().get(9).getNome());
        sistema.removerImagem("aql");
        assertEquals(215.0000000000001, sistema.getComputadores().get(9).getEspacoDisponivel(), .0001);
        assertEquals("cmmaSolutions", sistema.getComputadores().get(9).getNome());

        assertEquals(218.10000000000014, sistema.getComputadores().get(5).getEspacoDisponivel(), .0001);
        assertEquals("hspSolution", sistema.getComputadores().get(5).getNome());
        sistema.removerImagem("ary");
        assertEquals(219.50000000000014, sistema.getComputadores().get(5).getEspacoDisponivel(), .0001);
        assertEquals("hspSolution", sistema.getComputadores().get(5).getNome());

        assertEquals(215.79999999999987, sistema.getComputadores().get(8).getEspacoDisponivel(), .0001);
        assertEquals("solutions", sistema.getComputadores().get(8).getNome());
        sistema.removerImagem("azm");
        assertEquals(216.39999999999986, sistema.getComputadores().get(8).getEspacoDisponivel(), .0001);
        assertEquals("solutions", sistema.getComputadores().get(8).getNome());
    }

}
