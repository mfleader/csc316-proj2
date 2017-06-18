package tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;



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
		return root;
	}

	@Override
	public TreeNode parent(TreeNode tn) {
		return tn.getParent();
	}

	@Override
	public Iterable<TreeNode> children(TreeNode tn) {
		return tn.getChildren();
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
		ArrayList<E> list = new ArrayList<>();
		for(E element: treeNodes()) {
			list.add(element);
		}
		
		return list.iterator();
	}

	@Override
	public Iterable<E> treeNodes() {		
		return null;
	}
	
	public LinkedQueue<TreeNode> levelOrder(TreeNode tn) {
		LinkedQueue<TreeNode> queue = new LinkedQueue<TreeNode>();
		if (tn == null) {
			return queue;
		}
		queue.enqueue(root);
		while (!queue.isEmpty()) {
			TreeNode parent = queue.dequeue();
			for (TreeNode child : children(parent)) {
				queue.enqueue(child);
			}
		}
		return queue;
	}
	
	public Iterable<TreeNode> preorder() {
		List<TreeNode> snap = new ArrayList<>();
		if (!isEmpty()) {
			preorderSubtree(root(), snap);
		}
		return snap;
	}
	
	private void preorderSubtree(TreeNode parent, List<TreeNode> snap) {
		snap.add(parent);
		for (TreeNode child : children(parent)) {
			preorderSubtree(child, snap);
		}
	}
	
	public Iterable<TreeNode> postorder() {
		List<TreeNode> snap = new ArrayList<>();
		if (!isEmpty()) {
			postorderSubtree(root(), snap);
		}
		return snap;
	}
	
	private void postorderSubtree(TreeNode parent, List<TreeNode> snap) {		
		for (TreeNode child : children(parent)) {
			preorderSubtree(child, snap);
		}
		snap.add(parent);
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
