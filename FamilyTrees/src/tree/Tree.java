package tree;

import java.util.Iterator;

public interface Tree<E> extends Iterable<E> {
	
	TreeNode root();
	TreeNode parent(TreeNode p);
	Iterable<TreeNode> children(TreeNode p);
	int numChildren(TreeNode p);
	boolean isInternal(TreeNode p);
	boolean isExternal(TreeNode p);
	boolean isRoot(TreeNode p);
	int size();
	boolean isEmpty();
	Iterator<E> iterator();
	Iterable<TreeNode> treeNodes();
}
