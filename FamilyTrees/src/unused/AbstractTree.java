package unused;

import tree.Tree;
import tree.TreeNode;

public abstract class AbstractTree<E> implements Tree<E> {
	
	private TreeNode root;
	
	public AbstractTree(E element) {
		root = new TreeNode(element);
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
