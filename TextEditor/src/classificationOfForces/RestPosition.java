package classificationOfForces;

import languageProcessing.InitialProcessing;

//import java.util.List;

import edu.stanford.nlp.trees.TypedDependency;

public class RestPosition extends PositionTemplate {
	public RestPosition(InitialProcessing ip){
		this.ip =ip;
		System.out.println("inside Rest position");
		do_classification();	
	}
	
	public void do_classification(){
		assignValuesToObject();
		
	}		
		
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
