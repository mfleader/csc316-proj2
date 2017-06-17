package tree;

import java.util.LinkedList;
import java.util.Scanner;

public class proj2 {
	
	private static char[] pretrav;
	private static char[] posttrav;
	
	public static void main(String[] args) {
		
		
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
        	String line = input.nextLine();
        	if (line.charAt(0) == '<') {
        		pretrav = makeCharArray(line.substring(2));
        	} else if (line.charAt(0) == '>') {
        		posttrav = makeCharArray(line.substring(2));
        	} else if (line.charAt(0) == '?') {
        		
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
	public static void buildTree(int size, int prestart, int poststart) {
		
	}
	
	public static char[] makeCharArray(String line) {
		char[] array = null;
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
			
			array = new char[list.size()];
			for (int j = 0; j < list.size(); j++) {
				array[j] = list.get(j).charValue();
			}
			
		}
		
		return array;
	}
	
}
