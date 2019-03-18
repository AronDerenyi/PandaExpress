package hu.bme.iit.beta.pandaexpress.model.tile;

import hu.bme.iit.beta.pandaexpress.debug.Logger;
import hu.bme.iit.beta.pandaexpress.model.animal.Animal;

public class Exit extends Tile {
	
	public Exit() {
		Logger.startFunction(this, "Exit");
		Logger.endFunction();
	}
	
	@Override
	public boolean stepOn(Animal a) {
		Logger.startFunction(this, "stepOn", a);
		a.exit();
		return Logger.endFunction(true);
	}
	

}
