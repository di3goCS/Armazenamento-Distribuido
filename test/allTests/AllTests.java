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
package allTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import model.ComputadorTeste;
import model.ImagemTeste;
import model.SystemTeste;
import util.FilaPrioridadeTeste;
import util.TreeTeste;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    ComputadorTeste.class,
    ImagemTeste.class,
    SystemTeste.class,
    FilaPrioridadeTeste.class,
    TreeTeste.class
})

public class AllTests {
}
