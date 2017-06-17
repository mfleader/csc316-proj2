package tree;

import java.util.Iterator;

public class GeneralTree<E> implements Tree {
	
	private TreeNode root;
	

	public GeneralTree(E element) {
		root = new TreeNode(element);
	}
	
	public GeneralTree() {
		this(null);
	}

	@Override
	public TreeNode root() {
		// TODO Auto-generated method stub
		return root;
	}

	@Override
	public TreeNode parent(TreeNode tn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<TreeNode> children(TreeNode tn) {
		return tn.getNodeChildren();
	}

	@Override
	public int numChildren(TreeNode tn) {
		return tn.numChildren();
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<TreeNode> treeNodes() {
		// TODO Auto-generated method stub
		return null;
	}


	public boolean isInternal(TreeNode node) {
		return numChildren(node) > 0;
	}
	
	public boolean isExternal(TreeNode node) {
		return numChildren(node) == 0;
	}
	
	public boolean isRoot(TreeNode node) {
		return node == root();
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	




	
	

}
