package assignment2;
import java.util.Iterator;
import java.util.NoSuchElementException;
//March 20 Ver
public class MyDoublyLinkedList<E> extends MyLinkedList<E> {
	private DNode head;
	private DNode tail;

	public void add(E elmnt) {
		DNode node = new DNode();
		node.element = elmnt;
		//isEmpty checks if anything is inside
		if(tail == null){
			head = node;
			tail = node;
		} else {
			tail.next = node;
			node.prev = tail;
			tail = node;
		}
		size++;
	}

	public E remove() { //Removes the element E at the back of the list
		if (this.isEmpty()){ //edge case one
			throw new NoSuchElementException("No elements in list");
		}
		if(tail == null){
			throw new NoSuchElementException("No elements in list");
		}
		E elmnt = tail.element;
		if (tail == head){ //Edge case two
			tail = null;
			head = null;
		} else {
			tail = tail.prev;
			if (tail != null) {
				tail.next = null;
			}
		}
		size --;
		return elmnt;

	}

	public void clear(){ //clears eveything from the list
		if (this.isEmpty()){
			return;
		} else {
			while(tail != null){
				this.remove(); //remove from the head
			}
			head = null;
			tail = null;
			size = 0;
		}
	}

	public void addFirst(E elmnt){
		// create a new node then move the node
		DNode node = new DNode();
		node.element = elmnt;
		if (head == null){ 
			tail = node;
			head = node;
		} else {
			node.next = head; //so the node next to the node is the head
			head.prev = node; //then the head is the new node
			head = head.prev;
		}
		size ++;
	}
	public void addLast(E elmnt){
		DNode node = new DNode();
		node.element = elmnt;
		//isEmpty checks if anything is inside
		if (tail == null) {
			head = node;
			tail = node;
		} else {
			tail.next = node;
			node.prev = tail;
			tail = node;
		}
		size++;
	}
	public E removeFirst(){
		if(this.isEmpty()){ //isEmpty check,
			throw new NoSuchElementException("Nothing inside");
		}
		if(head == null){
			throw new NoSuchElementException("No elements in list");
		}
		E elmnt = head.element; //basically the temp node
		if(tail == head){ //edgecase 2
			tail = null;
			head = null;
		} else {
			head = head.next;
			head.prev = null;
		}
		size --;
		return elmnt;
	}
	public E removeLast(){
		if (this.isEmpty()){ //edge case one
			throw new NoSuchElementException("No elements in list");
		}
		if(tail == null){
			throw new NoSuchElementException("No elements in list");
		}
		E elmnt = tail.element;
		if (tail == head){ //Edge case two
			tail = null;
			head = null;
		} else {
			tail = tail.prev;
			tail.next = null;
		}
		size --;
		return elmnt;
	}
	public E peekFirst(){
		if(this.isEmpty()){
			throw new NoSuchElementException("NOTHING INSIDE");
		}
		return head.element;
	}

	public E peekLast(){
		if(this.isEmpty()){
			throw new NoSuchElementException("NOTHING INSIDE");
		}
		return tail.element;
	}

	public boolean equals(Object obj) {
		if (obj == null){ //always check null first in equals methods
			return false;
		}
		if(!(obj instanceof MyDoublyLinkedList)){ 
			return false;
		}
		MyDoublyLinkedList<E> compareList = (MyDoublyLinkedList<E>) obj; 
		if(this.size != compareList.size){
			return false; //quicker run-time
		}

		DNode currentTwo = compareList.head;
		DNode currentOne = this.head;

		for(int i = 0; i < size; i++){
			if (!currentOne.element.equals(currentTwo.element)){
				return false;
			}
			currentOne = currentOne.next;
			currentTwo = currentTwo.next;

		}
		return true;
	}

	public Iterator<E> iterator() {
		return new DLLIterator();
	}

	private class DNode {
		private E element;
		private DNode next;
		private DNode prev;
	}

	private class DLLIterator implements Iterator<E> {
		DNode curr;

		public DLLIterator() {
			this.curr = head;
		}

		public boolean hasNext() {
			return this.curr != null;
		}

		public E next() {
			if (!this.hasNext())
				throw new NoSuchElementException();

			E element = this.curr.element;
			this.curr = this.curr.next;
			return element;
		}
	}
}
