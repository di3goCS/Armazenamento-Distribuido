/**
 * Componente Curricular: Módulo Integrado de Programação
 * Autores: Diego Silva e Estéfane Souza
 * Data:14/08/2019
 *
 * Declaramos que este código foi elaborado de forma coletiva pelos autores
 * e não contém nenhum trecho de código de outro autor, tais como provindos
 * de livros e apostilas, e páginas ou documentos eletrônicos da Internet.
 * Qualquer trecho de código de outra autoria que uma citação para o mesmo
 * não a minha está destacado com autor e a fonte do código, e estou ciente
 * que estes trechos não serão considerados para fins de avaliação.
 */
package exception;

/**
 * Exceção lançada quando o usuário tenta inserir uma imagem, porém
 * já existe uma imagem com o mesmo nome no sistema.
 * 
 * @author Estéfane Carmo de Souza
 * @author Diego do Carmo Silva
 */
public class ImagemRepetidaException extends Exception {
    
    public ImagemRepetidaException(){
        super();
    }
}
