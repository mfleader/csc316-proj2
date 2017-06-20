package tree;

import java.util.LinkedList;
import java.util.Scanner;

public class proj2 {
	
	private static Character[] pretrav = null;
	private static Character[] posttrav = null;
	
	public static void main(String[] args) {
		
		
        Scanner input = new Scanner(System.in);
        String line;
        
        /*
        while (pretrav == null && posttrav == null) {
        	line = input.nextLine();
        	if (line.charAt(0) == '<') {
        		pretrav = makeCharArray(line.substring(2));
        	} else if (line.charAt(0) == '>') {
        		posttrav = makeCharArray(line.substring(2));
        	}
        }
        */
        line = input.nextLine();
        pretrav = makeCharArray(line.substring(2));
        line = input.nextLine();
        posttrav = makeCharArray(line.substring(2));
        
        TreeNode<Character> root = buildTree(pretrav.length, 0, 0);
        GeneralTree<Character> tree = new GeneralTree<Character>(root);
        
        tree.levelOrder(tree.root());
        
        while (input.hasNextLine()) {
        	line = input.nextLine();
        	if (line.charAt(0) == '?') {
        		// Answer questions about family tree
        	}
        }
		
	}


	/**
	 * 
	 * @param <E>
	 * @param size
	 * 				the number of nodes in the subtree to be built
	 * @param prestart
	 * 				the place in pretrav where the preorder traversal of this subtree begins
	 * @param poststart
	 * 				the place in posttrav where the post order traversal of this subtree begins
	 */
	public static TreeNode<Character> buildTree(int size, int prestart, int poststart) {
		if (size == 1) {
			return new TreeNode<Character>(pretrav[prestart]);
		} 		
		if (pretrav[prestart] == posttrav[size - (prestart + 1)]) {			
			TreeNode<Character> root = new TreeNode<Character>(pretrav[prestart]);
						
			int numChildren = 0;
			int pretravIdx = prestart + 1;
			int posttravIdxCurr = 0;
			int posttravIdxPrev = -1;
			while (pretravIdx < (size + prestart)) {
				posttravIdxCurr = indexOf(pretrav[pretravIdx], posttrav);
				pretravIdx += (posttravIdxCurr - posttravIdxPrev);
				posttravIdxPrev = posttravIdxCurr;
				numChildren++;
			}
			for (int k = 0; k < numChildren; k++) {
				TreeNode<Character> n = buildTree(indexOf(pretrav[prestart + 1], posttrav) + 1 - prestart, 
													prestart + 1,
													indexOf(pretrav[prestart + 1], posttrav) + 1);
				root.addChild(n);
				n.setParent(root);
			}
			return root;
		}
		
		return null;
	}
	
	public static Character[] makeCharArray(String line) {
		Character[] array = null;
		LinkedList<Character> list = new LinkedList<Character>();
		int index = 0;
		
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
	
	private static int indexOf(Character c, Character[] array) {		
		for (int k = 0; k < array.length; k++) {
			if (array[k].equals(c)) {
				return k;
			}
		}		
		return -1;
	}
	
	public static int numChildren(Character[] pretrav, Character[] posttrav) {
		int size = pretrav.length;
		int prestart = 0;
		int numChildren = 0;
		int pretravIdx = prestart + 1;
		int posttravIdxCurr = 0;
		int posttravIdxPrev = -1;
		while (pretravIdx < (size + prestart)) {
			posttravIdxCurr = indexOf(pretrav[pretravIdx], posttrav);
			pretravIdx += (posttravIdxCurr - posttravIdxPrev);
			posttravIdxPrev = posttravIdxCurr;
			numChildren++;
		}
		return numChildren;
	}
	
	
	
}
