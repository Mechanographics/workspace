package classificationOfForces;

//A block of 10 kg is resting on an inclined plane at an angle 15 degrees

public class Resolve {

	public static void resolveForces(PositionTemplate pt){
		
		pt.leftForce.add(Math.sin(pt.inclination_angle)*pt.current_force);
		pt.downForce.add(Math.cos(pt.inclination_angle)*pt.current_force*(-1));
		
	}
	
}
