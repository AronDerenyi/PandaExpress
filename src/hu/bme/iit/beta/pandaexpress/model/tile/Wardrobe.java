package hu.bme.iit.beta.pandaexpress.model.tile;


import hu.bme.iit.beta.pandaexpress.model.animal.Animal;

public class Wardrobe extends Tile {
	
	/**
	 *  stores the entry of the Wardrobe
	 */
	private Tile entry;
	/**
	 *  stores the pair of the Wardrobe
	 */
	private Wardrobe pair;
	
	
    /**
     * This method sets the entry of a wardrobe, and connects them
     */
    public void setEntry(Tile entry) {
		this.entry= entry;
		entry.connectNeighbor(this);
	}
    
    /**
     * getter: returns the entry of the Wardrobe
     */
    public Tile getEntry() {
    	return entry;
    }
	
	/**
	 * this method connects two Wardrobe
	 */
	public void connect(Wardrobe pair) {
		this.pair= pair;
		this.pair.pair = this;
	}
	
	/**
	 * getter: returns the pair of the Wardrobe
	*/
	public Wardrobe getPair() {
		return pair;
	}
	
	
	/** 
	 *  Tile stepOn override - The Wardrobe teleports the animal who steps on it 
	 * to the other Wardrobe's entry
	 */
	@Override
	public boolean stepOn(Animal a) {
    	return pair.entry.stepOn(a);
	}
	
	
	/**
	 * Tile getAnimal override - returns the animal who is standing on the Wardrobe's pair's entry
	 */
	@Override
	public Animal getAnimal() {
    	return pair.entry.getAnimal();
	}
}
