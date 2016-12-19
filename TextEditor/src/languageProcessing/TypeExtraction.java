package languageProcessing;

import java.util.HashMap;
import java.util.LinkedList;

public class TypeExtraction {

	HashMap<String, LinkedList<String>> type = new HashMap<>();
	
	public TypeExtraction(){
		
		LinkedList<String> list = new LinkedList<>();
		list =  getResting();
		type.put("rest", list);
		
		list =  getFriction();
		type.put("friction", list);
		
		list =  getFreeFalling();
		type.put("free-falling", list);
	}
	
	public LinkedList<String> getResting(){
		LinkedList<String> list = new LinkedList<>();
		list.add("kept");
		list.add("rest");
		list.add("resting");
		list.add("lying");
		list.add("suspended");
		
		return list;
	}
	
	public LinkedList<String> getFriction(){
		LinkedList<String> list = new LinkedList<>();
		list.add("sliding");
		list.add("gliding");
		list.add("friction");
		list.add("friction-less");
		
		return list;
	}
	
	public LinkedList<String> getFreeFalling(){
		LinkedList<String> list = new LinkedList<>();
		list.add("free-falling");
		//list.add("falling");
		
		return list;
	}
}
