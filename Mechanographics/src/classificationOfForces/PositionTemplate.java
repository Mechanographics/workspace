package classificationOfForces;

import java.util.HashMap;
import java.util.LinkedList;
import languageProcessing.InitialProcessing;

public class PositionTemplate {
	
	public String objectName = "0";
	public HashMap<Double,String> objectW = new HashMap<Double,String>();
	public InitialProcessing ip;
	
	public int inclination_angle = 0;
	public int friction_coeff = 0;
	
	public LinkedList<Double> upForce = new LinkedList<>();
	public LinkedList<Double> downForce = new LinkedList<>();
	public LinkedList<Double> leftForce = new LinkedList<>();
	public LinkedList<Double> rightForce = new LinkedList<>();
	
	double current_force;
	int current_direction;
	// 1 - downward, 2 - upward, 3- left, 4 -right 
	
	public void assignValuesToObject(){
		
			System.out.println("\n\nObject is " +objectName + " and it weighs " +objectW.toString());
			System.out.println("Downward forces: "+downForce);
			System.out.println("UP forces: "+upForce);
			System.out.println("Left forces: "+leftForce);
			System.out.println("Right forces: "+rightForce);
	}
	
	public boolean isInclination(String unit){
		
		unit = unit.substring(0, unit.indexOf("/")).toLowerCase();
		if(unit.equals("degrees")|| unit.equals("degree"))
			return true;
		else 
			return false;
		
	}
	
	public void addForceAppro(Double force, int direction){
		switch(direction){
		case 1 : downForce.add(force);
		case 2 : upForce.add(force);
		case 3 : leftForce.add(force);
		case 4 : rightForce.add(force);
		
		}
	}
}
