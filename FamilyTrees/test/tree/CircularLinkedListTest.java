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
	public void testSize() {
		fail("Not yet implemented");
	}

	@Test
	public void testCircularLinkedList() {
		CircularLinkedList<String> list = new CircularLinkedList<String>();
	}

	@Test
	public void testListIteratorInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testAppend() {
		CircularLinkedList<Character> list = new CircularLinkedList<Character>();
		assertEquals(0, list.size());
		list.append(A);
		assertEquals(1, list.size());
		list.append(B);
		assertEquals(2, list.size());
		
		System.out.println(list.listIterator(0).next());
		System.out.println(list.listIterator(1).next());
		System.out.println(list.listIterator(2).next());
		//System.out.println(list.listIterator(3).next());
	}

	@Test
	public void testSetIntE() {
		fail("Not yet implemented");
	}

}
