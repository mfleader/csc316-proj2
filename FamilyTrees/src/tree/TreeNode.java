package tree;

import java.util.LinkedList;

public class TreeNode<E> {
	
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
    
    public LinkedList<TreeNode> getChildren() {
    	return children;
    }
    
    public int numChildren() {
    	if (children != null) {
    		return children.size(); 
    	}
    	return 0;
    }
    
    public TreeNode getParent() {
    	return parent;
    }
    
    public void setParent(TreeNode n) {
    	this.parent = n;
    }
    
    public void addChild(TreeNode n) {
    	children.addLast(n);
    }
    
    public void addChildAt(int index, TreeNode n) {
    	children.add(index, n);
    }
    
    public E getData() {
    	return data;
    }
    
    public void setData(E element) {
    	this.data = element;
    }


}