package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class proj2 {
	
	private static Character[] pretrav;
	private static Character[] posttrav;
	private static ArrayList<Character> preTrav;
	private static ArrayList<Character> postTrav;
	
	public static void main(String[] args) {
		
		
        Scanner input = new Scanner(System.in);
        String line;
        
        line = input.nextLine();
        pretrav = makeCharArray(line.substring(2));
        line = input.nextLine();
        posttrav = makeCharArray(line.substring(2));        
        preTrav = toArrayList(pretrav);
        postTrav = toArrayList(posttrav);
        
        
        TreeNode<Character> root = buildTree(pretrav.length, 0, 0);
        GeneralTree<Character> tree = new GeneralTree<Character>(root);
        
        tree.levelOrder(tree.root());
        
        Character[] pair = new Character[2];
        while (input.hasNextLine()) {
        	line = input.nextLine();        	
        	if (line.charAt(0) == '?') {
        		pair = makeCharArray(line);
        		System.out.println(pair[0] + " is " + pair[1] + findRelationship(pair, tree));
        	}
        }
	}


	/**
	 * 
	 * @param size
	 * 				the number of nodes in the subtree to be built
	 * @param prestart
	 * 				the place in pretrav where the preorder traversal of this subtree begins
	 * @param poststart
	 * 				the place in posttrav where the post order traversal of this subtree begins
	 */
	public static TreeNode<Character> buildTree(int size, int prestart, int poststart) {
		if (size == 1) {
			// if all parents are correctly removed from both Lists in the size > 1 branch, then we
			// can safely remove this child from both lists at index = 0
			preTrav.remove(0);
			return new TreeNode<Character>(postTrav.remove(0));
			
		} else if (size > 1) {
			// Look at subRoot of subTree, remove it from preTrav, and store as a sentinel 
			Character rootChar = preTrav.remove(0);
			TreeNode<Character> root = new TreeNode<Character>(rootChar);
			
			// Build subTrees until we see the subRoot sentinel in postTrav at index = 0
			while (postTrav.get(0) != rootChar) {				
				root.addChild(buildTree(postTrav.indexOf(preTrav.get(0)) + 1, 0, 0));				
			}
			
			// Set the parent of all root's children to the root
			for (TreeNode<Character> child : root.getChildren()) {
				child.setParent(root);
			}

			// After we see the root we no longer need it can remove it from postTrav
			postTrav.remove(0);
			
			return root;
		}		
		return null;
	}
	
	public static Character[] makeCharArray(String line) {
		Character[] array = null;
		LinkedList<Character> list = new LinkedList<Character>();		
		if (line.length() > 0) {
			for (int k = 0; k < line.length(); k++) {
				if (line.charAt(k) != ' '
					&& line.charAt(k) != '<'
					&& line.charAt(k) != '>'
					&& line.charAt(k) != '?'
					&& line.charAt(k) != '.'
					&& line.charAt(k) != ',') {
					
					list.addLast(line.charAt(k));
				}
			}		
			array = new Character[list.size()];
			for (int j = 0; j < list.size(); j++) {
				array[j] = list.get(j);
			}			
		}		
		return array;
	}
	
	
	public static ArrayList<Character> toArrayList(Character[] array) {
		ArrayList<Character> list = new ArrayList<Character>();
		for (int k = 0; k < array.length; k++) {			
			list.add(array[k]);			
		}		
		return list;
	}
	
	public static String findRelationship(Character[] array, GeneralTree<Character> tree) {
		tree.clearMarks();
		TreeNode<Character> node0 = tree.find(array[0]);
		TreeNode<Character> node1 = tree.find(array[1]);
		return tree.findRelationship(node0, node1);
	}
	
	
	
	
	
	
	
}
