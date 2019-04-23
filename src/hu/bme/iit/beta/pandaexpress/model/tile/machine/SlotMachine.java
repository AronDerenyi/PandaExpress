package hu.bme.iit.beta.pandaexpress.model.tile.machine;

import hu.bme.iit.beta.pandaexpress.debug.Logger;
import hu.bme.iit.beta.pandaexpress.model.animal.Animal;
import hu.bme.iit.beta.pandaexpress.model.tile.Tile;

public class SlotMachine extends Machine {
	
	// Tile stepOn override - return false, because animals can't step on a slot machine
	@Override
	public boolean stepOn(Animal a) {
		Logger.startFunction(this, "stepOn", a);
		return Logger.endFunction(false);
	}

	// Slot machine rings
	@Override
	protected void makeNoise() {
		Logger.startFunction(this, "makeNoise");
		
		Tile[] neighbors= getNeighbors();

		for(Tile neighbor: neighbors){
			Animal animalOnNeighbor = neighbor.getAnimal();
			if(animalOnNeighbor != null)
				animalOnNeighbor.hearRinging();
		}
		
		Logger.endFunction();
	}
}
