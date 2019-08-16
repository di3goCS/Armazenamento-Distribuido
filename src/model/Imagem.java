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

/**
 * Esta classe armazena os dados de uma imagem, contendo informação sobre o seu
 * nome e o seu tamanho.
 *
 * Exemplo de uso:
 *
 * Imagem picture= new Imagem("nome",tamanho);
 *
 * @author Estéfane Carmo de Souza
 * @author Diego do Carmo Silva
 */
public class Imagem {

    private String nome;
    private double tamanho;
    private Computador computador;

    public Imagem(String nome, double tamanho) {
        this.nome = nome;
        this.tamanho = tamanho;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getTamanho() {
        return tamanho;
    }

    public void setTamanho(double tamanho) {
        this.tamanho = tamanho;
    }

    public Computador getComputador() {
        return computador;
    }

    public void setComputador(Computador computador) {
        this.computador = computador;
    }

    /**
     * Método que testa a igualdade dos valores dos atributos entre dois objetos
     * do tipo
     *
     * @param img - objeto a ser comparado
     * @return true - se os objetos forem iguais
     */
    public boolean equals(Imagem img) {
        return this.getNome().equals(img.getNome()) && this.getTamanho() == img.getTamanho();
    }
}
