package hu.bme.iit.beta.pandaexpress.model.tile.machine;

import hu.bme.iit.beta.pandaexpress.model.animal.Animal;
import hu.bme.iit.beta.pandaexpress.model.tile.Tile;

/**
 * Slot Machine class
 * Represents the tiles that has a slot machine on them
 * Extends the Machine abstract class
 */
public class SlotMachine extends Machine {

	/**
	 * Constructor
	 * Calls the constructor of its ancestor class, the Machine
	 */
	public SlotMachine() {
		super();
	}

	/**
	 * Machine makeNoise override
	 * The slot machine rings
	 * Tells the animals who are standing on the surrounding tiles that a ringing has happened
	 */
	@Override
	public void makeNoise() {
		Tile[] neighbors = getNeighbors();

		for(Tile neighbor: neighbors){
			Animal animalOnNeighbour = neighbor.getAnimal();
			if(animalOnNeighbour != null)
				animalOnNeighbour.hearRinging();
		}
	}
}
