package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		initialize(generator);
		
	}
	
	public void initialize(Random generator){
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	/** Train the generator by adding the sourceText */
	
	public List<String> getWords(String sourceText){
		
		List<String> words = new LinkedList<>();
		
		Pattern split = Pattern.compile("[a-zA-Z']+");
		Matcher mat = split.matcher(sourceText);
		
		while(mat.find())
			words.add(mat.group());
		
		return words;
		
	}
	
	public int findIndexInWordslist(String word){
		
		int index = -1;
		for(int i=0;i<wordList.size();i++){
			if(wordList.get(i).getWord().equals(word)){
				index = i;
				break;
			}
		}
		
		return index;
		
	}
	
	@Override
	public void train(String sourceText)
	{		
		// TODO: Implement this method
		if(sourceText.equals(""))
			return;
		
		
		List<String> words = getWords(sourceText);
		starter = words.get(0);
		
		
		if(!words.isEmpty()){
		ListNode ln = new ListNode(starter);
		String prevWord = starter;
		
			for(int i=1;i<words.size();i++){
				
					if(findIndexInWordslist(prevWord)!=-1){
						 int index = findIndexInWordslist(prevWord);
						 wordList.get(index).addNextWord(words.get(i));
					}
				
				else{
					ListNode ln1 = new ListNode(prevWord);
					ln1.addNextWord(words.get(i));
					wordList.add(ln1);
				}
				prevWord = words.get(i);
				
			}//for closes
			
			//adding the starter element to the last word
			
			String lastword = words.get(words.size()-1);
			int index = findIndexInWordslist(lastword);
			if(index == -1)
			{
				ListNode ln1 = new ListNode(lastword);
				ln1.addNextWord(starter);
				wordList.add(ln1);
			}
			else
				wordList.get(index).addNextWord(starter);
			
		}//outer if closes
		
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
	    // TODO: Implement this method
		if(starter.equals(""))
			return "";
		
		String currWord = starter;
		String output = "";
		
		while(numWords>0){
			output+=currWord + " ";
			numWords--;
			
			int indexInWordslist  = findIndexInWordslist(currWord);
			ListNode currNode = wordList.get(indexInWordslist);
			
			currWord = currNode.getRandomNextWord(rnGenerator);
			
			
		}
		
		return output;
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		// TODO: Implement this method.
		initialize(rnGenerator);
		if(sourceText.equals(""))
			return;
		else
			train(sourceText);
	}
	
	// TODO: Add any private helper methods you need here.
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
	
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class
		
		int random =  generator.nextInt() %(nextWords.size());
		random = random <0 ? random*(-1) : random;
		String randomString  = nextWords.get(random);
	    return randomString;
	}
	
	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


