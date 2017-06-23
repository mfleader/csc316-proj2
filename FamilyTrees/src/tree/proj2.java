package tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This program generates a unique tree given the tree's preorder and
 * postorder traversal. It then can report on the familial relationships
 * between nodes in the tree.
 * @author Matthew F. Leader
 *
 */
public class proj2 {
	
	/** a global array of the characters tree's preorder traversal */
	private static ArrayList<Character> pretrav;
	/** a global array of the characters tree's postorder traversal */
	private static ArrayList<Character> posttrav;
	
	public static void main(String[] args) {
				
        Scanner console = new Scanner(System.in);
        Scanner input = getInputScanner(console);
        PrintStream output = getOutputPrintStream(console);
        TreeNode<Character> root = null;
        GeneralTree<Character> tree = null;
        String line;
        
        // Create ArrayLists for pretrav and posttrav
        line = input.nextLine();
		if (line.charAt(0) == '<') {
			pretrav = toArrayList(line);
		} else if (line.charAt(0) == '>') {
			posttrav = toArrayList(line);
		}
        line = input.nextLine();
		if (line.charAt(0) == '<') {
			pretrav = toArrayList(line);
		} else if (line.charAt(0) == '>') {
			posttrav = toArrayList(line);
		}
		
		// Build family tree
		root = buildTree(pretrav.size(), 0, 0);
        tree = new GeneralTree<Character>(root);            
        	
        // Print answers to questions about positions in family tree
        while (input.hasNextLine()) {
        	line = input.nextLine();        	        	
        	if (line.length() > 0 && line.charAt(0) == '?') {
        		ArrayList<Character> pair = toArrayList(line);
               	output.println(pair.get(0) + " is " + pair.get(1) + findRelationship(pair, tree));
            }        		
        }        	
        
        //Print level order
        output.println(tree.levelOrder(tree.root()) + ".");
        
        // Use input and output
        input.close();
        output.close();
        console.close();
	}
	
    /**
     * Returns a Scanner for input from a file.
     *
     * @param console Scanner for console
     * @return Scanner for input from a file
     */
    public static Scanner getInputScanner(Scanner console) {
        Scanner input = null;
        while (input == null) {
            System.out.print("input file name? ");
            String name = console.nextLine();
            try {
                input = new Scanner(new File(name));
            } catch (FileNotFoundException e) {
                System.out.println("File not found. Please try again.");
            }
        }
        return input;
    }

    /**
     * Returns a PrintStream for output to a file. NOTE: If file exists, this
     * code will overwrite the existing file. It is likely that you want to add
     * additional tests.
     *
     * @param console Scanner for console.
     * @return PrintStream for output to a file
     */
    public static PrintStream getOutputPrintStream(Scanner console) {
        PrintStream outputFile = null;
        while (outputFile == null) {
            System.out.print("output file name? ");
            String name = console.nextLine();
            try {
                outputFile = new PrintStream(new File(name));
            } catch (FileNotFoundException e) {
                System.out.println("File unable to be written. Please try again.");
            }
        }
        return outputFile;
    }


	/**
	 * Recursively build a tree given the size of the subtree, and where the subRoot
	 * is located in the pretrav and posttrav array
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
			pretrav.remove(0);
			return new TreeNode<Character>(posttrav.remove(0));
			
		} else if (size > 1) {
			// Look at subRoot of subTree, remove it from preTrav, and store as a sentinel 
			Character rootChar = pretrav.remove(0);
			TreeNode<Character> root = new TreeNode<Character>(rootChar);
			
			// Build subTrees until we see the subRoot sentinel in postTrav at index = 0
			while (posttrav.get(0) != rootChar) {				
				root.addChild(buildTree(posttrav.indexOf(pretrav.get(0)) + 1, 0, 0));				
			}
			
			// Set the parent of all root's children to the root
			for (TreeNode<Character> child : root.getChildren()) {
				child.setParent(root);
			}

			// After we see the root we no longer need it can remove it from postTrav
			posttrav.remove(0);
			
			return root;
		}		
		return null;
	}
	
	/**
	 * Converts a line of text delimited by commas into an ArrayList
	 * @param line
	 * 				a line of text
	 * @return an ArrayList of Characters
	 */
	public static ArrayList<Character> toArrayList(String line) {
		ArrayList<Character> array = new ArrayList<Character>();		
		for (int k = 0; k < line.length(); k++) {
			if (line.charAt(k) != ' '
				&& line.charAt(k) != '<'
				&& line.charAt(k) != '>'
				&& line.charAt(k) != '?'
				&& line.charAt(k) != '.'
				&& line.charAt(k) != ',') {		
				
				array.add(line.charAt(k));
			}
		}				
		return array;
	}
	
	/**
	 * Reports a String that describes the relationship between the two Characters in the
	 * given tree	
	 * @param array
	 * 				an array of the given Characters
	 * @param tree
	 * 				the tree that these Characters reside in
	 * @return
	 */
	public static String findRelationship(ArrayList<Character> array, GeneralTree<Character> tree) {
		tree.clearMarks();
		TreeNode<Character> node0 = tree.find(array.get(0));
		TreeNode<Character> node1 = tree.find(array.get(1));
		return tree.findRelationship(node0, node1);
	}
	
	
	
	
	
	
	
}
