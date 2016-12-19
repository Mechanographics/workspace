package classificationOfForces;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import edu.stanford.nlp.trees.TypedDependency;
import languageProcessing.InitialProcessing;

public class PositionTemplate {
	
	public String objectName = "0";
	public HashMap<Double,String> objectW = new HashMap<Double,String>();
	public InitialProcessing ip;
	
	public LinkedList<Double> upForce = new LinkedList<>();
	public LinkedList<Double> downForce = new LinkedList<>();
	public LinkedList<Double> leftForce = new LinkedList<>();
	public LinkedList<Double> rightForce = new LinkedList<>();
	
	public void assignValuesToObject(){
		String govNumMod = "gov", depNumMod ="dep";
		for(List<TypedDependency> list : ip.typed_dependencies){

			System.out.println("inside Rest position - for loop");
			for(TypedDependency td : list){
				System.out.print("td :gov()"+td.gov());				
				System.out.print("td :dep()"+td.dep());
				System.out.println("td :reln()"+td.reln());
				if(td.reln().toString().contains("nsubj") && objectName.equals("0")){
					String so = td.dep().toString();
					objectName = so.substring(0, so.indexOf("/"));
				}
				else if(td.reln().toString().equals("nummod")){
					govNumMod = td.gov().toString();
					depNumMod = td.dep().toString();
					continue;
				}
				else if (td.reln().toString().contains("nmod") && td.dep().toString().equals(govNumMod)){
						if(td.gov().toString().contains(objectName))
							objectW.put(Double.parseDouble(depNumMod.substring(0,depNumMod.indexOf("/"))), govNumMod.substring(0, govNumMod.indexOf("/"))	);

						else if(td.gov().toString().contains(ip.root_word)){
							System.out.println(depNumMod+" number of downward forces");	
						}
				}
			}
		}
			CheckUnits units = new CheckUnits((PositionTemplate)this);
			System.out.println("Object is " +objectName + " and it weighs " +objectW.toString());
			
	}	
}
