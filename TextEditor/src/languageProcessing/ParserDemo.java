package languageProcessing;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;

public class ParserDemo {

	public static LinkedList<String> final_tree = new LinkedList<>();
	public LexicalizedParser lp;
	public String textfile;
	public static LinkedList<String> typed_dependencies = new LinkedList<>();
	
  public static void main(String[] args) {
   String parserModel = "edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz";
  }
  
  public void demoDP(LexicalizedParser lp, String filename) {
 
	 TreebankLanguagePack tlp = lp.treebankLanguagePack(); // a PennTreebankLanguagePack for English
	 GrammaticalStructureFactory gsf = null;
	    if (tlp.supportsGrammaticalStructures()) {
	      gsf = tlp.grammaticalStructureFactory();
	    }
	    PrintWriter out;
	    for (List<HasWord> sentence : new DocumentPreprocessor(filename)) {
	    	Tree parse = lp.apply(sentence);

	    	String tree = parse.pennString();
	    	System.out.println();
	    	//System.out.println("Tree is : "+tree);
	    	final_tree.add(tree);

	    	if (gsf != null) {
	    		GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);
	    		List<TypedDependency> tdl = gs.typedDependenciesCCprocessed();
   		
        for(TypedDependency td : tdl){
        	//System.out.println("This part of the sentence is : " +td.toString());
        	
        	String relation = td.reln().toString();
        	//System.out.println("Relation : "+td.reln().toString());
        	//if(relation.equals("rest"))
        	
        
        }
       
        
	    		String depend = tdl.toString();
	    		//System.out.println(tdl);
	    		typed_dependencies.add(depend+"\n");
        
	    		System.out.println();
      }
    }
	    System.out.println("Final tree is "+final_tree);
	    System.out.println("Typed Dependencies are : "+typed_dependencies);
  }

  public ParserDemo(){
	  String parserModel = "edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz";
      lp = LexicalizedParser.loadModel(parserModel);
      
  }
  
  public ParserDemo(String filename) {	  
	  String parserModel = "edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz";
      lp = LexicalizedParser.loadModel(parserModel);
	  
  }
}
