package hu.bme.iit.beta.pandaexpress.model.tile;

import hu.bme.iit.beta.pandaexpress.debug.Logger;
import hu.bme.iit.beta.pandaexpress.model.animal.Animal;

public class Chair extends Tile {
	
	public Chair() {
		Logger.startFunction(this, "Chair");
		Logger.endFunction();
	}
	
	@Override
	public boolean stepOn(Animal a) {
		Logger.startFunction(this, "stepOn", a);
		return Logger.endFunction(false);
	}
	
	@Override
	public boolean sitOn(Animal a) {
		Logger.startFunction(this, "sitOn", a);
		if(getAnimal() == null) {
			a.replaceTile(this);
			return Logger.endFunction(true);
		} else {
			return Logger.endFunction(false);
		}
	}

}
