package spelling;

import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should ignore the word's case.
	 * That is, you should convert the string to all lower case as you insert it. */
	public boolean addWord(String word)
	{
	    //TODO: Implement this method.
		TrieNode node = root;

		for (Character c : word.toLowerCase().toCharArray()) {
			TrieNode child = node.getChild(c);

			if (child != null) {
				node = child;
			} else {
				node = node.insert(c);
			}
		}

		if (node.endsWord()) {
			return false;
		}

		node.setEndsWord(true);
		++size;

		return true;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) 
	{
	    // TODO: Implement this method
		TrieNode node = root;

		for (Character c : s.toLowerCase().toCharArray()) {
			TrieNode child = node.getChild(c);
			if (child != null) {
				node = child;
			} else {
				return false;
			}
		}

		return node.endsWord();
	}

	/** 
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param text The text to use at the word stem
     * @param n The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 
    	 TrieNode node = root;

 		for (Character c : prefix.toLowerCase().toCharArray()) {
 			TrieNode child = node.getChild(c);
 			if (child != null) {
 				node = child;
 			} else {
 				return Collections.<String> emptyList();
 			}
 		}

 		List<String> completions = new LinkedList<>();
 		Queue<TrieNode> queue = new LinkedList<>();
 		queue.offer(node);

 		while (!queue.isEmpty() && numCompletions > 0) {
 			TrieNode t = queue.poll();

 			if (t.endsWord()) {
 				completions.add(t.getText());
 				--numCompletions;
 			}

 			for (Character c : t.getValidNextCharacters()) {
 				queue.offer(t.getChild(c));
 			}

 		}

 		return completions;
    	 
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}