package tda;

import java.util.Comparator;
import java.util.Iterator;

public class LinkedList<E> implements List<E> {

    private Node<E> first, last;

    public LinkedList() {
        first = last = null;
    }
    
    @Override
    public List<E> findIntersection (List<E> anotherList, Comparator<E> cmp) {
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
    public boolean contains (E element, Comparator<E> cmp) {
        for (E e1 : this) {
            if (cmp.compare(e1, element) == 0)
                return true;
        }
        return false;
    }

    
    @Override
    public boolean addFirst(E element) {
        Node<E> nodo = new Node<>(element);
        if (element == null) {
            return false;
        } else if (isEmpty()) {
            first = last = nodo;
        } else {
            nodo.setNext(first);
            first = nodo;
        }
        return true;
    }

    @Override
    public boolean addLast(E element) {
        Node<E> nodo = new Node<>(element);
        if (element == null) {
            return false;
        } else if (isEmpty()) {
            first = last = nodo;
        } else {
            last.setNext(nodo);
            last = nodo;
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
    public boolean removeFirst() {
        if (isEmpty()) {
            return false;
        } else if (first == last) {
            first = last = null;
        } else {
            Node<E> tmp = first;
            first = first.getNext();
            tmp.setNext(null);
        }
        return true;
    }

    @Override
    public boolean removeLast() {
        if (this.isEmpty()) //si está vacío no se saca el nodo
        {
            return false;
        } else if (first == last) { //si ambos son iguales solo hay un nodo en el arreglo
            first = last = null; //solo ese se remueve
        } else {

            //Iterar nodos con un while hasta antes del last
            Node<E> nodo = first;
            while (nodo.getNext() != last) {
                nodo = nodo.getNext();
            }
            last = nodo;
            last.setNext(null);
        }
        return true;
    }

    private Node<E> getPrevious(Node<E> nodo) {
        if (nodo == first) {
            return null;
        }
        for (Node<E> i = first; i != null; i = i.getNext()) {
            if (i.getNext() == nodo) {
                return i;
            }
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return (first == null && last == null);
    }

    @Override
    public boolean contains(E element) {
        if (element == null || isEmpty()) {
            return false;
        }
        for (Node<E> i = first; i != null; i = i.getNext()) {
            if (i.getContent().equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean insert(int index, E element) {
        if (element == null || index < 0 || index >= this.size()) {
            return false;
        } else if (index == 0) {
            addFirst(element);
            return true;
        } else if (index == this.size() - 1) {
            addLast(element);
            return true;
        }

        int i = 0;
        Node<E> nodo = new Node<>(element);
        for (Node<E> j = first; j != null; j = j.getNext()) {
            if (index - 1 == i) {
                nodo.setNext(j.getNext());
                j.setNext(nodo);
                return true;
            }
            i++;
        }
        return false;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= this.size()) {
            return null;
        }
        int j = 0;
        for (Node<E> i = first; i != null; i = i.getNext()) {
            if (j == index) {
                return i.getContent();
            }
            j++;
        }
        return null;
    }

    @Override
    public int indexOf(E element) {
        if (element == null) {
            return -1;
        }

        int j = 0;
        for (Node<E> i = first; i != null; i = i.getNext()) {
            if (i.getContent().equals(element)) {
                return j;
            }
            j++;
        }
        return -1;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= this.size()) {
            return null;
        } else if (index == 0) {
            E tmp = getFirst();
            removeFirst();
            return tmp;
        } else if (index == this.size() - 1) {
            E tmp = getLast();
            removeLast();
            return tmp;
        }

        Node<E> j = first.getNext();
        for (int i = 1; i != index - 1; i++) {
            j = j.getNext();
        }

        Node<E> tmp = j.getNext();
        j.setNext(tmp.getNext());
        tmp.setNext(null);
        return tmp.getContent();

    }

    @Override
    public boolean remove(E element) {
        if (element == null) {
            return false;
        } else if (first.getContent().equals(element)) {
            removeFirst();
            return true;
        } else if (last.getContent().equals(element)) {
            removeLast();
            return true;
        }

        for (Node<E> i = first; i != null; i = i.getNext()) {
            if (i.getContent().equals(element)) {
                Node<E> previo = getPrevious(i);
                previo.setNext(i.getNext());
                i.setNext(null);
                return true;
            }
        }
        return false;
    }

    @Override
    public E set(int index, E element) {
        if (element == null || index < 0 || index >= this.size()) {
            return null;
        }

        int j = 0;
        for (Node<E> i = first; i != null; i = i.getNext()) {
            if (j == index) {
                E tmp = i.getContent();
                i.setContent(element);
                return tmp;
            }
        }
        return null;
    }

    @Override
    public int size() {
        int cont = 0;
        Node n;
        for (n = this.first; n != null; n = n.getNext()) {
            cont++;
        }
        return cont;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        if (isEmpty()) {
            return "[]";
        }
        s.append("[");

        for (Node<E> p = this.first; p != null; p = p.getNext()) {
            if (p != this.last) {
                s.append(p.getContent() + ",");
            } else {
                s.append(p.getContent() + "]");
            }
        }
        return s.toString();
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {
            private Node<E> cursor = first;

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
        };

        return it;
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

}
