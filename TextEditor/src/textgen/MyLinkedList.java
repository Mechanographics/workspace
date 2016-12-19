package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		
		//creating buffer nodes
		LLNode<E> node = new LLNode(null);
		head = node;
		LLNode<E> node2 = new LLNode(null);
		tail  = node2;
		
		head.next = tail;
		tail.prev = head;
		
		size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		LLNode<E> node = new LLNode(element);
		
		if(element == null)
			return false;
		
		if(size()==0){
			head.next = node;
			node.next = tail;
			tail.prev = node;
			node.prev = head;
			size++;
			return true;
		}
		
		else{
			node.prev = tail.prev; 
			tail.prev = node;
			node.prev.next = node;
			node.next = tail;
			size++;
			return true;
		}
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		int i=index;
		LLNode<E> temp = head;
		
		if(index > size()-1)
			throw new IndexOutOfBoundsException();
		
		else if(index == -1 || size()==0)
			throw new IndexOutOfBoundsException();
		
		else{
			while(i>=0){
				temp = temp.next;
				i--;
			}
			return temp.data;
		}
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		LLNode<E> node = new LLNode(element);
		LLNode<E> temp = head;
		
		if(index == -1 || index > size() )
			throw new IndexOutOfBoundsException();
		
		if( element == null)
			throw new NullPointerException();
			
		else if(index== size()){ //adding at the end
			LLNode<E> before = tail.prev;
			LLNode<E> after  = tail;
			
			before.next = node;
			node.next = after;
			tail.prev = node;
			node.prev = before;
			
			size++;	
		}
		
		else{
			int i = index;
			while(i>=0){
				temp = temp.next;
				i--;
			}
			
			node.prev = temp.prev;
			temp.prev= node;
			node.prev.next = node;
			node.next = temp;
			size++;	
		}
		
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		
		if(index > size()-1 || index ==-1)
			throw new IndexOutOfBoundsException();
		
		else{
			LLNode<E> temp = head;
			int i = index;
			while(i>=0){
				temp = temp.next;
				i--;
			}
			/*
			temp.prev.next = temp.next;
			temp.next.prev = temp.prev;
			*/
			LLNode<E> before = temp.prev;
			LLNode<E> after  = temp.next;
			
			before.next = after;
			after.prev = before;
			size--;
			
			if(size()==0){
				head.next = tail;
				tail.prev = head;
			}
			
			return temp.data;
		}
			

	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		
		if(index == -1 || index > size()-1 || element == null)
			throw new NullPointerException();
		
		LLNode<E> temp = head;
		int i = index;
		while(i>=0){
			temp = temp.next;
			i--;
		}
		temp.data = element;
		return temp.data;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
