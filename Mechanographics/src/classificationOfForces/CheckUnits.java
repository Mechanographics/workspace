package classificationOfForces;

import java.util.Iterator;
import java.util.Map;

public class CheckUnits  {
	
	public static void actuallyCheckUnitsofObject(PositionTemplate rp){
		for(Iterator<Map.Entry<Double, String>> it = rp.objectW.entrySet().iterator(); it.hasNext(); ) {
			Map.Entry<Double, String> entry = it.next(); 
			String s = 	entry.getValue();
			s = s.toLowerCase();
			Double key = entry.getKey();
			if(s.equals("gms") || s.equals("g") || s.equals("gm") || s.equals("grams")){
				//entry.setValue("kg");
				it.remove();
				rp.objectW.put(key/1000, "kg");
			}
			else if(s.equals("mg") || s.equals("mgs") || s.equals("milligrams")){
				//entry.setValue("kg");
				it.remove();
				rp.objectW.put(key/1000000, "kg");
			}
			
		}
	}

	/*public void isSIUnit (){
		for(Iterator<Map.Entry<Double, String>> it = objectW.entrySet().iterator(); it.hasNext(); ) {
			Map.Entry<Double, String> entry = it.next(); 
			String s = 	entry.getValue();
			s = s.toLowerCase();
			if(s.equals("gms") || s.equals("g") || s.equals("gm") || s.equals("grams")){
				//entry.setValue("kg");
				Double key = entry.getKey();
				it.remove();
				objectW.put(key/1000, "kg");
			}
		} 				
					
	}*/
		
}


