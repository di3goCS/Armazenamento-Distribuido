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
package model;

import java.io.*;
import util.FilaDeComputadores;
import util.Tree;

/**
 * Classe para objetos do tipo System, contendo referência para a árvore com
 * todas as imagens e a fila de prioridade com computadore.
 *
 * Exemplo de uso:
 *
 * Systema sistema = new System();
 *
 * @author Estéfane Carmo de Souz
 */
public class System {

    private Tree registro;
    private FilaDeComputadores computadores;

    public System() {
        registro = new Tree();
        computadores = new FilaDeComputadores();
    }

    /**
     * Método para obtenção do registro de imagens do sistema.
     *
     * @return Tree - árvore de imagens.
     */
    public Tree getRegistro() {
        return this.registro;
    }

    /**
     * Método para obtenção dos computadores do sistema.
     *
     * @return FilaDeComputadores - computadores registrados.
     */
    public FilaDeComputadores getComputadores() {
        return this.computadores;
    }

    /**
     * Método que adiciona uma imagem ao sitema.
     *
     * @param img - Imagem a ser adicionada.
     * @throws NullPointerException
     * @throws FileNotFoundException
     */
    public void addImagem(Imagem img) throws NullPointerException, FileNotFoundException {
        try {
            registro.add(img); //Adiciona a imagem na árvore de registos
            computadores.get(0).addImagem(img); //adiciona no computador que possui maior capacidade
            //remove o computador da fila e adiciona para atualizar a prioridade
            Computador removido = computadores.dequeue(); 
            computadores.addComputador(removido);
        } catch (NullPointerException ex) {
            //importa o arquivo de computadores se não tiver computadores cadastrados no sistema
           this.importarComputadores("files//computadores.ascii");
        }
    }

    /**
     * Método para remover uma imagem do sistema.
     *
     * @param remover - nome da imagem que deve ser removida.
     */
    public void removerImagem(String remover) {
        Computador computador = registro.buscarImagem(remover); //busca em que computador está a imagem
        registro.remover(remover); //remove da árvore de registro
        Computador removido = computadores.remove(computador); //remove o computador da fila
        removido.removerImagem(remover); //remove a imagem
        computadores.addComputador(removido); //adiciona na fila, para atualizar a prioridade
    }

    /**
     * Método para importação de imagens a partir de um arquivo ascii, em que
     * cada linha contém o nome da imagem e o tamanho da mesma, separados por um
     * espaço.
     *
     * Exemplo: img1 10.0
     *
     * @param nome_arq - nome do arquivo que contém as imagens.
     * @return int - contador de quantas imagens foram importadas.
     * @throws IOException
     */
    public int importarImagens(String nome_arq) throws IOException {
        BufferedReader ler = null;
        int contador = 0;
        try {
            ler = new BufferedReader(new FileReader(nome_arq));
            while (ler.ready()) {
                String linha = ler.readLine();
                String[] parts = linha.split(" ");
                String nome = parts[0];
                double tamanho = Double.parseDouble(parts[1]); //converte a string com números para double
                Imagem nova = new Imagem(nome, tamanho); //instancia um objeto imagem
                this.addImagem(nova); //adiciona a imagem
                contador++;//incrementa o contador
            }
            ler.close(); //fecha o arquivo
        } catch (IOException ioe) {
            java.lang.System.out.println("Arquivo não encontrado. Tente novamente");
        } catch (NullPointerException ex) {
            importarComputadores("files//computadores.ascii");
        } finally {
            if (ler != null) {
                ler.close(); //fecha o arquivo
            }
            return contador;
        }
    }

    /**
     * Método para importar computadores a partir de um arquivo ascii em que
     * cada par de linhas contém o nome do computador e o armazenamento
     * disponível dela.
     *
     * Exemplo: casa-pc 500
     *
     * @param nomeArquivo - nome do arquivo que contém os computadores.
     * @throws FileNotFoundException
     */
    public void importarComputadores(String nomeArquivo) throws FileNotFoundException {
        FileReader arquivo = new FileReader(nomeArquivo);
        try {
            BufferedReader ler = new BufferedReader(arquivo);
            Computador computador;
            String nome;
            String linha = ler.readLine(); //ler a linha e armazena na string
            while (linha != null) { //Enquanto não for o fim do arquivo
                nome = linha; //armazena os dados da linha anterior
                linha = ler.readLine();//ler a proxima linha
                Double capacidade = Double.parseDouble(linha); //converte para double a string com os números
                computador = new Computador(nome, capacidade); //cria o objeto computador
                computadores.add(computador); //adiciona o computador na fila
                linha = ler.readLine(); //ler a proxima linha
            }
            ler.close();//fecha o arquivo
        } catch (IOException excecao) {
            java.lang.System.out.println("Arquivo não encontrado. Tente novament");
        }
    }
}
