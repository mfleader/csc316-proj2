package tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class GeneralTree<E> {
	
	private TreeNode root;
	

	public GeneralTree(E element) {
		root = new TreeNode(element);
	}
	
	public GeneralTree() {
		root = null;
	}

	
	public TreeNode root() {
		return root;
	}

	public TreeNode parent(TreeNode tn) {
		return tn.parent;
	}

	
	public Iterable<TreeNode> children(TreeNode tn) {
		return tn.children;
	}


	public int numChildren(TreeNode tn) {
		return tn.numChildren();
	}

	
	public int size() {
		int size = 0;
		for (TreeNode element : preorder()) {
			size++;
		}
		return size;
	}

	
	public Iterator<TreeNode> iterator() {
		ArrayList<TreeNode> list = new ArrayList<>();
		for(TreeNode element : preorder()) {
			list.add(element);
		}
		
		return list.iterator();
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
		return root == null;
	}


	public class TreeNode {
		
	    /** the data within the element */
		private E data;
		/** the parent TreeNode of this TreeNode */
		private TreeNode parent;
		/** a reference to the next element in the list */
		private LinkedList<TreeNode> children;
		/** true if an ancestor of this node */
		private boolean mark;

	    /**
	     * Constructs a Node given data and a pointer to the next element.
	     * @param data
	     *              the data in this element
	     * @param next
	     *              the pointer to the next element
	     */
	    public TreeNode(E data, TreeNode parent) {
	        this.data = data;
	        this.parent = parent;
	        children = new LinkedList<TreeNode>();
	        mark = false;
	    }

	    /**
	     * Constructs a TreeNode with a null parent
	     * @param data
	     *              the data in this element
	     */
	    public TreeNode(E data) {
	        this(data, null);
	    }
	    
	    
	    public int numChildren() {
	    	if (children != null) {
	    		return children.size(); 
	    	}
	    	return 0;
	    }
	    
	    public E getData() {
	    	return data;
	    }
	}
}
