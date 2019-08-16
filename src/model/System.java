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

import util.FilaDeComputadores;
import util.Tree;
import java.io.*;
import model.Computador;
import model.Imagem;
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

    public Tree getRegistro() {
        return this.registro;
    }

    public FilaDeComputadores getComputadores() {
        return this.computadores;
    }
    
    /** 
     * Método que adiciona uma imagem a um computador
     * 
     * @param img
     * @throws NullPointerException
     * @throws FileNotFoundException 
     */
    public void addImagemAoComputador(Imagem img) throws NullPointerException, FileNotFoundException {
        //PRECISA TRATAR A EXCEÇÃO DE ADD ATÉ A CAPACIDADE SER 0;
        try {
            registro.add(img);
            computadores.get(0).addImagem(img);
            Computador removido = computadores.dequeue();
            computadores.addComputador(removido);
        } catch (NullPointerException ex) {
            importarComputadores("files//computadores.ascii");
        }
    }

    public int importarImagens(String nome_arq) throws IOException {
        BufferedReader br = null;
        int contador = 0;
        try {
            br = new BufferedReader(new FileReader(nome_arq));
            while (br.ready()) {
                String linha = br.readLine();
                String[] parts = linha.split(" ");
                String nome = parts[0];
                double tamanho = Double.parseDouble(parts[1]);
                Imagem nova = new Imagem(nome, tamanho);
                this.addImagemAoComputador(nova);
                contador++;
            }
            br.close();
        } catch (IOException ioe) {
            java.lang.System.out.println("Arquivo não encontrado.Tente novamente");
        } catch (NullPointerException ex) {
            importarComputadores("files//computadores.ascii");
        } finally {
            if (br != null) {
                br.close();
            }
            return contador;
        }
    }

    public void importarComputadores(String nomeArquivo) throws FileNotFoundException {
        FileReader arquivo = new FileReader(nomeArquivo);
        try {
            BufferedReader ler = new BufferedReader(arquivo);
            Computador computador;
            String nome;
            String linha = ler.readLine();
            while (linha != null) {
                nome = linha;
                linha = ler.readLine();
                Double capacidade = Double.parseDouble(linha);
                computador = new Computador(nome, capacidade);
                computadores.add(computador);
                linha = ler.readLine();
            }
            ler.close();
        } catch (IOException excecao) {
            java.lang.System.out.println("Arquivo não encontrado. Tente novament");
        }
    }

    public void removerImagens(String remover) {
        Computador computador = registro.buscarImagem(remover);
        registro.remover(remover);
        Computador removido = computadores.remove(computador);
        removido.removerImagem(remover);
        computadores.addComputador(removido);
    }

}
