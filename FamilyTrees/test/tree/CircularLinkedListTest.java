package tree;

import static org.junit.Assert.*;

import java.util.ListIterator;

import org.junit.Test;

public class CircularLinkedListTest {
	
	private static final Character A = new Character('A');
	private static final Character B = new Character('B');
	private static final Character C = new Character('C');
	private static final Character D = new Character('D');
	private static final Character E = new Character('E');
	private static final Character F = new Character('F');
	private static final Character G = new Character('G');
	private static final Character H = new Character('H');
	private static final Character I = new Character('I');
	private static final Character J = new Character('J');
	private static final Character K = new Character('K');
	private static final Character[] charArry = { A, B, C, D, E, F, G, H, I, J, K };


	@Test
	public void testCircularLinkedList() {
		CircularLinkedList<String> list = new CircularLinkedList<String>();
	}

	@Test
	public void testListIteratorInt() {
		CircularLinkedList<Character> list = new CircularLinkedList<Character>();
		assertEquals(0, list.size());
		list.append(A);
		assertEquals(1, list.size());
		list.append(B);
		assertEquals(2, list.size());
		list.append(C);
		assertEquals(3, list.size());
		
		assertEquals(A, list.listIterator(0).next());
		assertEquals(B, list.listIterator(1).next());
		assertEquals(C, list.listIterator(2).next());
		assertEquals(C, list.listIterator(0).previous());
	}

	@Test
	public void testAppend() {
		CircularLinkedList<Character> list = new CircularLinkedList<Character>();
		assertEquals(0, list.size());
		list.append(A);
		assertEquals(1, list.size());
		assertEquals(A, list.get(0));
		
		list.append(B);
		assertEquals(2, list.size());
		assertEquals(A, list.get(0));
		assertEquals(B, list.get(1));
		
		list.append(C);
		assertEquals(3, list.size());
		assertEquals(A, list.get(0));
		assertEquals(B, list.get(1));
		assertEquals(C, list.get(2));
		
	}



}
