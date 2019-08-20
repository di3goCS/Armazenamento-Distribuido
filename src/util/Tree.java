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

import exception.ImagemRepetidaException;
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

    /**
     * Método que obtém a referência para a raiz da árvore
     *
     * @return Node - o primeiro nó
     */
    public Node getRootNode() {
        return root;
    }

    /**
     * Método que obtém o conteúdo da raíz da árvore
     *
     * @return Imagem - o conteúdo
     */
    public Imagem getRoot() {
        return root.getConteudo();
    }

    /**
     * Método que altera a referência da raíz da árvore
     *
     * @param root - nova raíz
     */
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

    /**
     * Método que obtém a altura de um determinado nó. Nós nulos, tem altura 0.
     *
     * @param no - nó que deseja obter a altura
     * @return int- a altura
     */
    public int altura(Node no) {
        if (no == null) { //Se o nó for nulo
            return 0;
        } else { //Se não for nulo
            return no.getHeight();
        }
    }

    /**
     * Método que compara qual das duas alturas informadas é a maior
     *
     * @param altura1 - uma das alturas da comparação
     * @param altura2 - uma das alturas da comparação
     * @return int - a maior altura
     */
    public int alturaMaxima(int altura1, int altura2) {
        if (altura1 > altura2) {
            return altura1;
        } else {
            return altura2;
        }
    }

    /**
     * Método privado que rotaciona a árvore para a esquerda, quando ela está
     * desbalanceada para a direita.
     *
     * @param root - raíz da árvore.
     * @return Node - nó que estava desbalanceado.
     */
    private Node rotacaoEsquerda(Node root) {
        Node auxiliar = root.getRight();
        root.setRight(auxiliar.getLeft());
        auxiliar.setLeft(root);
        // Atualiza a altura.
        root.setHeight(alturaMaxima(altura(root.getLeft()), altura(root.getRight())) + 1);
        auxiliar.setHeight(alturaMaxima(altura(auxiliar.getLeft()), altura(auxiliar.getRight())) + 1);

        return auxiliar;
    }

    /**
     * Método privado que rotaciona a árvore para a direita, quando ela está
     * desbalanceada para a esquerda.
     *
     * @param root - raíz da árvore.
     * @return Node - nó que estava desbalanceado.
     */
    private Node rotacaoDireita(Node root) {
        Node auxiliar = root.getLeft();
        root.setLeft(auxiliar.getRight());
        auxiliar.setRight(root);
        // Atualiza a altura.
        root.setHeight(alturaMaxima(altura(root.getLeft()), altura(root.getRight())) + 1);
        auxiliar.setHeight(alturaMaxima(altura(auxiliar.getLeft()) + 1, altura(auxiliar.getRight())) + 1);

        return auxiliar;
    }

    /**
     * Método privado que realização uma rotação direita-esquerda, quando a
     * árvore está desbalanceada.
     *
     * @param root - raíz da árvore.
     * @return Node - nó que estava desbalanceado.
     */
    private Node rotacaoDE(Node root) {
        root.setRight(rotacaoDireita(root.getRight()));
        return rotacaoEsquerda(root);
    }

    /**
     * Método privado que realização uma rotação esquerda-direita, quando a
     * árvore está desbalanceada.
     *
     * @param root - raíz da árvore.
     * @return Node - nó que estava desbalanceado.
     */
    private Node rotacaoED(Node root) {
        root.setLeft(rotacaoEsquerda(root.getLeft()));
        return rotacaoDireita(root);
    }

    /**
     * Método que retorna o fator de balanceamento do nó.
     *
     * @param root - Nó que se deseja obter o fator de balanceamento.
     * @return int - fator de balanceamento.
     */
    public int getBalanceamento(Node root) {
        if (root == null) {
            return 0;
        } else {
            return altura(root.getLeft()) - altura(root.getRight());
        }
    }

    /**
     * Método que retorna o computador em que uma determinada imagem está.
     *
     * @param nomeImagem - nome da imagem que se deseja buscar.
     * @return Computador em que a imagem está.
     */
    public Computador buscarImagem(String nomeImagem) {
        Imagem auxiliar = procurar(nomeImagem);
        return auxiliar.getComputador();
    }

    /**
     * Método que procura uma imagem no registro.
     *
     * @param key - chave/nome da imagem que se está buscando.
     * @return Imagem buscada.
     */
    public Imagem procurar(String key) {
        Node encontrado = procurarNo(this.root, key);
        if (encontrado == null) {
            return null;
        } else {
            return encontrado.getConteudo();
        }
    }

    /**
     * Método privado que busca um nó específico na árvore em que se está um
     * elemento.
     *
     * @param root - nó raíz da árvore.
     * @param key - chave do elemento através do qual se deseja buscar o nó.
     * @return Node - nó que possui a chave passada.
     */
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

    /**
     * Método de balanceamento da árvore.
     *
     * @param root - nó raíz.
     * @return Node - raíz da árvore.
     */
    public Node balancear(Node root) {
        // se o fator de balanceamento for > 1 ou < -1, a árvore deve ser balanceada.
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

    /**
     * Método para listar todas as imagens da árvore.
     *
     * @param root - nó raíz.
     * @return int - contador de quantas imagens foram listadas.
     */
    public int listar(Node root) {
        int direita = 0;
        int esquerda = 0;
        if (root != null) {
            System.out.println(" Nome da imagem: "+ root.getKey());
            // passa recursivamente pelas subárvores esquerdas e direitas.
            esquerda = listar(root.getLeft());
            direita = listar(root.getRight());
            return esquerda + direita + 1;
        }
        return esquerda + direita;
    }

    /**
     * Método para listar as imagens de uma árvore com nome e tamanho.
     *
     * @param root - nó raíz.
     * @return int - contador de quantas imagens foram listadas.
     */
    public int listarImagem(Node root) {
        int direita = 0;
        int esquerda = 0;
        if (root != null) {
            System.out.print("\n   Nome: " + root.getKey()+" /Tamanho" + root.getConteudo().getTamanho());
            esquerda = listarImagem(root.getLeft());
            direita = listarImagem(root.getRight());
            return esquerda + direita + 1; // incrementa a quantidade de imagens listadas
        }
        return esquerda + direita;
    }

    /**
     * Método para obter o objeto de menor valor na árvore.
     *
     * @return Imagem - imagem que possui a chave com menor valor.
     */
    public Imagem getMenorChave() {
        Node r = menorChave(this.root);
        return r.getConteudo();
    }

    /**
     * Método privado para obter o objeto de menor valor na árvore.
     *
     * @param root - nó raíz.
     * @return Node - nó raíz.
     */
    private Node menorChave(Node root) {
        if (root == null) {
            return null;
        }
        // Se os dois filhos forem nulos.
        if (root.getRight() == null && root.getLeft() == null) {
            return root;
        } else if (root.getLeft() != null) { // Se o filho esquerdo for nulo.
            return menorChave(root.getLeft());
        } else {
            return root;
        }
    }

    /**
     * Método para obter o objeto de maior valor na árvore.
     *
     * @return Imagem- imagem que possui a chave com maior valor.
     */
    public Imagem getMaiorChave() {
        Node im = maiorChave(this.root);
        return im.getConteudo();
    }

    /**
     * Método privado para obter o objeto de maior valor na árvore.
     *
     * @param root- nó raíz.
     * @return Node - nó raíz.
     */
    private Node maiorChave(Node root) {
        if (root == null) {
            return null;
        }
        // Se os dois filhos forem nulos
        if (root.getRight() == null && root.getLeft() == null) {
            return root;
        } else if (root.getRight() != null) { // Se o filho direito for nulo.
            return maiorChave(root.getRight());
        } else {
            return root;
        }
    }

    /**
     * Método para adicionar uma imagem à árvore.
     *
     * @param img- imagem que se deseja adicionar.
     */
    public void add(Imagem img) {
        Computador pc;
        try {
            this.root = insercao(img, this.root);
            pc = img.getComputador();
            if (pc != null) {
                // Atualiza o espaço disponível no computador.
                pc.setEspacoDisponivel(pc.getEspacoDisponivel() - img.getTamanho());
            }
        } catch (ImagemRepetidaException e) {
            System.out.println("Já existe uma imagem com esse nome!");
        }
    }

    /**
     * Método privado que adiciona a imagem no lugar adequado da árvore.
     *
     * @param im- imagem que se deseja adicionar.
     * @param root- nó raíz.
     * @return Node- nó raíz.
     * @throws ImagemRepetidaException - exceção de imagem repetida.
     */
    private Node insercao(Imagem im, Node root) throws ImagemRepetidaException {
        if (root == null) {
            return (new Node(im));
        }
        // Se essa comparação for verdadeira, já existe uma imagem com a chave.
        if (im.getNome().compareToIgnoreCase(root.getKey()) == 0) {
            throw new ImagemRepetidaException();
            // Aqui, observamos se a chave atual é maior ou menor que a do nó
            // atual, para decidir para que lado ir.
        } else if (im.getNome().compareToIgnoreCase(root.getKey()) < 0) {
            root.setLeft(insercao(im, root.getLeft()));
        } else if (im.getNome().compareToIgnoreCase(root.getKey()) > 0) {
            root.setRight(insercao(im, root.getRight()));
        } else {
            return root;
        }
        // Atualiza a altura.
        root.setHeight(alturaMaxima(altura(root.getLeft()), altura(root.getRight())) + 1);
        return balancear(root);
    }

    /**
     * Método para remover uma imagem da árvore.
     *
     * @param im- nome/chave da imagem a ser removida.
     */
    public void remover(String im) {
        root = remove(im, root);
    }

    /**
     * Método privado que busca e remove a imagem da árvore.
     *
     * @param im - imagem a ser removida.
     * @param root - nó raíz.
     * @return Node - nó raíz.
     */
    private Node remove(String im, Node root) {
        if (root == null) {
            this.root = null;
        }
        // Se o resultado da comparação for true, a chave é menor que o nó atual.
        if (im.compareToIgnoreCase(root.getKey()) < 0) {
            root.setLeft(remove(im, root.getLeft()));
            // Se o resultado da comparação for true, a chave é maior que o nó atual.
        } else if (im.compareToIgnoreCase(root.getKey()) > 0) {
            root.setRight(remove(im, root.getRight()));
            // Se entrar no else, a chave foi encontrada.
        } else {
            root.getConteudo().setComputador(null);
            // Caso o nó tenha ao menor um filho.
            if (root.getLeft() == null || root.getRight() == null) {
                Node auxiliar = null;
                // Se o esquerdo for nulo, pegamos o filho direito.
                if (root.getLeft() == null) {
                    auxiliar = root.getRight();
                    root = auxiliar;
                } else { // Senão, pega o esquerdo.
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
                // Busca o sucessor.
                Node auxiliar2 = this.menorChave(root.getRight());
                root.setKey(auxiliar2.getKey());
                root.setRight(remove(auxiliar2.getKey(), root.getRight()));
            }
        }
        root.setHeight(alturaMaxima(altura(root.getLeft()) + 1, altura(root.getRight())));
        return balancear(root);
    }

    /**
     * Método que retorna o tamanho da árvore (quantidade de nós).
     *
     * @return int - quantidade de nós da árvore.
     */
    public int size() {
        return quantidadeDeNós(root);
    }

    /**
     * Método privado que retorna o tamanho da árvore (quantidade de nós).
     *
     * @return int - quantidade de nós da árvore.
     */
    private int quantidadeDeNós(Node root) {
        if (root == null) {
            return 0;
        } else {
            int quantidade = 1;
            //chamada recursiva
            quantidade += quantidadeDeNós(root.getLeft());
            quantidade += quantidadeDeNós(root.getRight());
            return quantidade;
        }
    }

}
