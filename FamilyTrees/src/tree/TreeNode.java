package tree;

import java.util.LinkedList;

public class TreeNode<E> {
	
    /** the data within the element */
	private E data;
	/** the parent TreeNode of this TreeNode */
	private TreeNode parent;
	/** a reference to the next element in the list */
	private LinkedList<TreeNode> children;

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

    }

    /**
     * Constructs a TreeNode with a null parent
     * @param data
     *              the data in this element
     */
    public TreeNode(E data) {
        this(data, null);
    }
    
    public LinkedList<TreeNode> getNodeChildren() {
    	return children;
    }
    
    public int numChildren() {
    	if (children != null) {
    		return children.size(); 
    	}
    	return 0;
    }


}