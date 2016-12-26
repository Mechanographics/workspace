package classificationOfForces;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeSet;

import edu.stanford.nlp.trees.TypedDependency;
import languageProcessing.InitialProcessing;

public class PositionTemplate {
	
	public String objectName = "0";
	public HashMap<Double,String> objectW = new HashMap<Double,String>();
	public InitialProcessing ip;
	
	public int inclination_angle = 0; //angle of inclination
	public int friction_coeff = 0;
	
	public int inclined_object =0;
	public int inclined_force_angle = 0;
	public int inclined_force_direction = 0;
	
	public LinkedList<Double> upForce = new LinkedList<>();
	public LinkedList<Double> downForce = new LinkedList<>();
	public LinkedList<Double> leftForce = new LinkedList<>();
	public LinkedList<Double> rightForce = new LinkedList<>();
	
	double current_force;
	int current_direction;
	// 1 - downward, 2 - upward, 3- left, 4 -right 
	
	public void print_dependency(TypedDependency td){
		System.out.print("td :gov() - "+td.gov());				
		System.out.print("td :dep() - "+td.dep());
		System.out.println("td :reln() - "+td.reln());
	}
	
	public void assignValuesToObject(){
		
			System.out.println("\n\nObject is " +objectName + " and it weighs " +objectW.toString());
			System.out.println("Downward forces: "+downForce);
			System.out.println("Up forces: "+upForce);
			System.out.println("Left forces: "+leftForce);
			System.out.println("Right forces: "+rightForce);
	}
	
	public void addNumberOfForces(int direction, String numbers){
		int forces = 1;
		if(numbers.length()==1)
			forces = Integer.parseInt(numbers);
		else{
			switch(numbers){
			case "one" : forces = 1;
			break;
			case "two" : forces = 2;
			break;
			case "three" : forces = 3;
			break;
			case "four" : forces = 4;
			break;
			case "five" : forces = 5;
			break;
			}
		}
		
		switch(direction){
		case 1:	for(int i=1;i<=forces;i++)
					downForce.add(0.0);
				break;
		case 2:	for(int i=1;i<=forces;i++)
					upForce.add(0.0);
				break;
		case 3:	for(int i=1;i<=forces;i++)
					leftForce.add(0.0);
				break;
		case 4:	for(int i=1;i<=forces;i++)
					rightForce.add(0.0);
				break;
		}
	}
	public int identifyDirection(String depAmod){
		if(depAmod.contains("right"))
			current_direction = 4;
		else if (depAmod.contains("left"))
			current_direction = 3;
		else if (depAmod.contains("up"))
			current_direction = 2;
		else if(depAmod.contains("down"))
			current_direction = 1;
		return 1;

	}
	public boolean isInclination(String unit){
		
		unit = unit.substring(0, unit.indexOf("/")).toLowerCase();
		if(unit.contains("degree"))
			return true;
		else 
			return false;
		
	}
	
	//hasInclination
	
	public boolean dependency_for_inclination(String relation){
		TreeSet<String> incline =  new TreeSet<>();
		incline.add("amod"); incline.add("acl"); incline.add("case"); incline.add("compound"); incline.add("acl:relcl");
		
		if(incline.contains(relation)){
			return true;
		}
		else
			return false;
	}
	
	public boolean hasInclination(String word){
	
		if(word.contains("inclin")|| word.contains("elevat"))
			return true;
		else
			return false;
	}
	
	public void addForceAppro(Double force, int direction){
		switch(direction){
		case 1 : downForce.add(force);
				break;
		case 2 : upForce.add(force);
				break;
		case 3 : leftForce.add(force);
				break;
		case 4 : rightForce.add(force);
				break;
		}
	}
}
