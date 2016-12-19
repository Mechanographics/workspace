package languageProcessing;

import java.util.LinkedList;
import java.util.List;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.trees.GrammaticalStructure;
import edu.stanford.nlp.trees.GrammaticalStructureFactory;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreebankLanguagePack;
import edu.stanford.nlp.trees.TypedDependency;

public class InitialProcessing {

	public LexicalizedParser lp;
	public String textfile;
	public LinkedList<List<TypedDependency>> typed_dependencies = new LinkedList<>();
	public String root_word = "0";
	
	DoExtraction dodo = new DoExtraction();
	
	public InitialProcessing(){
		  String parserModel = "edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz";
	      lp = LexicalizedParser.loadModel(parserModel);
	      
	}
	  
	public InitialProcessing(String filename) {	  
		  String parserModel = "edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz";
	      lp = LexicalizedParser.loadModel(parserModel);
		  
	}
	  
	public static void main(String[] args) {
		//String parserModel = "edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz";
	}
  
	public void demoDP(LexicalizedParser lp, String filename) {
 
	 TreebankLanguagePack tlp = lp.treebankLanguagePack(); // a PennTreebankLanguagePack for English
	 GrammaticalStructureFactory gsf = null;
	    if (tlp.supportsGrammaticalStructures()) {
	      gsf = tlp.grammaticalStructureFactory();
	    }
	    
	    for (List<HasWord> sentence : new DocumentPreprocessor(filename)) {
	    	Tree parse = lp.apply(sentence);
	    	System.out.println();
	    	
	    	if (gsf != null) {
	    		GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);
	    		List<TypedDependency> tdl = gs.typedDependenciesCCprocessed();
   		
	        for(TypedDependency td : tdl){
	        	String relation = td.reln().toString();
	        	if(relation.equals("root")&& root_word.equals("0")){
	        		root_word = td.dep().toString();
		        	//this.getExtractiondone();
	        		System.out.println("Root word : "+root_word);
	        	}
	        }
	    		//String depend = tdl.toString();
	    		System.out.println(tdl);
	    		typed_dependencies.add(tdl);
	    		System.out.println();
	    		this.getExtractiondone();
      }
    }
	    System.out.println("Typed Dependencies are : "+typed_dependencies);
  }
	
	public void getExtractiondone(){

		dodo.extraction_based_classification(this);
	}
	
}
