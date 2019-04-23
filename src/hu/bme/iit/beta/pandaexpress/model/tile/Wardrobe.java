package hu.bme.iit.beta.pandaexpress.model.tile;


import hu.bme.iit.beta.pandaexpress.model.animal.Animal;

public class Wardrobe extends Tile {
	
	private Tile entry;
	private Wardrobe pair;
	
	// This method sets the entry of a wardrobe, and connects them
    public void setEntry(Tile entry) {
		this.entry= entry;
		entry.connectNeighbor(this);
	}
    
    public Tile getEntry() {
    	return entry;
    }
	
    // connects two Wardrobe
	public void connect(Wardrobe pair) {
		this.pair= pair;
		this.pair.pair = this;
	}
	
	public Wardrobe getPair() {
		return pair;
	}
	
	// Tile stepOn override - The Wardrobe teleports the animal who on it 
	// to the other Wardrobe's entry
	@Override
	public boolean stepOn(Animal a) {
    	return pair.entry.stepOn(a);
	}
	
	// Tile getAnimal override - returns the animal who is standing on the Wardrobe's pair's entry
	@Override
	public Animal getAnimal() {
    	return pair.entry.getAnimal();
	}
}
