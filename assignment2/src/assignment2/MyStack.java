package assignment2;
//March 20 Ver
public class MyStack<E> {
    private MyDoublyLinkedList<E> storage = new MyDoublyLinkedList<>();
    public MyStack(){
    } //LIFO for doubly addLast(e) and removeLast()
    public void push(E elmnt){
        storage.addLast(elmnt);
    }
    public E pop(){
        return storage.removeLast();
    }
    public E peek(){
        return storage.peekLast();
    }
    public boolean isEmpty(){
        return storage.getSize() == 0;
    }
    public void clear(){
        storage.clear();
    }
    public int getSize(){
        return storage.getSize();
    }
}
