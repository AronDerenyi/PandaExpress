package hu.bme.iit.beta.pandaexpress.model.tile.machine;

import hu.bme.iit.beta.pandaexpress.debug.Logger;
import hu.bme.iit.beta.pandaexpress.model.animal.Animal;
import hu.bme.iit.beta.pandaexpress.model.tile.Tile;

public class SlotMachine extends Machine {
	
	// Constructor
	public SlotMachine(int[] whenToMakeNoise) {
		super(whenToMakeNoise);
		Logger.startFunction(this, "SlotMachine", whenToMakeNoise);
		Logger.endFunction();
	}
	
	
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
			Animal animalOnNeighbour = neighbor.getAnimal();
			if(animalOnNeighbour != null)
				animalOnNeighbour.hearRinging();
		}
		
		Logger.endFunction();
	}
}
