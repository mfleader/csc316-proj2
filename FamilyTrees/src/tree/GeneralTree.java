package tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class GeneralTree<E> {
	
	private TreeNode<E> root;
	

	public GeneralTree(E element) {
		root = new TreeNode<E>(element);
	}
	
	public GeneralTree() {
		root = null;
	}
	
	
	@SuppressWarnings("unchecked")
	public GeneralTree(TreeNode<Character> root) {
		this.root = (TreeNode<E>) root;
	}

	public void insert(TreeNode<E> tn, E element) {
		tn.getChildren().add(new TreeNode<E>(element, tn));
	}
	

	
	public TreeNode<E> root() {
		return root;
	}

	public TreeNode<E> parent(TreeNode<E> tn) {
		return tn.getParent();
	}

	
	public Iterable<TreeNode<E>> children(TreeNode<E> tn) {
		return tn.getChildren();
	}


	public int numChildren(TreeNode<E> tn) {
		return tn.numChildren();
	}

	
	public int size() {
		int size = 0;
		for (TreeNode<E> element : preorder()) {
			size++;
		}
		return size;
	}

	
	public Iterator<TreeNode<E>> iterator() {
		ArrayList<TreeNode<E>> list = new ArrayList<>();
		for(TreeNode<E> element : preorder()) {
			list.add(element);
		}
		
		return list.iterator();
	}
	
	// LinkedQueue<TreeNode<E>>
	public void levelOrder(TreeNode<E> tn) {
		LinkedQueue<TreeNode<E>> queue = new LinkedQueue<TreeNode<E>>();
		/*
		if (tn == null) {
			return queue;
		}
		*/
		queue.enqueue(tn);
		while (!queue.isEmpty()) {
			TreeNode<E> parent = queue.dequeue();
			System.out.print(parent.getData().toString() + ", ");

			for (TreeNode<E> child : children(parent)) {
				queue.enqueue(child);
			}
		}
		System.out.print(".");
	}
	
	public Iterable<TreeNode<E>> preorder() {
		List<TreeNode<E>> snap = new ArrayList<>();
		if (!isEmpty()) {
			preorderSubtree(root(), snap);
		}
		return snap;
	}
	
	private void preorderSubtree(TreeNode<E> parent, List<TreeNode<E>> snap) {
		snap.add(parent);
		for (TreeNode<E> child : children(parent)) {
			preorderSubtree(child, snap);
		}
	}
	
	public Iterable<TreeNode<E>> postorder() {
		List<TreeNode<E>> snap = new ArrayList<>();
		if (!isEmpty()) {
			postorderSubtree(root(), snap);
		}
		return snap;
	}
	
	private void postorderSubtree(TreeNode<E> parent, List<TreeNode<E>> snap) {		
		for (TreeNode<E> child : children(parent)) {
			preorderSubtree(child, snap);
		}
		snap.add(parent);
	}


	public boolean isInternal(TreeNode<E> node) {
		return numChildren(node) > 0;
	}
	
	public boolean isExternal(TreeNode<E> node) {
		return numChildren(node) == 0;
	}
	
	public boolean isRoot(TreeNode<E> node) {
		return node == root();
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public int fanOut(TreeNode<E> node) {
		return fanOut(node, 0);
	}
	
	private int fanOut(TreeNode<E> node, int fan) {
		if (numChildren(node) > fan) {
			fan = numChildren(node);
		}
		for (TreeNode<E> child : node.getChildren()) {
			fan = fanOut(child, fan);
		}
		return fan;
	}
	




}
