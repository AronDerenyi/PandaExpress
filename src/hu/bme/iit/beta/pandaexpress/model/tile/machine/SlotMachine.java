package hu.bme.iit.beta.pandaexpress.model.tile.machine;

import hu.bme.iit.beta.pandaexpress.debug.Logger;
import hu.bme.iit.beta.pandaexpress.model.animal.Animal;
import hu.bme.iit.beta.pandaexpress.model.tile.Tile;

public class SlotMachine extends Machine {
	
	public SlotMachine(int[] whenToMakeNoise) {
		super(whenToMakeNoise);
		Logger.startFunction(this, "SlotMachine", whenToMakeNoise);
		Logger.endFunction();
	}
	
	
	@Override
	public boolean stepOn(Animal a) {
		Logger.startFunction(this, "stepOn", a);
		return Logger.endFunction(false);
	}

	
	@Override
	protected void makeNoise() {
		Logger.startFunction(this, "makeNoise");
		
		Tile[] neighbors= getNeighbors();
		for(int i=0; i<neighbors.length; i++) {
			if(neighbors[i].getAnimal()!=null) {
				neighbors[i].getAnimal().hearRinging();
			}
		}
		
		Logger.endFunction();
	}
}
