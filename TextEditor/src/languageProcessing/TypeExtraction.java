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
		list.add("rests");
		list.add("sits");
		list.add("acts");
		list.add("resting");
		list.add("lying");
		list.add("suspended");
		list.add("applied");
		list.add("acting");
		list.add("sitting");
		//can rest on an inclined plane as well		
		return list;
	}
	
	public LinkedList<String> getFriction(){
		LinkedList<String> list = new LinkedList<>();
		list.add("sliding");
		list.add("slides");
		list.add("gliding");
		list.add("glides");	 list.add("coasting");
		list.add("friction"); list.add("hauling");
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
