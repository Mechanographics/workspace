package languageProcessing;

import classificationOfForces.RestPosition;

public class DoExtraction {

	public void extraction_based_classification(InitialProcessing ip){
		
		String so[] = ip.root_word.split("/");
		ip.root_word = so[0];
		TypeExtraction te = new TypeExtraction();
		
    	if(te.type.get("rest").contains(ip.root_word)){
    		System.out.println("Belongs to the rest-type");
    		RestPosition rp = new RestPosition(ip);
    	}
    	
    	else if(te.type.get("free-falling").contains(ip.root_word)){
    		System.out.println("Belongs to the free-falling-type");
    	}
	}
	
}
