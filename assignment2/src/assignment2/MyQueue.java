package assignment2;
//March 20 Ver
public class MyQueue<E>{
    private MyDoublyLinkedList<E> storage = new MyDoublyLinkedList<E>();
    public MyQueue(){ 

    } //FIFO for doubly addLast(e) and removeFirst()
    public void enqueue(E elmnt){
        storage.addLast(elmnt);
    }
    public E dequeue(){
        return storage.removeFirst();
    }
    public boolean isEmpty(){
        return storage.getSize() == 0;
    }
    public void clear(){
        storage.clear();
    }
    public boolean equals(Object obj){
        if(obj == null){ //always check null first
            return false;
        }
        if(!(obj instanceof MyQueue)){
            return false;
        }
        MyQueue<E> other = (MyQueue<E>) obj;
        return this.storage.equals(other.storage);
    }

}
