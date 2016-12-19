/**
 * Dictionary interface, representing and old school word-lookup dictionary
 */
package spelling;

public interface Dictionary {

	public abstract boolean addWord(String word);

	/** Is this a word according to this dictionary? */
	public abstract boolean isWord(String s);
	
	/** Return the number of words in the dictionary */
	public abstract int size();
	
}
