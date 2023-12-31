package tda;


import java.io.Serializable;
import java.util.Comparator;

public interface List<E> extends Iterable<E>, Serializable {

    boolean addFirst(E element);

    boolean addLast(E element);

    boolean removeFirst();

    boolean removeLast();
    
    boolean removeAll();

    E getFirst();

    E getLast();

    boolean insert(int index, E element);

    boolean contains(E element);

    E get(int index);

    int indexOf(E element);

    boolean isEmpty();

    E remove(int index);

    boolean remove(E element);

    E set(int index, E element);

    int size();
    
    public boolean addAll (List<E> l);
          
    public boolean contains (E element, Comparator<E> cmp);
    
    //Ordenar
    public boolean sort(Comparator<E> comparator);

}




