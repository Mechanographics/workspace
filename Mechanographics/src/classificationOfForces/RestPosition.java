package classificationOfForces;

import java.util.List;

import edu.stanford.nlp.trees.TypedDependency;
import languageProcessing.InitialProcessing;

public class RestPosition extends PositionTemplate {
	public RestPosition(InitialProcessing ip){
		this.ip =ip;
		do_classification();
	}
	
	public void do_classification(){
		
		String govNumMod = "gov", depNumMod ="dep";
		for(List<TypedDependency> list : ip.typed_dependencies){

			int inclination_flag=0;
			//System.out.println("inside Rest position - for loop");
			for(TypedDependency td : list){
				System.out.print("td :gov() - "+td.gov());				
				System.out.print("td :dep() - "+td.dep());
				System.out.println("td :reln() - "+td.reln());
				
				if(td.reln().toString().contains("nsubj") && objectName.equals("0")){
					String so = td.dep().toString();
					objectName = so.substring(0, so.indexOf("/"));
				}

				else if (td.reln().toString().contains("amod") && td.dep().toString().equals("inclined/JJ")){
				inclination_flag =1;
				}
				
				else if(td.reln().toString().equals("nummod")){
					govNumMod = td.gov().toString();
					depNumMod = td.dep().toString();
					continue;
					
				}
				else if (td.reln().toString().contains("nmod") && td.dep().toString().equals(govNumMod)){
					//System.out.println("For every nmod : ........ "+td.toString());
						if(td.gov().toString().contains(objectName)){
							objectW.put(Double.parseDouble(depNumMod.substring(0,depNumMod.indexOf("/"))), govNumMod.substring(0, govNumMod.indexOf("/"))	);
							CheckUnits.actuallyCheckUnitsofObject((PositionTemplate)this);
							
							current_force = Double.parseDouble(depNumMod.substring(0,depNumMod.indexOf("/")))*9.8;
							current_direction = 1;
						}
						
						else if(td.gov().toString().contains(ip.root_word)){
							System.out.println(depNumMod+" number of downward forces");	
						}
						
						else if(isInclination(govNumMod) && inclination_flag==1){
							//System.out.println("Angle of Inclination is: "+depNumMod);
							String s[] = depNumMod.split("/");
							
							inclination_angle = Integer.parseInt(s[0]);
							System.out.println("Angle of Inclination is: "+inclination_angle);
							
							//now resolve the forces from the object hashmap and angle of inclination
							Resolve.resolveForces(this);
						}
				}
				
			}
			
			//wont work if there are two forces separated by and
			if(inclination_angle==0 && current_direction!=0){
				addForceAppro(current_force, current_direction);
				current_direction=0; current_force =0;
			}
		}
		
		assignValuesToObject();
		
	}		
	
}
