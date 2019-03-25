package hu.bme.iit.beta.pandaexpress.model.tile;

import hu.bme.iit.beta.pandaexpress.debug.Logger;
import hu.bme.iit.beta.pandaexpress.model.animal.Animal;

public class Wardrobe extends Tile {
	
	private Tile entry;
	private Wardrobe pair;
	
	// Constructor
	public Wardrobe() {
		Logger.startFunction(this, "Wardrobe");
		Logger.endFunction();
	}
	
	// This method sets the entry of a wardrobe, and connects them
    public void setEntry(Tile entry) {
		Logger.startFunction(this, "setEntry", entry);
		this.entry= entry;
		entry.connectNeighbor(this);
		Logger.endFunction();
	}
	
    // connects two Wardrobe
	public void connect(Wardrobe pair) {
		Logger.startFunction(this, "connect", pair);
		this.pair= pair;
		Logger.endFunction();
	}
	
	// Tile stepOn override - The Wardrobe teleports the animal who on it 
	// to the other Wardrobe's entry
	@Override
	public boolean stepOn(Animal a) {
		Logger.startFunction(this, "stepOn", a);
		return Logger.endFunction(pair.entry.stepOn(a));
	}
	
	// Tile getAnimal override - returns the animal who is standing on the Wardrobe's pair's entry
	@Override
	public Animal getAnimal() {
		Logger.startFunction(this, "getAnimal");
		return Logger.endFunction(pair.entry.getAnimal());
	}
	

}
