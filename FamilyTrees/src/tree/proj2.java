package tree;

import java.util.Scanner;

public class proj2 {
	
	private char[] pretrav;
	private char[] posttrav;
	
	public static void main(String[] args) {
		
		
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
        	String line = input.nextLine();
        	if (line.charAt(0) == '<') {
        		
        	} else if (line.charAt(0) == '>') {
        		
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
	
	public static char[] makePretrav(String line) {
		char[] array = null;
		int index = 0;
		
		if (line.length() > 0) {
			array = new char[line.length()];
			for (int k = 0; k < line.length(); k++) {
				if (line.charAt(k) != ' '
					&& line.charAt(k) != '<'
					&& line.charAt(k) != '>'
					&& line.charAt(k) != '?'
					&& line.charAt(k) != '.'
					&& line.charAt(k) != ',') {
					
					array[index++] = line.charAt(k);
				}
			}
		}
		
		return array;
	}
	
}
