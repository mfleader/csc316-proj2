package tree;

import static org.junit.Assert.*;

import org.junit.Test;

public class GeneralTreeTest {
	
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
		GeneralTree<Character> tree = new GeneralTree<Character>(A);
		tree.insert(tree.root(), B);
		assertEquals(1, tree.root().numChildren());
		tree.insert(tree.root(), C);
		assertEquals(2, tree.root().numChildren());
		tree.insert(tree.root(), D);
		assertEquals(3, tree.root().numChildren());
	}
	

	@Test
	public void testLevelOrder() {
		GeneralTree<Character> tree = new GeneralTree<Character>(A);
		
		
		tree.insert(tree.root(), B);
		
		tree.insert(tree.root(), C);
		
		//tree.levelOrder(tree.root());
		//System.out.println();
		//System.out.println("fanOut = " + tree.fanOut(tree.root()));
		
		tree.insert(tree.root(), D);
		TreeNode<Character> tnB = tree.root().getChildren().getFirst();
		TreeNode<Character> tnC = tree.root().getChildren().getLast();
		TreeNode<Character> tnD = tree.root().getChildren().getLast();
		
		tree.insert(tnB, E);
		TreeNode<Character> tnE = tnB.getChildren().getLast();
		
		tree.insert(tnB, F);
		TreeNode<Character> tnF = tnB.getChildren().getLast();
		
		tree.insert(tnF, I);
		TreeNode<Character> tnI = tnF.getChildren().getLast();
		tree.insert(tnF, J);
		TreeNode<Character> tnJ = tnF.getChildren().getLast();
		tree.insert(tnF, K);
		TreeNode<Character> tnK = tnF.getChildren().getLast();
		
		
		tree.insert(tnC, G);
		TreeNode<Character> tnG = tnC.getChildren().getLast();
		tree.insert(tnC, H);
		TreeNode<Character> tnH = tnC.getChildren().getLast();
		
		

		//tree.levelOrder(tree.root());
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
		GeneralTree<Character> tree = new GeneralTree<Character>(A);
		assertFalse(tree.isInternal(tree.root()));
		tree.insert(tree.root(), B);
		assertTrue(tree.isInternal(tree.root()));		
	}

	@Test
	public void testIsExternal() {
		GeneralTree<Character> tree = new GeneralTree<Character>(A);
		assertTrue(tree.isExternal(tree.root()));
		tree.insert(tree.root(), B);
		assertFalse(tree.isExternal(tree.root()));	
	}

	@Test
	public void testIsRoot() {
		GeneralTree<Character> tree = new GeneralTree<Character>(A);
		assertTrue(tree.isRoot(tree.root()));
		tree.insert(tree.root(), B);		
		assertFalse(tree.isRoot(tree.root().getChildren().getFirst()));
	}
	
	@Test
	public void testMarkAncestors() {
		GeneralTree<Character> tree = new GeneralTree<Character>(A);
		
		tree.insert(tree.root(), B);		
		tree.insert(tree.root(), C);		
		tree.insert(tree.root(), D);		
		TreeNode<Character> tnB = tree.root().getChildren().getFirst();
		TreeNode<Character> tnC = tree.root().getChildren().getLast();
		TreeNode<Character> tnD = tree.root().getChildren().getLast();
		tree.markAncestors(tnB);
		assertEquals(0, tnC.getMark());
		assertEquals(0, tnD.getMark());
		assertEquals(1, tnB.getMark());
		assertEquals(1, tree.root().getMark());
		tree.clearMarks();
		
		
		tree.insert(tnB, E);
		TreeNode<Character> tnE = tnB.getChildren().getLast();
		tree.markAncestors(tnE);
		assertEquals(0, tnC.getMark());
		assertEquals(0, tnD.getMark());
		assertEquals(1, tnE.getMark());
		assertEquals(1, tnB.getMark());
		assertEquals(1, tree.root().getMark());
		tree.clearMarks();
		
		tree.insert(tnB, F);
		TreeNode<Character> tnF = tnB.getChildren().getLast();
		
		tree.insert(tnF, I);
		TreeNode<Character> tnI = tnF.getChildren().getLast();
		tree.insert(tnF, J);
		TreeNode<Character> tnJ = tnF.getChildren().getLast();
		tree.insert(tnF, K);
		TreeNode<Character> tnK = tnF.getChildren().getLast();
				
		tree.insert(tnC, G);
		TreeNode<Character> tnG = tnC.getChildren().getLast();
		tree.insert(tnC, H);
		TreeNode<Character> tnH = tnC.getChildren().getLast();
	}
	
	@Test
	public void testLeastCommonAncestorTreeNode() {
		GeneralTree<Character> tree = new GeneralTree<Character>(A);
		
		tree.insert(tree.root(), B);		
		tree.insert(tree.root(), C);		
		tree.insert(tree.root(), D);		
		TreeNode<Character> tnB = tree.root().getChildren().getFirst();
		TreeNode<Character> tnC = tree.root().getChildren().getLast();
		TreeNode<Character> tnD = tree.root().getChildren().getLast();
		tree.markAncestors(tnB);
		assertEquals(tree.root(), tree.leastCommonAncestor(tnC));
		tree.clearMarks();
		
		
		tree.insert(tnB, E);
		TreeNode<Character> tnE = tnB.getChildren().getLast();
		

		tree.insert(tnB, F);
		TreeNode<Character> tnF = tnB.getChildren().getLast();
		tree.markAncestors(tnF);
		assertEquals(1, tnB.getMark());
		assertEquals(1, tree.root().getMark());
		assertEquals(tree.root(), tree.leastCommonAncestor(tnC));
		
		tree.insert(tnF, I);
		TreeNode<Character> tnI = tnF.getChildren().getLast();
		tree.insert(tnF, J);
		TreeNode<Character> tnJ = tnF.getChildren().getLast();
		tree.insert(tnF, K);
		TreeNode<Character> tnK = tnF.getChildren().getLast();
				
		tree.insert(tnC, G);
		TreeNode<Character> tnG = tnC.getChildren().getLast();
		tree.insert(tnC, H);
		TreeNode<Character> tnH = tnC.getChildren().getLast();
	}


}
