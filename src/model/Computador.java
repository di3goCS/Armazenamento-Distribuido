/**
 * Componente Curricular: Módulo Integrado de Programação
 * Autores: Diego Silva e Estéfane Souza
 * Data:01/08/2019
 *
 * Declaramos que este código foi elaborado de forma coletiva pelos autores
 * e não contém nenhum trecho de código de outro autor, tais como provindos
 * de livros e apostilas, e páginas ou documentos eletrônicos da Internet.
 * Qualquer trecho de código de outra autoria que uma citação para o mesmo
 * não a minha está destacado com autor e a fonte do código, e estou ciente
 * que estes trechos não serão considerados para fins de avaliação.
 */
package model;

import exception.EspacoInsuficienteException;
import util.Tree;

/**
 * Esta classe armazena os dados de computador, contendo informação sobre o seu
 * nome, a sua capacidade de disco, o espaço disponível na memória e a árvore
 * para as imagens a serem armazenadas.
 *
 * Exemplo de uso:
 *
 * Computador computer = new Computador("nome",capacidade);
 *
 * @author Estéfane Carmo de Souza
 * @author Diego do Carmo Silva
 */
public class Computador {

    private String nome;
    private double capacidade;
    private Tree imagens;
    private double espacoDisponivel;

    /**
     * O construtor inicializa as váriaveis e o espaço disponivel inicialmente é
     * igual a capacidade de disco.
     *
     * @param nome- nome do computador
     * @param capacidade- capacidade de disco do computador
     */
    public Computador(String nome, double capacidade) {
        this.nome = nome;
        this.capacidade = capacidade;
        imagens = new Tree();
        espacoDisponivel = capacidade;
    }

    /**
     * Método que obtém o nome do computador
     *
     * @return String - nome do computador
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método que obtém a capacidade do computador
     *
     * @return double- a capacidade
     */
    public double getCapacidade() {
        return capacidade;
    }

    /**
     * Método para obter a árvore de imagens
     *
     * @return Tree - árvore de imagens
     */
    public Tree getImagens() {
        return imagens;
    }

    /**
     * Método que obtém o espaço disponível no computador
     *
     * @return double - espaco disponivel
     */
    public double getEspacoDisponivel() {
        return espacoDisponivel;
    }

    /**
     * Método que altera o espaco disponível do computador
     *
     * @param espacoDisponivel - novo espaço
     */
    public void setEspacoDisponivel(double espacoDisponivel) {
        this.espacoDisponivel = espacoDisponivel;
    }

    /**
     * Método para adicionar imagens aos computadores.
     *
     * @param imagem - imagem que se deseja adicionar.
     * @return boolean - true se foi adicionada ou false caso contrário.
     */
    public boolean addImagem(Imagem imagem) {
        try {
            if (this.getCapacidade() > 0) {
                imagem.setComputador(this);
                imagens.add(imagem);
                return true;
            } else {
                throw new EspacoInsuficienteException();
            }
        } catch (EspacoInsuficienteException e) {
            java.lang.System.out.println("Adicione mais computadores");
            return false;
        }
    }

    /**
     * Método para remoção de uma imagem da árvore, após a remorção o espaço na
     * memória é liberado.
     *
     * @param nomeImagem - nome da imagem que deve ser removida.
     * @return Imagem - a imagem que foi removida.
     */
    public Imagem removerImagem(String nomeImagem) {
        Imagem removido = imagens.procurar(nomeImagem); //Procura se existe a imagem
        if (removido != null) { //Se encontrou, remove
            imagens.remover(nomeImagem);
            setEspacoDisponivel(this.espacoDisponivel + removido.getTamanho());
        }
        return removido;
    }

    /**
     * Método que testa a igualdade dos valores dos atributos entre dois objetos
     * do tipo Computador
     *
     * @param pc- objeto a ser comparado
     * @return true - se os objetos forem iguais
     */
    public boolean equals(Computador pc) {
        return this.getNome().equals(pc.getNome()) && this.getEspacoDisponivel() == pc.getEspacoDisponivel();
    }

}
