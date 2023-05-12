package assignment2;
//March 20 Ver
public abstract class MyLinkedList<E> implements MyList<E> {
    protected int size = 0;
    public int getSize(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public abstract void add(E elmnt); //must fix later a little confused
    public abstract void clear();
    public abstract E remove();

}

