package hu.bme.iit.beta.pandaexpress.model.tile;

import hu.bme.iit.beta.pandaexpress.debug.Logger;
import hu.bme.iit.beta.pandaexpress.model.animal.Animal;

public class Chair extends Tile {
	
	// Tile stepOn override - return false, because animals can't step on a chair
	@Override
	public boolean stepOn(Animal a) {
		Logger.startFunction(this, "stepOn", a);
		return Logger.endFunction(false);
	}
	
	// Tile sitOn override - An animal can sit on a chair,
	// except if somebody already sitting on it
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
