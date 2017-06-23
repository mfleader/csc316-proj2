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
	
	public void clearMarks() {
		for(TreeNode<E> element : preorder()) {
			element.clearMark();
		}
	}
	
	public String findRelationship(TreeNode<E> node0, TreeNode<E> node1) {
		if (node0 == null) {
			throw new IllegalArgumentException(node0.getData().toString() + "is not in this family tree.");
		}
		if (node1 == null) {
			throw new IllegalArgumentException(node1.getData().toString() + "is not in this family tree.");
		}
		clearMarks();
		markAncestors(node0);
		TreeNode<E> lca = leastCommonAncestor(node1); 
		return getRelationship(distanceToAncestor(node0, lca), distanceToAncestor(node1, lca));
	}
	
	
	public void markAncestors(TreeNode<E> node) {
		if (parent(node) != null) {
			markAncestors(parent(node));
		}
		node.incrementMark();
	}
	
	public TreeNode<E> leastCommonAncestor(TreeNode<E> node) {
		if (node.getMark() == 1) {
			return node;
		}
		return leastCommonAncestor(parent(node));
	}
	
	public int distanceToAncestor(TreeNode<E> descendent, TreeNode<E> ancestor) {
		return ancestor.distanceToAncestor(descendent);
	}	
	
	public TreeNode<E> find(E e) {
		for (TreeNode<E> treeNode : preorder()) {
			if (treeNode.getData().equals(e)) {
				return treeNode;
			}
		}
		return null;
	}

	
	public String getRelationship(int nodeA, int nodeB) {
		if (nodeA == 0) {
			if (nodeB == 0) {
				return "";
			} else if (nodeB == 1) {
				return " parent";
			} else if (nodeB == 2) {
				return " grandparent";
			} else if (nodeB == 3) {
				return " great grandparent";
			} else if (nodeB > 3) {
				return " (great)^" + (nodeB - 2) + "-grandparent";
			}
		} else if (nodeA == 1) {
			if (nodeB == 0) {
				return " child";
			} else if (nodeB == 1) {
				return " sibling";
			} else if (nodeB == 2) {
				return " aunt";
			} else if (nodeB > 2) {
				return " (great)^" + (nodeB - 2) + "-aunt";
			}
		} else if (nodeA == 2) {
			if (nodeB == 0) {				
				return " grandchild";
			} else if (nodeB == 1) {
				return " niece";
			}
		} else if (nodeA > 2) {
			if (nodeB == 0) {
				return " (great)^" + (nodeA - 2) + "-grandchild";
			} else if (nodeB == 1) {
				return " (great)^" + (nodeA - 2) + "-niece";
			} 
		}				
		return " " + (Math.min(nodeA, nodeA) - 1) + "th cousin " + Math.abs(nodeA - nodeB) + " times removed.";
	}
	
	
	// LinkedQueue<TreeNode<E>>
	public String levelOrder(TreeNode<E> tn) {
		LinkedQueue<TreeNode<E>> queue = new LinkedQueue<TreeNode<E>>();
		StringBuilder levelOrder = new StringBuilder();

		queue.enqueue(tn);
		levelOrder.append(tn.getData().toString());
		while (!queue.isEmpty()) {
			TreeNode<E> parent = queue.dequeue();
			if (!isRoot(parent)) {
				levelOrder.append(", " + parent.getData().toString());
			}			
			for (TreeNode<E> child : children(parent)) {
				queue.enqueue(child);
			}
		}
		return levelOrder.toString();
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
		int result = node.numChildren();
		for (TreeNode<E> child : node.getChildren()) {
			result = Math.max(result, fanOut(child));
		}
		return result;
	}
	

	




}
