package unused;

import java.util.Iterator;

import tree.TreeNode;

public interface Tree<E> extends Iterable<E> {
		
	TreeNode root();
	TreeNode parent(TreeNode p);
	int numChildren(TreeNode p);
	Iterable<TreeNode> children(TreeNode tn);
	Iterable<TreeNode> treeNodes(); /* iterate through nodes */
	Iterator<E> iterator(); /* iterate through data objects */
	int size();
	boolean isEmpty();
	boolean isInternal(TreeNode tn);
	boolean isExternal(TreeNode tn);
	boolean isRoot(TreeNode tn);
}
