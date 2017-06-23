package tree;

import java.util.LinkedList;

/**
 * This class represents a TreeNode position in a given tree, and has
 * functionality for a family tree.
 * @author Matthew F. Leader
 *
 * @param <E>
 * 			an element type for the TreeNode
 */
public class TreeNode<E> {
	
    /** the data within the element */
	private E data;
	/** the parent TreeNode of this TreeNode */
	private TreeNode<E> parent;
	/** a reference to the next element in the list */
	private LinkedList<TreeNode<E>> children;
	/** used to denote if this node is in the lineage of a TreeNodes */
	private int mark;

    /**
     * Constructs a Node given data and a pointer to the next element.
     * @param data
     *              the data in this element
     * @param next
     *              the pointer to the next element
     */
    public TreeNode(E data, TreeNode<E> parent) {
        this.data = data;
        this.parent = parent;
        children = new LinkedList<TreeNode<E>>();
        setMark(0);
    }

    /**
     * Constructs a TreeNode with a null parent
     * @param data
     *              the data in this element
     */
    public TreeNode(E data) {
        this(data, null);
    }
    
    /**
     * Accessor method for this TreeNode's children
     * @return the list of this TreeNode's children
     */
    public LinkedList<TreeNode<E>> getChildren() {
    	return children;
    }
    
    /**
     * Calculates the number of hops between this node and one
     * of its descendants
     * @param descendant
     * @return
     */
	public int distanceToAncestor(TreeNode<E> descendant) {
		if (this == descendant) {
			return 0;
		}
		return distanceToAncestor(descendant.parent) + 1;
	}
    
    public int numChildren() {
    	if (children != null) {
    		return children.size(); 
    	}
    	return 0;
    }
    
    public TreeNode<E> getParent() {
    	return parent;
    }
    
    public void setParent(TreeNode<E> n) {
    	this.parent = n;
    }
    
    public void addChild(TreeNode<E> n) {
    	children.addLast(n);
    }
    
    public E getData() {
    	return data;
    }
    
    public void setData(E element) {
    	this.data = element;
    }
    
    public void setMark(int mark) {
    	this.mark = mark;
    }
    
    public void clearMark() {
    	mark = 0;
    }
    
    public void incrementMark() {
    	mark++;
    }
    
    public int getMark() {
    	return mark;
    }
    
    



}