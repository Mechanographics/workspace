package classificationOfForces;

import java.util.LinkedList;

//A block of 10 kg is resting on an inclined plane at an angle 15 degrees

public class Resolve {

	public static LinkedList<Double> mg = new LinkedList<>();
	
	public static void resolveObjectWeight(PositionTemplate pt){
		//
		//sin and cos function take angles as radians. so convert
		mg.add(pt.current_force);
		pt.rightForce.add(Math.abs(Math.sin(pt.inclination_angle*(Math.PI/180))*pt.current_force));
		pt.downForce.add(Math.abs(Math.cos(pt.inclination_angle*(Math.PI/180))*pt.current_force));
		
	}
	
	public static void resolveForce(PositionTemplate pt){
		
		//sin and cos function take angles as radians. so convert
		//rightForce -because....- refer diagram ( while resolving..........)
		pt.rightForce.add(Math.abs(Math.sin(pt.inclined_force_angle*(Math.PI/180))*pt.current_force));
		pt.downForce.add(Math.abs(Math.cos(pt.inclined_force_angle*(Math.PI/180))*pt.current_force));
	}
}
