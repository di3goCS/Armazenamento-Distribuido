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
package util;

import java.util.Iterator;
import model.Computador;
import model.Imagem;

/**
 * Classe para objetos do tipo Tree. Esta classe, possui uma referência para a
 * raíz da árvore.
 *
 * Exemplo de uso:
 *
 * Tree arvore = new Tree();
 *
 * @author Estéfane Carmo de Souza
 * @author Diego do Carmo Silva
 */
public class Tree {

    private Node root;

    public Tree() {
    }

    public Node getRootNode() {
        return root;
    }

    public Imagem getRoot() {
        return root.getConteudo();
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    /**
     * Método que verifica se a árvore está vazia
     *
     * @return true - Se estiver vazia
     */
    public boolean isEmpty() {
        return (root == null);
    }

    public int altura(Node no) {
        if (no == null) {
            return 0;
        } else {
            return no.getHeight();
        }
    }

    public int alturaMaxima(int altura1, int altura2) {
        if (altura1 > altura2) {
            return altura1;
        } else {
            return altura2;
        }
    }

    private Node rotacaoEsquerda(Node root) {
        Node auxiliar = root.getRight();
        root.setRight(auxiliar.getLeft());
        auxiliar.setLeft(root);

        root.setHeight(alturaMaxima(altura(root.getLeft()), altura(root.getRight())) + 1);
        auxiliar.setHeight(alturaMaxima(altura(auxiliar.getLeft()), altura(auxiliar.getRight())) + 1);

        return auxiliar;
    }

    private Node rotacaoDireita(Node root) {
        Node auxiliar = root.getLeft();
        root.setLeft(auxiliar.getRight());
        auxiliar.setRight(root);

        root.setHeight(alturaMaxima(altura(root.getLeft()), altura(root.getRight())) + 1);
        auxiliar.setHeight(alturaMaxima(altura(auxiliar.getLeft()) + 1, altura(auxiliar.getRight())) + 1);

        return auxiliar;
    }

    private Node rotacaoDE(Node root) {
        root.setRight(rotacaoDireita(root.getRight()));
        return rotacaoEsquerda(root);
    }

    private Node rotacaoED(Node root) {
        root.setLeft(rotacaoEsquerda(root.getLeft()));
        return rotacaoDireita(root);
    }

    public int getBalanceamento(Node root) {
        if (root == null) {
            return 0;
        } else {
            return altura(root.getLeft()) - altura(root.getRight());
        }
    }

    public Computador buscarImagem(String nomeImagem) {
        Imagem auxiliar = procurar(nomeImagem);
        return auxiliar.getComputador();
    }

    //Contando que arvore é só para imagem
    public Imagem procurar(String key) {
        Node encontrado = procurarNo(this.root, key);
        if (encontrado == null) {
            return null;
        } else {
            return encontrado.getConteudo();
        }
    }

    private Node procurarNo(Node root, String key) {
        if (root == null) {
            return null;
        }
        if (root.getKey().equals(key)) {
            return root;
        } else if (root.getKey().compareToIgnoreCase(key) > 0) {
            return procurarNo(root.getLeft(), key);
        } else {
            return procurarNo(root.getRight(), key);
        }
    }

    public Node balancear(Node root) {
        if (getBalanceamento(root) == 2) {
            if (getBalanceamento(root.getLeft()) >= 0) {
                return rotacaoDireita(root);
            } else if (getBalanceamento(root.getLeft()) < 0) {
                return rotacaoED(root);
            }
        } else if (getBalanceamento(root) == -2) {
            if (getBalanceamento(root.getRight()) <= 0) {
                return rotacaoEsquerda(root);
            } else if (getBalanceamento(root.getRight()) > 0) {
                return rotacaoDE(root);
            }
        }
        return root;
    }

    public void listar(Node root) {
        if (root != null) {
            System.out.print("\nImagem: " + root.getKey());
            listar(root.getLeft());
            listar(root.getRight());
        }
    }

    public void listarImagem(Node root) {
        if (root != null) {
            System.out.print("\nNome " + root.getKey());
            System.out.print("\tTamanho" + root.getConteudo().getTamanho());
            listarImagem(root.getLeft());
            listarImagem(root.getRight());
        }
    }

    public Imagem getMenorChave() {
        Node r = menorChave(this.root);
        return r.getConteudo();
    }

    private Node menorChave(Node root) {
        if (root == null) {
            return null;
        }
        if (root.getRight() == null && root.getLeft() == null) {
            return root;
        } else if (root.getLeft() != null) {
            return menorChave(root.getLeft());
        } else {
            return root;
        }
    }

    public Imagem getMaiorChave() {
        Node im = maiorChave(this.root);
        return im.getConteudo();
    }

    private Node maiorChave(Node root) {
        if (root == null) {
            return null;
        }
        if (root.getRight() == null && root.getLeft() == null) {
            return root;
        } else if (root.getRight() != null) {
            return maiorChave(root.getRight());
        } else {
            return root;
        }
    }

    //considerando que a arvore só será usada para a imagem
    public void add(Imagem im) {
        Computador pc;
        try {
            this.root = insercao(im, this.root);
            pc = im.getComputador();
            if (pc != null) {
                pc.setEspacoDisponivel(pc.getEspacoDisponivel() - im.getTamanho());
            }
        } catch (ImagemRepetidaException e) {
            System.out.println("Já existe uma imagem com esse nome!");
        }
    }

    private Node insercao(Imagem im, Node root) throws ImagemRepetidaException {
        if (root == null) {
            return (new Node(im));
        }
        if (im.getNome().compareToIgnoreCase(root.getKey()) == 0) {
            throw new ImagemRepetidaException();
        } else if (im.getNome().compareToIgnoreCase(root.getKey()) < 0) {
            root.setLeft(insercao(im, root.getLeft()));
        } else if (im.getNome().compareToIgnoreCase(root.getKey()) > 0) {
            root.setRight(insercao(im, root.getRight()));
        } else {
            return root;
        }
        root.setHeight(alturaMaxima(altura(root.getLeft()), altura(root.getRight())) + 1);
        return balancear(root);
    }

    public void remover(String im) {
        root = remove(im, root);
    }

    //METODO DE REMOVER
    private Node remove(String im, Node root) {
        if (root == null) {
            this.root = null;
        }
        if (im.compareToIgnoreCase(root.getKey()) < 0) {
            root.setLeft(remove(im, root.getLeft()));
        } else if (im.compareToIgnoreCase(root.getKey()) > 0) {
            root.setRight(remove(im, root.getRight()));
        } else {
            root.getConteudo().setComputador(null);
            if (root.getLeft() == null || root.getRight() == null) {
                Node auxiliar = null;
                if (root.getLeft() == null) {
                    auxiliar = root.getRight();
                    root = auxiliar;
                } else {
                    auxiliar = root.getLeft();
                    root = auxiliar;
                }
                //Se os dois lados forem nulos
                if (auxiliar == null) {
                    auxiliar = root;
                    root = null;
                    return root;
                }
            } else {
                Node auxiliar2 = this.menorChave(root.getRight());
                root.setKey(auxiliar2.getKey());
                root.setRight(remove(auxiliar2.getKey(), root.getRight()));
            }
        }
        root.setHeight(alturaMaxima(altura(root.getLeft()) + 1, altura(root.getRight())));
        return balancear(root);
    }

    public int size() {
        return quantidadeDeNós(root);
    }

    private int quantidadeDeNós(Node root) {
        if (root == null) {
            return 0;
        } else {
            int quantidade = 1;
            quantidade += quantidadeDeNós(root.getLeft());
            quantidade += quantidadeDeNós(root.getRight());
            return quantidade;
        }
    }

    public Iterator iterator() {
        IteratorTree itTree = new IteratorTree(this.root);
        return itTree;
    }

}
