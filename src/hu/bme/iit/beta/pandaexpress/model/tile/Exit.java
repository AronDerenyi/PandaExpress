package hu.bme.iit.beta.pandaexpress.model.tile;

import hu.bme.iit.beta.pandaexpress.debug.Logger;
import hu.bme.iit.beta.pandaexpress.model.animal.Animal;

public class Exit extends Tile {
	
	// Constructor
	public Exit() {
		Logger.startFunction(this, "Exit");
		Logger.endFunction();
	}
	
	// Tile stepOn override - calls animal's exit method, when an animal steps on it
	@Override
	public boolean stepOn(Animal a) {
		Logger.startFunction(this, "stepOn", a);
		a.exit();
		return Logger.endFunction(true);
	}
	

}
