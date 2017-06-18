package tree;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * The LinkedQueue class models the state and behavior of a Queue
 * based on a LinkedAbstractList.
 * @author Matthew F. Leader
 * @param <E>
 * 			the parameter the class is instantiated with
 */
public class LinkedQueue<E> {
	
	/** the list of members in the queue */
	private LinkedList<E> list;
	
	/**
	 * LinkedQueue constructor
	 * @param capacity
	 * 				the maximum number of members this list can have currently
	 */
	public LinkedQueue() {
		list = new LinkedList<E>();
	}

	/**
	 * Add an element to the end of the list
	 */
	public void enqueue(E element) {
		list.add(list.size(), element);		
	}

	/**
	 * Remove an element from the front of the list
	 * @throws NoSuchElementException
	 * 				when the list is empty
	 * @return the element removed from the list
	 */
	public E dequeue() {
		if (list.isEmpty()) {
			throw new NoSuchElementException();
		}
		return list.remove(0);
	}

	/**
	 * Validates whether or not the list is empty.
	 * A list is empty if it has no members.
	 * @return true if the list is empty, false otherwise.
	 */
	public boolean isEmpty() {
		return list.isEmpty();
	}

	/**
	 * Accessor method for the number of members currently in the list
	 * @return the size of the list
	 */
	public int size() {
		return list.size();
	}



}
