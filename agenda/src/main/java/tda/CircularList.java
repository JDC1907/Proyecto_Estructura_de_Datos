package tda;


import java.io.Serializable;
import java.util.Comparator;
import java.util.ListIterator;

public interface CircularList<E> extends Iterable<E>, Serializable, List<E> {

    public boolean addAll (CircularList<E> l);
    
    public CircularList<E> findIntersection (CircularList<E> anotherList, Comparator<E> cmp);
    
    public ListIterator<E> listIterator();
    
    //Ordenar
    public boolean sort(Comparator<E> comparator);
    

}




