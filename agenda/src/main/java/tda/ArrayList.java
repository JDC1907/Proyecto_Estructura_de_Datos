package tda;

import java.util.Comparator;
import java.util.Iterator;

public class ArrayList<E> implements List<E> {

    private E[] elements;
    private int CAPACITY = 10;
    private int effectiveSize;

    public ArrayList() { //Todas las TDA van a inicializar vacias
        //No se puede hacer new de E directamente, por ello tienes que hacer el casting 
        elements = (E[]) new Object[CAPACITY];
        effectiveSize = 0;
    }

    @Override
    public boolean addFirst(E element) {
        if (element == null) {
            return false;
        } else if (isEmpty()) {
            elements[effectiveSize++] = element;
            return true;
        } else if (CAPACITY == effectiveSize) {
            addCapacity();
        }

        for (int i = effectiveSize - 1; i >= 0; i--) {
            elements[i + 1] = elements[i];
        }

        elements[0] = element;
        effectiveSize++;
        return true;
    }

    @Override
    public boolean insert(int index, E element) {
        if (element == null || index >= effectiveSize) {
            return false;
        } else if (effectiveSize == CAPACITY) {
            addCapacity();
        }

        for (int i = effectiveSize; i > index; i--) {
            elements[i] = elements[i - 1];
        }

        elements[index] = element;
        effectiveSize++;
        return true;
    }

    @Override
    public boolean addLast(E element) {
        if (element == null) {
            return false;
        } else if (effectiveSize == CAPACITY) {
            addCapacity();
        }

        elements[effectiveSize++] = element;
        return true;
    }

    private void addCapacity() {
        E[] tmp = (E[]) new Object[CAPACITY * 2];
        for (int i = 0; i < CAPACITY; i++) {
            tmp[i] = elements[i];
        }
        elements = tmp;
        CAPACITY = CAPACITY * 2;
    }

    @Override
    public E getFirst() {
        return elements[0];
    }

    @Override
    public E getLast() {
        return elements[effectiveSize - 1];
    }

    @Override
    public boolean removeFirst() {
        if (isEmpty()) {
            return false;
        }
        effectiveSize--;
        for (int i = 0; i < effectiveSize; i++) {
            elements[i] = elements[i + 1];
        }

        elements[effectiveSize] = null;
        return true;
    }

    @Override
    public boolean removeLast() {
        if (isEmpty()) {
            return false;
        }

        elements[--effectiveSize] = null;
        return true;
    }

    @Override
    public boolean isEmpty() {
        return effectiveSize == 0;
    }

    @Override
    public boolean contains(E element) {
        if (element == null || isEmpty()) {
            return false;
        }

        for (int i = 0; i < effectiveSize; i++) {
            if (elements[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[");

        for (int i = 0; i < effectiveSize; i++) {
            if (i != effectiveSize - 1) {
                s.append(elements[i] + ", ");
            } else {
                s.append(elements[i]);
            }
        }

        s.append("]");
        return s.toString();
    }

    public List<E> slicing(int start, int end) {
        List<E> lista = new ArrayList<>();
        if (start >= end || end > effectiveSize) {
            return lista;
        }

        for (int i = start; i < end; i++) {
            lista.addLast(elements[i]);
        }

        return lista;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof ArrayList)) {
            return false;
        }

        ArrayList<E> other = (ArrayList<E>) o;

        if (this.effectiveSize != other.effectiveSize) {
            return false;
        }

        for (int i = 0; i < effectiveSize; i++) {
            if (!this.elements[i].equals(other.elements[i])) {
                return false;
            }
        }

        return true;
    }

    @Override
    public E get(int index) {
        if (effectiveSize == 0 || index < 0 || index >= effectiveSize) {
            return null;
        }
        return elements[index];
    }

    @Override
    public int indexOf(E element) {
        if (element == null) {
            return -1;
        }

        for (int i = 0; i < effectiveSize; i++) {
            if (elements[i].equals(element)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public E remove(int index) {
        if (effectiveSize == 0 || index < 0 || index >= effectiveSize) {
            return null;
        }
        E element = elements[index];
        for (int i = index; i < effectiveSize - 1; i++) {
            elements[index] = elements[index + 1];
        }
        elements[effectiveSize - 1] = null;
        effectiveSize--;
        return element;
    }

    @Override
    public boolean remove(E element) {
        if (element == null) {
            return false;
        }

        for (int i = 0; i < effectiveSize; i++) {
            if (elements[i].equals(element)) {
                remove(i);
                return true;
            }
        }

        return false;
    }

    @Override
    public E set(int index, E element) {
        if (element == null || index < 0 || index >= effectiveSize) {
            return null;
        }

        E i = elements[index];
        elements[index] = element;
        return i;
    }

    @Override
    public int size() {
        return effectiveSize;
    }
    
    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {
            int cursor = 0;
            @Override
            public boolean hasNext() {
                return cursor < effectiveSize;
            }

            @Override
            public E next() {
                E element = elements[cursor];
                cursor++;
                return element;
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

}
