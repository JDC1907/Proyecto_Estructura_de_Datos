
package tda;

import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;

public class DoublyLinkedList<E> implements CircularList<E>{
    private DoublyNode<E> first, last;
    private int lenght;

    public DoublyLinkedList() {
        this.first = null;
        this.last = null;
        lenght = 0;
    }

    @Override
    public boolean addFirst(E element) {
        DoublyNode nuevoNodo = new DoublyNode(element);
        if(element == null){
            return false;
        }
        if(first == null && last == null){
            first = nuevoNodo;
            last = first;
        }else{
            nuevoNodo.setNext(first);
            nuevoNodo.setPrevious(last);
            first = nuevoNodo;
            last.setNext(first);
        }
        lenght++;
        return true;
    }

    @Override
    public boolean addLast(E element) {
        DoublyNode nuevoNodo = new DoublyNode(element);
        if(element == null){
            return false;
        }
        if(first == null && last == null){
            last = nuevoNodo;
            first = last;
        }else{
            first.setPrevious(nuevoNodo);
            nuevoNodo.setNext(first);
            nuevoNodo.setPrevious(last);
            last.setNext(nuevoNodo);
            last = nuevoNodo;
        }
        lenght++;
        return true;
    }

    @Override
    public boolean removeFirst() {
        if(first == null && last == null){
            return false;
        }
        if(first != last){
        first = first.getNext();
        first.setPrevious(last);
        last.setNext(first);}
        else{
            first = null;
            last = null;
        }
        lenght--;
        return true;
    }

    @Override
    public boolean removeLast() {
        if(isEmpty()){
            return false;
        }else if(first == last){
            first = null;
            last = null;
        }else{
            DoublyNode<E> nodo = first;
            while (nodo.getNext() != last) {
                nodo = nodo.getNext();
            }
            last = nodo;
            last.setNext(first);
            first.setPrevious(last);
        }
        return true;
    }

    @Override
    public E getFirst() {
        return first.getContent();
    }

    @Override
    public E getLast() {
        return last.getContent();
    }

    @Override
    public boolean insert(int index, E element) {
        if (index>=size() || index<0 || element == null){
            return false;
        }
        if(index == 0){
            this.addFirst(element);
            return true;
        }
        if(index==size()-1){
            this.addLast(element);
            return true;
        }
        
        DoublyNode<E> newNode = new DoublyNode<E>(element);
        int indice = 0;
        boolean insertado = false;
        DoublyNode current = first;
        while(insertado){
            if(indice == index){
                newNode.setPrevious(current.getPrevious());
                current.getPrevious().setNext(newNode);
                
                newNode.setNext(current);
                current.setPrevious(newNode);
            }
            current = current.getNext();
            indice++;
        }
        return true;
    }

    @Override
    public boolean contains(E element) {
        boolean hallo = false;
        boolean fin = false;
        DoublyNode<E> current = first;
        if(!isEmpty() && element != null){
            while(!fin){
                if(current != last){
                    current = current.getNext();
                }else{
                    fin = true;
                }
                
                if(current.getContent().equals(element)){
                    fin = true;
                    hallo = false;
                }
            }
        }
        return hallo;
    }

    @Override
    public E get(int index) {
        int i = 0;
        for(E element: this){
            if(i==index){
                return element;
            }
            i++;
        }
        return null;
    }

    @Override
    public int indexOf(E element) {
        int i = 0;
        for(E e: this){
            if(e.equals(element))
                return i;
            i++;
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return first == null && last == null;
    }

    @Override
    public E remove(int index) {
        E elemento = this.get(index);
        if (remove(elemento )){
            return elemento;
        }
        return null;
    }

    @Override
    public boolean remove(E element) {
        DoublyNode<E> current = first;
        boolean fin = false;
        if(!isEmpty() && element != null){
            if (this.indexOf(element) == 0){
                    return this.removeFirst();
            }else if (this.indexOf(element) == this.size()-1){
                    return this.removeLast();
            }
            else{
                while(!fin){
                    if(current.getContent().equals(element)){
                        current.getPrevious().setNext(current.getNext());
                        current.getNext().setPrevious(current.getPrevious());
                        return true;
                    }
                    
                    if(current == last){
                        fin = true;
                    }

                    current = current.getNext();
                }
            }
        }
        return false;
    }

    @Override
    public E set(int index, E element) {
        E e = this.get(index);
        int i = 0;
        DoublyNode current = first;
        boolean fin = false;
        while(!fin){
            if(i==index){
                current.setContent(element);
                return e;
            }
            if(current == last){
                fin = true;
            }
            current = current.getNext();
            i++;
        }
        return null;
        
        
    }

    @Override
    public int size() {
        return this.lenght;
    }

    @Override
    public boolean addAll(CircularList<E> l) {
        if (l == null) {
            return false;
        }
        for (int i = 0; i < l.size(); i++) {
            this.addLast(l.get(i));
        }
        return true;
    }
    
    @Override
    public boolean addAll(List<E> l) {
        if (l == null) {
            return false;
        }
        for (int i = 0; i < l.size(); i++) {
            this.addLast(l.get(i));
        }
        return true;
    }

    @Override
    public List findIntersection(List<E> anotherList, Comparator<E> cmp) {
        List<E> results = new LinkedList<>();
        for (E e1 : this) {
            for (E e2 : anotherList) {
                if (cmp.compare(e1,e2) == 0) { // e1 es igual a e2
                    results.addLast(e2);
                }
            }
        }
        return results;
    }
    
    @Override
    public CircularList findIntersection(CircularList<E> anotherList, Comparator<E> cmp) {
        CircularList<E> results = new DoublyLinkedList<>();
        for (E e1 : this) {
            for (E e2 : anotherList) {
                if (cmp.compare(e1,e2) == 0) { // e1 es igual a e2
                    results.addLast(e2);
                }
            }
        }
        return results;
    }

    @Override
    public boolean contains(E element, Comparator<E> cmp) {
        for(E e: this){
            if (cmp.compare(e,element) == 0){
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator iterator(){
        Iterator<E> it = new Iterator<E>(){
            private DoublyNode<E> cursor = first;
            private boolean llegoFin = false;
            @Override
            public boolean hasNext() {
                
                return cursor != null && !llegoFin;
            }

            @Override
            public E next() {
                E tmp = cursor.getContent();
                cursor = cursor.getNext();
                llegoFin = (cursor == last.getNext());
                return tmp;
            }
        };
        return it;
    }
    @Override
    public ListIterator listIterator() {
        ListIterator<E> it = new ListIterator<E>() {
            private DoublyNode<E> cursor = first;

            @Override
            public boolean hasNext() {
                return cursor != null;
            }

            @Override
            public E next() {
                E tmp = cursor.getContent();
                cursor = cursor.getNext();
                return tmp;
            }
            @Override
            public E previous() {
                E tmp = cursor.getContent();
                cursor = cursor.getPrevious();
                return tmp;
            }
            @Override
            public boolean hasPrevious(){
                return cursor.getPrevious() != null;
            }

            @Override
            public int nextIndex() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public int previousIndex() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void set(E e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void add(E e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        };

        return it;
    }
    
    @Override
    public String toString(){
        boolean isFinal = false;
        String salida = "";
        DoublyNode<E> current = first;
        if(!isEmpty()){
            while(!isFinal){
                salida += current.getContent() + ", ";
                if(current != last){
                    current = current.getNext();
                }else{
                    isFinal = true;
                }
            }
        }
        return salida.substring(0, salida.length()-2);
    }   
}
