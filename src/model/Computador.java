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
 * nome e a sua capacidade de disco. Exemplo de uso:
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

    public Computador(String nome, double capacidade) {
        this.nome = nome;
        this.capacidade = capacidade;
        imagens = new Tree();
        espacoDisponivel = capacidade;
    }

    public String getNome() {
        return nome;
    }

    public double getCapacidade() {
        return capacidade;
    }

    public Tree getImagens() {
        return imagens;
    }

    public double getEspacoDisponivel() {
        return espacoDisponivel;
    }

    public void setEspacoDisponivel(double espacoDisponivel) {
        this.espacoDisponivel = espacoDisponivel;
    }

    /** Método para adicionar imagens aos computadores.
     * 
     * 
     * 
     * @param imagem - imagem que se deseja adicionar.
     * @return boolean - true se foi adicionada ou false caso ccontrário.
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
            // é pra chamar mais computadores ou lançar um print mandando
            // add?
            java.lang.System.out.println("Adicione mais computadores");
            return false;
        }

    }

    //O add da arvore precisa retornar o que removeu
    /** Método para remoção de uma imagem.
     * 
     * @param nomeImagem - nome da imagem que deve ser removida.
     * @return Imagem - a imagem que foi removida.
     */
    public Imagem removerImagem(String nomeImagem) {
        Imagem removido = imagens.procurar(nomeImagem);
        if (removido != null) {
            imagens.remover(nomeImagem);
            setEspacoDisponivel(this.espacoDisponivel + removido.getTamanho());
        }
        return removido;
    }

    /** Método que testa a igualdade dos valores dos atributos entre dois objetos
     * do tipo Computador
     *
     * @param pc - objeto a ser comparado
     * @return true - se os objetos forem iguais
     */
    public boolean equals(Computador pc) {
        return this.getNome().equals(pc.getNome()) && this.getEspacoDisponivel() == pc.getEspacoDisponivel();
    }

}
