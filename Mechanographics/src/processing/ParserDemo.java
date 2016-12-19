package processing;
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
  /**
   * The main method demonstrates the easiest way to load a parser.
   * Simply call loadModel and specify the path of a serialized grammar
   * model, which can be a file, a resource on the classpath, or even a URL.
   * For example, this demonstrates loading a grammar from the models jar
   * file, which you therefore need to include on the classpath for ParserDemo
   * to work.
   *
   */
  public static void main(String[] args) {
   String parserModel = "edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz";
  }

  /**
   * demoDP demonstrates turning a file into tokens and then parse
   * trees.  Note that the trees are printed by calling pennPrint on
   * the Tree object.  It is also possible to pass a PrintWriter to
   * pennPrint if you want to capture the output.
   * This code will work with any supported language.
   */
  public static void demoDP(LexicalizedParser lp, String filename) {
    // This option shows loading, sentence-segmenting and tokenizing
    // a file using DocumentPreprocessor.
    TreebankLanguagePack tlp = lp.treebankLanguagePack(); // a PennTreebankLanguagePack for English
    GrammaticalStructureFactory gsf = null;
    if (tlp.supportsGrammaticalStructures()) {
      gsf = tlp.grammaticalStructureFactory();
    }
	PrintWriter out;
    for (List<HasWord> sentence : new DocumentPreprocessor(filename)) {
      Tree parse = lp.apply(sentence);
   //   parse.pennPrint();
      
      String tree = parse.pennString();
      System.out.println();
      System.out.println("Tree is : "+tree);
      final_tree.add(tree);

      if (gsf != null) {
        GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);
        Collection tdl = gs.typedDependenciesCCprocessed();
        String depend = tdl.toString();
        System.out.println(tdl);
        typed_dependencies.add(depend+"\n");
        
        System.out.println();
      }
    }
    System.out.println("Final tree is "+final_tree);
    System.out.println("Typed Dependencies are : "+typed_dependencies);
  }

  /**
   * demoAPI demonstrates other ways of calling the parser with
   * already tokenized text, or in some cases, raw text that needs to
   * be tokenized as a single sentence.  Output is handled with a
   * TreePrint object.  Note that the options used when creating the
   * TreePrint can determine what results to print out.  Once again,
   * one can capture the output by passing a PrintWriter to
   * TreePrint.printTree. This code is for English.
   */

  public ParserDemo(){
	  String parserModel = "edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz";
      lp = LexicalizedParser.loadModel(parserModel);
      
  }
  public ParserDemo(String filename) {
	  
	  String parserModel = "edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz";
      lp = LexicalizedParser.loadModel(parserModel);
	  
  } // static methods only

}
