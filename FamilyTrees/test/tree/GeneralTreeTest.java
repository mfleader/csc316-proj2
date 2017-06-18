package tree;

import static org.junit.Assert.*;

import org.junit.Test;

public class GeneralTreeTest {
	
	private static final Character A = new Character('A');
	private static final Character B = new Character('B');
	private static final Character C = new Character('C');
	private static final Character d = new Character('D');
	

	@Test
	public void testGeneralTreeE() {
		GeneralTree<Character> tree = new GeneralTree<Character>(A);		
		assertEquals(A, tree.root().getData());
		assertFalse(tree.isEmpty());
		assertEquals(1, tree.size());
	}

	@Test
	public void testGeneralTree() {
		GeneralTree<Character> tree = new GeneralTree<Character>();		
		assertTrue(tree.isEmpty());
		assertEquals(0, tree.size());
	}


	@Test
	public void testNumChildren() {
		fail("Not yet implemented");
	}

	@Test
	public void testSize() {
		fail("Not yet implemented");
	}

	@Test
	public void testIterator() {
		fail("Not yet implemented");
	}

	@Test
	public void testTreeNodes() {
		fail("Not yet implemented");
	}

	@Test
	public void testLevelOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testPreorder() {
		fail("Not yet implemented");
	}

	@Test
	public void testPostorder() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsInternal() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsExternal() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsRoot() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsEmpty() {
		fail("Not yet implemented");
	}

}
