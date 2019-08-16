package util;

public interface IStack {

    public int size();

    public boolean isEmpty();
    
    public NodeStack pop();
    
    public void push(NodeStack novo);
    
    public NodeStack peek();
}

