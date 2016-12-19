package classificationOfForces;

import java.util.List;
import edu.stanford.nlp.trees.TypedDependency;
import languageProcessing.InitialProcessing;

public class RestPosition extends PositionTemplate {
	public RestPosition(InitialProcessing ip){
		this.ip =ip;
		ip.pt =this;
		do_classification();	
	}
	
	public void do_classification(){
		String forceName = "";
		String govNumMod = "gov", depNumMod ="dep";
		String govCompound = "gov", depCompound = "dep";
		String govAmod = "gov", depAmod = "dep";
		for(List<TypedDependency> list : ip.typed_dependencies){
			int directionFlag = 0;
			int inclination_flag=0;
			//System.out.println("inside Rest position - for loop");
			for(TypedDependency td : list){
				System.out.print("td :gov() - "+td.gov());				
				System.out.print("td :dep() - "+td.dep());
				System.out.println("td :reln() - "+td.reln());
			
				if(directionFlag == 0 && !govAmod.equals("gov") && !forceName.equals("")){
					if(depAmod.contains("right"))
						current_direction = 4;
					else if (depAmod.contains("left"))
						current_direction = 3;
					else if (depAmod.contains("up"))
						current_direction = 2;
					else if(depAmod.contains("down"))
						current_direction = 1;
					directionFlag = 1;
					//System.out.println("direction");
				}
				
				if(td.reln().toString().contains("nsubj") && objectName.equals("0")){
					String so = td.dep().toString();
					objectName = so.substring(0, so.indexOf("/"));
					if(govCompound.contains(objectName) && govNumMod.contains(objectName)){
						objectW.put(Double.parseDouble(depNumMod.substring(0,depNumMod.indexOf("/"))),depCompound.substring(0, depCompound.indexOf("/")));
						current_direction = 1;
						CheckUnits.actuallyCheckUnitsofObject((PositionTemplate)this);
						current_force = Double.parseDouble(depNumMod.substring(0,depNumMod.indexOf("/")))*9.8;
				
					}
				}
				
				else if(td.reln().toString().contains("nsubj") && forceName.equals("")){
					String so = td.dep().toString();
					forceName = so.substring(0, so.indexOf("/"));
					if(govCompound.contains(forceName) && govNumMod.contains(forceName)){
						if(govAmod.equals("gov")){
						current_direction = 1;
						CheckUnits.actuallyCheckUnitsofObject((PositionTemplate)this);
						}
						current_force = Double.parseDouble(depNumMod.substring(0,depNumMod.indexOf("/")));			
					}
				}

				else if ((dependency_for_inclination(td.reln().toString())) &&
							(hasInclination(td.dep().toString().substring(0,  td.dep().toString().indexOf("/"))) ||	
										hasInclination(td.gov().toString().substring(0,  td.gov().toString().indexOf("/"))))) {
					//System.out.println("Inside incline if");
					inclination_flag =1;
		 		}

				else if(td.reln().toString().contains("amod")){
					govAmod = td.gov().toString();
					depAmod = td.dep().toString();
					//System.out.println(govAmod);
					continue;
				}
				else if(td.reln().toString().equals("nummod")){
					govNumMod = td.gov().toString();
					depNumMod = td.dep().toString();
					continue;
				}
				
				else if(td.reln().toString().equals("compound")){
					govCompound = td.gov().toString();
					depCompound = td.dep().toString();
					continue;
				}
				
				else if (td.reln().toString().contains("nmod") && td.dep().toString().equals(govNumMod)){
					//System.out.println("For every nmod : ........ "+td.toString());
						if(td.gov().toString().contains(objectName)){
								current_direction = 1;	
								objectW.put(Double.parseDouble(depNumMod.substring(0,depNumMod.indexOf("/"))), govNumMod.substring(0, govNumMod.indexOf("/")));
								CheckUnits.actuallyCheckUnitsofObject((PositionTemplate)this);
								current_force = Double.parseDouble(depNumMod.substring(0,depNumMod.indexOf("/")))*9.8;
						}
						
						else if(td.gov().toString().contains(forceName)){
							if(govAmod.equals("gov")){
	//							System.out.println("gov " +govAmod);
								current_direction = 1;	
								CheckUnits.actuallyCheckUnitsofObject((PositionTemplate)this);
							}
							current_force = Double.parseDouble(depNumMod.substring(0,depNumMod.indexOf("/")));
						}
						
						else if(td.gov().toString().contains(ip.root_word)){
							System.out.println(depNumMod+" number of upward forces");
							addNumberOfForces(2,depNumMod.substring(0,depNumMod.indexOf("/")));
						}
						
						else if(isInclination(govNumMod) && inclination_flag==1){
							//System.out.println("Angle of Inclination is: "+depNumMod);
							String s[] = depNumMod.split("/");
							
							inclination_angle = Integer.parseInt(s[0]);
							System.out.println("\nAngle of Inclination is: "+inclination_angle);
							 
							//now resolve the forces from the object hashmap and angle of inclination
							Resolve.resolveForces(this);
						}
				}
				else if (td.reln().toString().contains("nmod") && td.gov().toString().equals(govNumMod)){
				 	 
				 	if(isInclination(govNumMod)){
				 	inclination_angle = Integer.parseInt(depNumMod.substring(0, depNumMod.indexOf("/")));
				 	System.out.println("\nAngle of Inclination is: "+inclination_angle);
				 	 
				 	//now resolve the forces from the object hashmap and angle of inclination
				 	Resolve.resolveForces(this);
				 	}
				 	 
				}
			}
			
			//wont work if there are two forces separated by and
			if(inclination_angle==0 && current_direction!=0){
				System.out.println("Adding forces at direction "+current_direction);
				addForceAppro(current_force, current_direction);
				current_direction=0; current_force =0;
			}
		}		
		assignValuesToObject();
		
	}		
	
}
