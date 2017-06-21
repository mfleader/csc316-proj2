package tree;

/**
 * 
 */


import java.util.AbstractSequentialList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Models the state and behavior of a Doubly Linked List
 * @author Matthew F. Leader
 * @param <E> 
 * 			a generic element specific to this collection
 */
public class CircularLinkedList<E> extends LinkedList<E> {
	
	/** the first element in the collection */
	private ListNode front;	
	/** the last element in the collection */
	private ListNode back;	
	/** size of the list */
	private int size;
	
	/**
	 * Construct a Linked List
	 */
	public CircularLinkedList() {
		front = null;		
		back = null;	
		size = 0;
	}
	
	
	
	/**
	 * Creates an iterator to move through the linked list
	 * @return the iterator
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
		return new CircularLinkedListIterator(index);
		//return super.listIterator(index);
	}
	
	
	/**
	 * Append an element to the end of CircularLinkedList
	 */
	public void append(E element) {
		/*
		ListNode node = new ListNode(element);
		if (front == null) {
			node.next = node;
			node.prev = node;
			front = node;
			back = front;
		} else {
			node.prev = back;
			back.prev = node;
			front.prev = node;
			node.next = front;
			back = node;
		}
		*/
		if (size == 0) {
			ListNode node = new ListNode(element);
			node.next = node;
			node.prev = node;
			front = node;
			back = front;
			size++;
		} else {
			listIterator(size).add(element);
		}				
	}
	
	
	public int find(E element) {
		int index = 0;
		for (ListNode current = front; current != null; current = current.next) {			
			if (current.data.equals(element)) {
				return index;
			}			
			index++;	
		}
		/*
		for (int k = 0; k < size; k++) {
			if (listIterator(k).next().equals(element)) {
				return k;
			}			
		}*/
		return -1;
	}
	
	
	/**
	 * Adds a unique element to the CircularLinkedList
	 * @throws IllegalArgumentException
	 * 			when the element parameter is a duplicate of an element in
	 * 			the list already
	 * @return the element replaced
	 */
	@Override
	public E set(int index, E element) {
		return super.set(index, element);
	}

	/**
	 * Accessor method for the linked list's size field
	 * @return the current size of the list
	 */
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public E get(int index) {
		return listIterator(index).next();
	}
	
	/**
	 * Models the state and behavior of a Linked List Iterator
	 * @author Matthew F. Leader
	 *
	 */
	private class CircularLinkedListIterator implements ListIterator<E> {
		
		/** a reference to the next element in the list */
		private ListNode next;
		/** a reference to the the previous element in the list */
		private ListNode previous;
		/** the index of the previous ListNode */
		private int	previousIndex;
		/** the index of the next ListNode */
		private int nextIndex;
		/** the last ListNode returned by either next() or previous() */
		private ListNode lastRetrieved;
		
		/**
		 * Constructs a CircularLinkedListIterator
		 * @param index
		 * 				the position to go to in the list
		 * @throws IndexOutOfBoundsException
		 * 				when the index parameter is not within the range of the list
		 */
		public CircularLinkedListIterator(int index) {			
			if (index < 0 || index > size()) {
				throw new IndexOutOfBoundsException();
			}
			previousIndex = -1;
			nextIndex = 0;
			previous = front;
			next = front.next;
			while (index > 0) {
				previous = previous.next;
				next = previous.next;
				previousIndex++;
				nextIndex++;
				index--;
			}
			lastRetrieved = null;
		}
		
		/**
		 * Adds an element to the list
		 * @param element 
		 * 				the element to add to the list
		 * @throws NullPointerException
		 * 				when the element parameter is null
		 */
		@Override
		public void add(E element) {
			if (element == null) {
				throw new NullPointerException();
			}			
			ListNode node = new ListNode(element, previous, next);
			previous.next = node;
			next.prev = node;						
			if (size == 0) {
				front.next = node;
				back.prev = node;
			} 			
			nextIndex++;
			previousIndex++;
			size++;
			lastRetrieved = null;
		}
		
		/**
		 * Returns the element currently stored in the next field and iterates positively
		 * through the list
		 * @throws NoSuchElementException
		 * 					when the next element is null
		 * @return the next element in the list
		 */
		@Override
		public E next() {
			if (next.data == null) {
				throw new NoSuchElementException();
			}
			lastRetrieved = next;
			next = next.next;
			previous = previous.next;
			nextIndex++;
			previousIndex++;
			return lastRetrieved.data;
		}
		
		/**
		 * Returns the element currently stored in the previous field and iterates negatively
		 * through the list
		 * @throws NoSuchElementException
		 * 					when the previous element is null
		 * @return the previous element in the list
		 */
		@Override
		public E previous() {
			if (previous.data == null) {
				throw new NoSuchElementException();
			}
			lastRetrieved = previous;
			previous = previous.prev;
			next = next.prev;
			nextIndex--;
			previousIndex--;
			return lastRetrieved.data;
		}

		/**
		 * Validates whether or not there is a next element in the list
		 * @return true if there is a next element in the list, false otherwise
		 */
		@Override
		public boolean hasNext() {	
			return nextIndex < size();
		}

		/**
		 * Validates whether or not there is a previous element in the list
		 * @return true if there is a previous element in the list, false otherwise
		 */
		@Override
		public boolean hasPrevious() {
			return previousIndex > -1;
		}

		/**
		 * Accessor method for the index of the next element in the list
		 * @return the index
		 */
		@Override
		public int nextIndex() {
			if (hasNext()) {
				return nextIndex;
			}
			return size();
		}


		/**
		 * Accessor method for the index of the previous element in the list
		 * @return the index
		 */
		@Override
		public int previousIndex() {
			if (nextIndex() > 0) {
				return previousIndex;
			}
			return -1;
		}


		/**
		 * Set the data of the last element retrieved to the parameter element
		 * @param element
		 * 					the new data to set in the list
		 * @throws IllegalStateException 
		 * 					when lastRetrieved field is null
		 * @throws NullPointerException
		 * 					when the element parameter is null
		 */
		@Override
		public void set(E element) {
			if (lastRetrieved == null) {
				throw new IllegalStateException ();
			}
			if (element == null) {
				throw new NullPointerException();
			}		
			lastRetrieved.data = element;			
		}

		/**
		 * Removes an element from the list
		 * @throws IllegalStateException
		 * 					when lastRetrieved is null
		 */
		@Override
		public void remove() {
			if (lastRetrieved == null) {
				throw new IllegalStateException();
			}
			ListNode before = lastRetrieved.prev;
			ListNode after  = lastRetrieved.next;
			before.next = after;
			after.prev = before;
			if (next == lastRetrieved) {
				next = after;
			} else {
				nextIndex--;
				previousIndex--;
			}
			lastRetrieved = null;
			size--;
		}
		
	}
	
	/**
	 * The ListNode class models the state and behavior of a single element within the
	 * Linked List. It knows the data that it contains and has a reference to the next
	 * element in the list, and the previous reference element in the list.
	 * @author Matthew F. Leader
	 */
	private class ListNode {
		/** the data within the element */
		private E data;		
		/** a reference to the next element in the list */
		private ListNode next;
		/** a reference to the the previous element in the list */
		private ListNode prev;
		
		/**
		 * Constructs a ListNode
		 * @param data
		 * 			the information contained within this element
		 */
		public ListNode(E data) {
			this.data = data;
			this.next = null;
			this.prev = null;
		}
		
		/**
		 * ListNode constructor when you know what the next ListNode should be
		 * @param data
		 * 			the information contained within this element
		 * @param prev
		 * 			a reference to the the previous element in the list
		 * @param next
		 * 			a reference to the next element in the list
		 */
		public ListNode(E data, ListNode prev, ListNode next) {
			this.data = data;
			this.prev = prev;
			this.next = next;			
		}
	}

}
