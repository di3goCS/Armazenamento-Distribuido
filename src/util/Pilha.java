/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author diego
 */
public class Pilha implements IStack{
    
    private NodeStack head;
    private int size;
    
    @Override
    public int size() {
        if (this.isEmpty()){
            return 0;
        }
        else
            return this.size;
    }

    @Override
    public boolean isEmpty() {
        return (head == null);
    }

    @Override
    public NodeStack pop() {
        NodeStack removido = null;
        if (!this.isEmpty()){
            removido = head;
            head = head.getNext();
            this.size--;
        }
        return removido;
    }

    @Override
    public void push(NodeStack novo) {
        NodeStack temp = head;
        head = novo;
        head.setNext(temp);
        this.size++;
    }

    @Override
    public NodeStack peek() {
        return head;
    }

    
}
