package tda;

import java.io.Serializable;


public class DoublyNode<E> implements Serializable{
    private E content;
    private DoublyNode next;
    private DoublyNode previous;
    
    public DoublyNode(E content){
        this.content = content;
        next = null;
        previous = null;
    }

    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public DoublyNode getNext() {
        return next;
    }

    public void setNext(DoublyNode next) {
        this.next = next;
    }

    public DoublyNode getPrevious() {
        return previous;
    }

    public void setPrevious(DoublyNode previous) {
        this.previous = previous;
    }
    
    
}
