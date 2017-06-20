package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
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
			ArrayList<Character> family = new ArrayList<Character>();
			List<Character> pretravList = Arrays.asList(pretrav);
			List<Character> posttravList = Arrays.asList(posttrav);
			
			int numChildren = 0;
			int pretravIdx = prestart + 1;
			int posttravIdxCurr = 0;
			int posttravIdxPrev = -1;
			//family.add(pretrav[prestart]);
			//family.add(pretrav[pretravIdx]);
			
			ListIterator<Character> pretravIterator = pretravList.listIterator(pretravIdx);
			ListIterator<Character> posttravIterator = posttravList.listIterator();
			Character c = pretravIterator.next();
			family.add(c);
			int familySize = 1;
			while (posttravIterator.next() != c) {
				familySize++;
			}
			
			/*
			while (pretravIterator.hasNext()) {
				ListIterator<Character> posttravIterator = posttravList.listIterator();
				while (pretravIdx < (size + prestart)) {
					posttravIdxCurr = indexOf(pretrav[pretravIdx], posttrav);
					pretravIdx += (posttravIdxCurr - posttravIdxPrev);
					family.add(pretrav[pretravIdx]);
					posttravIdxPrev = posttravIdxCurr;
					//numChildren++;
				}
			}
			*/

			for (int k = 0; k < family.size(); k++) {
				familySize = pretrav[family.get(k)] - posttrav[family.get(k - 1)];
				TreeNode<Character> n = buildTree(familySize, pretrav[family.get(k)], posttrav[family.get(k - 1)]);
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
