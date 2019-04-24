package hu.bme.iit.beta.pandaexpress.model.tile.machine;

import hu.bme.iit.beta.pandaexpress.model.animal.Animal;
import hu.bme.iit.beta.pandaexpress.model.tile.Tile;

public class ChocolateMachine extends Machine {
	
	/** 
	 * Tile stepOn override - return false, because animals can't step on a chocolate machine
	 */
	@Override
	public boolean stepOn(Animal a) {
		return false;
	}
 
	/** 
	 * Machine makeNoise override
	 * Chocolate machine beeps
	 * Tells the animals who are standing on the surrounding tiles that a beeping has happened
	 */
	@Override
	public void makeNoise() {
		Tile[] neighbors= getNeighbors();
		for(Tile neighbor: neighbors){
			Animal animalOnNeighbor = neighbor.getAnimal();
			if(animalOnNeighbor != null)
				animalOnNeighbor.hearBeeping();
		}
		
	}

}
