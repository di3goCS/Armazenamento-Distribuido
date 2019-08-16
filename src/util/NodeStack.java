/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import model.Imagem;

/**
 *
 * @author diego
 */
public class NodeStack {
    private Imagem conteudo;
    private NodeStack next;

    public NodeStack() {
    }

    public NodeStack(Imagem conteudo) {
        this.conteudo = conteudo;
    }

    /**
     * Método que obtém o próximo Nó
     *
     * @return Node - O próximo nó
     */
    public NodeStack getNext() {
        return next;
    }

    /**
     * Método que obtém o conteúdo do Nó
     *
     * @return Object - O conteúdo
     */
    public Imagem getConteudo() {
        return conteudo;
    }

    /**
     * Método que modifica a referência do próximo, para um outro nó.
     *
     * @param newNext - O novo próximo nó
     */
    public void setNext(NodeStack newNext) {
        next = newNext;
    }

    /**
     * Método que modifica o conteúdo do nó
     *
     * @param novoConteudo - O novo conteúdo do nó
     */
    public void setConteudo(Imagem novoConteudo) {
        conteudo = novoConteudo;
    }
}
