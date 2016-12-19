package spelling;

import java.util.LinkedList;

public class DictionaryLL implements Dictionary 
{

	private LinkedList<String> dict;
	
    // TODO: Add a constructor
	public DictionaryLL(){
		dict = new LinkedList<>();
	}

    public boolean addWord(String word) {
    	// TODO: Implement this method
    	word=  word.toLowerCase();
    	
    	if(dict.contains(word))
    		return false;
    	else{
    		dict.add(word);
    		return true;
    	}
    }


    /** Return the number of words in the dictionary */
    public int size()
    {
        // TODO: Implement this method
        return dict.size();
    }

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
        //TODO: Implement this method
    	
    	s = s.toLowerCase();
    	if(dict.contains(s))
    		return true;
    	else
        return false;
    }

    
}
