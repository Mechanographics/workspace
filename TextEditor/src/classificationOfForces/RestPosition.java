package classificationOfForces;

import java.util.List;

import com.sun.javafx.webkit.InputMethodClientImpl;

import edu.stanford.nlp.trees.TypedDependency;
import languageProcessing.InitialProcessing;

public class RestPosition extends MassAndForces {
	public RestPosition(InitialProcessing ip){
		this.ip =ip;
		ip.pt =this;
		do_classification();	
	}
	
	public void do_classification(){
		String forceName = "";
		
		for(List<TypedDependency> list : ip.typed_dependencies){
			int directionFlag = 0;
			int inclination_flag=0;
			String govNumMod = "gov", depNumMod ="dep";
			String govCompound = "gov", depCompound = "dep";
			String govAmod = "gov", depAmod = "dep";
			//System.out.println("inside Rest position - for loop");
			
			int object =0, force = 0;
			
			for(TypedDependency td : list){
				print_dependency(td);
			
				if(directionFlag == 0 && !govAmod.equals("gov") && !forceName.equals(""))
					directionFlag = identifyDirection(depAmod);
				
				if(td.reln().toString().contains("nsubj") ){
					// nsubj will always have either object or force
					if(objectName.equals("0")){
						objectName = td.dep().toString().substring(0, td.dep().toString().indexOf("/"));
						if(govCompound.contains(objectName) && govNumMod.contains(objectName)){
							setObjectValues(depNumMod, depCompound);
						 }
						object =1;
					}
					
					else{
						forceName = td.dep().toString().substring(0, td.dep().toString().indexOf("/"));
						if(govCompound.contains(forceName) && govNumMod.contains(forceName)){
							if(govAmod.equals("gov"))
							current_direction = 1;
						current_force = Double.parseDouble(depNumMod.substring(0,depNumMod.indexOf("/")));			
						}
						force = 1;
					}
				}
				
				else if ((dependency_for_inclination(td.reln().toString())) &&
							(hasInclination(td.dep().toString().substring(0,  td.dep().toString().indexOf("/"))) ||	
										hasInclination(td.gov().toString().substring(0,  td.gov().toString().indexOf("/"))))) {
					//System.out.println("Inside incline if");
					inclination_flag =1;
		 		}

				else if(td.reln().toString().contains("amod") || td.reln().toString().equals("nummod")|| td.reln().toString().equals("compound") ){
					
					if(td.reln().toString().contains("amod")){
						govAmod = td.gov().toString();
						depAmod = td.dep().toString();
						continue;
					}
					else if(td.reln().toString().equals("nummod")){
						govNumMod = td.gov().toString();
						depNumMod = td.dep().toString();
						continue;
					}
					else{
						govCompound = td.gov().toString();
						depCompound = td.dep().toString();
						continue;
					}
				}
				
				else if (td.reln().toString().contains("nmod") && td.dep().toString().equals(govNumMod)){
					//System.out.println("For every nmod : ........ "+td.toString());
						if(td.gov().toString().contains(objectName)){
							setObjectValues(depNumMod, govNumMod);
							object =1;
						}

						else if(td.gov().toString().contains(ip.root_word)){
							//System.out.println(depNumMod+" number of upward forces");
							addNumberOfForces(2,depNumMod.substring(0,depNumMod.indexOf("/")));
						}
						
						else if(!forceName.equals("") && td.gov().toString().contains(forceName)){
							if(govAmod.equals("gov")){
								current_direction = 1;	
							}
							current_force = Double.parseDouble(depNumMod.substring(0,depNumMod.indexOf("/")));
							force =1;
						}
						
						
						else if(inclination_flag==1 && isInclination(govNumMod)){
							//now resolve the forces from the object hashmap and angle of inclination
							if(object ==1 && inclined_object==0){
								inclination_angle = Integer.parseInt(depNumMod.substring(0, depNumMod.indexOf("/")));
								inclined_object =1;
								object =0;
								Resolve.resolveObjectWeight(this);
							}
							else if(force==1 && inclined_object==0){

								inclined_force_angle = Integer.parseInt(depNumMod.substring(0, depNumMod.indexOf("/")));
								inclined_force_direction = current_direction;
								System.out.println("Current Direction : "+current_direction + " Inclined Force Angle "+inclined_force_angle);
								Resolve.resolveForce(this);
							}
							
							current_direction = 0;
							
						}
				}
			}
			
			//wont work if there are two forces separated by 'and'
			if(current_direction!=0 && current_force!=0){
				System.out.println("Adding forces at direction "+current_direction);
				addForceAppro(current_force, current_direction);
				current_direction=0; current_force =0;
			}	
		}
		assignValuesToObject();	
	}		
}
