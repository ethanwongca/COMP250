package assignment2;
//March 20 Ver
public interface MyList<E> extends Iterable<E>{
    public int getSize();
    public boolean isEmpty();
    public void add(E elmnt);
    public void clear();
    public E remove(); 

}
