/**
 * 
 */
package spelling;

import java.util.HashSet;

public class DictionaryHashSet implements Dictionary 
{

    private HashSet<String> words;
	
	public DictionaryHashSet()
	{
	    words = new HashSet<String>();
	}
	
	@Override
	public boolean addWord(String word) 
	{
		return words.add(word.toLowerCase());
	}

	/** Return the number of words in the dictionary */
    @Override
	public int size()
	{
    	 return words.size();
	}
	
	/** Is this a word according to this dictionary? */
    @Override
	public boolean isWord(String s) {
    	return words.contains(s.toLowerCase());
	}
	
   
}
