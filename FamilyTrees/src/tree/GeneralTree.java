package tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class represents a general tree where each node can have a
 * list of children nodes. It has extra functionality to classify
 * relationships between nodes with human family structure names.
 * @author Matthew F. Leader
 *
 * @param <E>
 * 			a generic element
 */
public class GeneralTree<E> {
	
	/** the root of this tree */
	private TreeNode<E> root;
	
	/**
	 * Constructs a GeneralTree with a given element
	 * @param element
	 */
	public GeneralTree(E element) {
		root = new TreeNode<E>(element);
	}
	
	/**
	 * Null constructor for a GeneralTree
	 */
	public GeneralTree() {
		root = null;
	}
	
	/**
	 * Constructs a GeneralTree with a TreeNode<Character>
	 * @param root
	 * 				the new root of this tree
	 */
	@SuppressWarnings("unchecked")
	public GeneralTree(TreeNode<Character> root) {
		this.root = (TreeNode<E>) root;
	}

	/**
	 * Clear the mark field of every TreeNode in this tree
	 */
	public void clearMarks() {
		for(TreeNode<E> element : preorder()) {
			element.clearMark();
		}
	}
	
	/**
	 * Finds the relationship between two nodes in this tree, if they are both
	 * in this tree
	 * @param nodeA
	 * 				the first node to look for
	 * @param nodeB
	 * 				the second node to look for
	 * @return a classification of the relationship between two nodes
	 */
	public String findRelationship(TreeNode<E> nodeA, TreeNode<E> nodeB) {
		if (nodeA == null) {
			throw new IllegalArgumentException(nodeA.getData().toString() + "is not in this family tree.");
		}
		if (nodeB == null) {
			throw new IllegalArgumentException(nodeB.getData().toString() + "is not in this family tree.");
		}
		clearMarks();
		markAncestors(nodeA);
		TreeNode<E> lca = leastCommonAncestor(nodeB); 
		return getRelationship(distanceToAncestor(nodeA, lca), distanceToAncestor(nodeB, lca));
	}
	
	/**
	 * Mark the node and all of its ancestors
	 * @param node
	 * 			the node to mark
	 */
	public void markAncestors(TreeNode<E> node) {
		if (parent(node) != null) {
			markAncestors(parent(node));
		}
		node.incrementMark();
	}
	
	/**
	 * Finds the least common ancestor between the node and another node,
	 * after the other node and it's ancestors have all been marked
	 * @param node
	 * 				the node to begin with
	 * @return the least common ancestor
	 */
	public TreeNode<E> leastCommonAncestor(TreeNode<E> node) {
		if (node.getMark() == 1) {
			return node;
		}
		return leastCommonAncestor(parent(node));
	}
	
	/**
	 * The number of hops between the descendant and its ancestor
	 * @param descendent
	 * 					a node that is further in depth than its ancestor
	 * @param ancestor
	 * 					a node that shallower in depth than its descendant
	 * @return the number of hops between the descendant and its ancestor
	 */
	public int distanceToAncestor(TreeNode<E> descendent, TreeNode<E> ancestor) {
		return ancestor.distanceToAncestor(descendent);
	}	
	
	/**
	 * Finds a TreeNode that contains the given element
	 * @param e
	 * 				a generic element of the type as the TreeNodes
	 * @return a TreeNode that contains the given element
	 */
	public TreeNode<E> find(E e) {
		for (TreeNode<E> treeNode : preorder()) {
			if (treeNode.getData().equals(e)) {
				return treeNode;
			}
		}
		return null;
	}

	/**
	 * Classifies the relationship between nodeA and nodeB to a human
	 * relatable family relationship scheme. 
	 * @param nodeA
	 * 				the distance of nodeA to its lowest common ancestor with nodeB
	 * @param nodeB
	 * 				the distance of nodeB to its lowest common ancestor with nodeA
	 * @return the classification of the relationship between nodeA and nodeB
	 */
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
		return " " + (Math.min(nodeA, nodeA) - 1) + "th cousin " + Math.abs(nodeA - nodeB) + " times removed";
	}
		
	/**
	 * Creates a String of the level order of this tree
	 * @param tn
	 * 			the root node of this tree
	 * @return a String representation of the level order of this tree
	 */
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
	
	/*
	 * For an unrelated problem that needed a general tree
	public int fanOut(TreeNode<E> node) {
		int result = node.numChildren();
		for (TreeNode<E> child : node.getChildren()) {
			result = Math.max(result, fanOut(child));
		}
		return result;
	}
	*/
	

	




}
