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
	private static final Character N = new Character('N');
	private static final Character[] charArry = { A, B, C, D, E, F, G, H, I, J, K };
	private static final String self = "";
	private static final String parent = " parent";
	private static final String gparent = " grandparent";
	private static final String ggparent = " great grandparent";
	private static final String child = " child";
	private static final String sib = " sibling";
	private static final String aunt = " aunt";
	private static final String gchild = " grandchild";
	private static final String niece = " niece";
	
	

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

	@Test
	public void testDistanceToAncestor() {
		GeneralTree<Character> tree = new GeneralTree<Character>(A);
		assertEquals(0, tree.distanceToAncestor(tree.root(), tree.root()));
				
		tree.insert(tree.root(), B);		
		tree.insert(tree.root(), C);		
		tree.insert(tree.root(), D);		
		TreeNode<Character> tnB = tree.root().getChildren().getFirst();
		TreeNode<Character> tnC = tree.root().getChildren().getLast();
		TreeNode<Character> tnD = tree.root().getChildren().getLast();

		assertEquals(0, tree.distanceToAncestor(tree.root(), tree.root()));
		assertEquals(1, tree.distanceToAncestor(tnB, tree.root()));
		
		
		tree.insert(tnB, E);
		TreeNode<Character> tnE = tnB.getChildren().getLast();		
		assertEquals(0, tree.distanceToAncestor(tree.root(), tree.root()));
		assertEquals(1, tree.distanceToAncestor(tnB, tree.root()));
		assertEquals(2, tree.distanceToAncestor(tnE, tree.root()));
		
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
		tree.insert(tnH, N);
		TreeNode<Character> tnN = tnH.getChildren().getLast();
		
		assertEquals(0, tree.distanceToAncestor(tree.root(), tree.root()));
		assertEquals(1, tree.distanceToAncestor(tnB, tree.root()));
		assertEquals(2, tree.distanceToAncestor(tnE, tree.root()));
		assertEquals(3, tree.distanceToAncestor(tnN, tree.root()));		
	}
	
	@Test
	public void testGetRelationshipIntInt() {
		GeneralTree<Character> tree = new GeneralTree<Character>(A);
		assertEquals(self, tree.getRelationship(0, 0));
		assertEquals(parent, tree.getRelationship(0, 1));
		assertEquals(gparent, tree.getRelationship(0, 2));
		assertEquals(ggparent, tree.getRelationship(0, 3));
		assertEquals(" (great)^2-grandparent", tree.getRelationship(0, 4));
		assertEquals(child, tree.getRelationship(1, 0));
		assertEquals(sib, tree.getRelationship(1, 1));
		assertEquals(aunt, tree.getRelationship(1, 2));
		assertEquals(" (great)^1-aunt", tree.getRelationship(1, 3));
		assertEquals(gchild, tree.getRelationship(2, 0));
		assertEquals(niece, tree.getRelationship(2, 1));
		assertEquals(" (great)^1-grandchild", tree.getRelationship(3, 0));
		assertEquals(" (great)^1-niece", tree.getRelationship(3, 1));
		assertEquals(" 1th cousin 0 times removed",  tree.getRelationship(2, 2));
		
		
		
	}
}
