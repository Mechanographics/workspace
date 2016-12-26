package classificationOfForces;

import java.util.Iterator;
import java.util.Map;

public class MassAndForces extends PositionTemplate {

	public void setObjectValues(String depNumMod, String depCompound){

		objectW.put(Double.parseDouble(depNumMod.substring(0,depNumMod.indexOf("/"))),depCompound.substring(0, depCompound.indexOf("/")));
		current_direction = 1;
		CheckUnits.actuallyCheckUnitsofObject((PositionTemplate)this);
		 Iterator it = objectW.entrySet().iterator();
	        Map.Entry pair = (Map.Entry)it.next();
	    	current_force = (double) pair.getKey() * 9.8;
	}
	
}
