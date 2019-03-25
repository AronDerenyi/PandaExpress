package hu.bme.iit.beta.pandaexpress.model.tile;

import hu.bme.iit.beta.pandaexpress.debug.Logger;
import hu.bme.iit.beta.pandaexpress.model.animal.Animal;

public class Wardrobe extends Tile {
	
	private Tile entry;
	private Wardrobe pair;
	
	public Wardrobe() {
		Logger.startFunction(this, "Wardrobe");
		Logger.endFunction();
	}

    public void setEntry(Tile entry) {
		Logger.startFunction(this, "setEntry", entry);
		this.entry= entry;
		entry.connectNeighbor(this);
		Logger.endFunction();
	}
	
	public void connect(Wardrobe pair) {
		Logger.startFunction(this, "connect", pair);
		this.pair= pair;
		this.pair.pair = this;
		Logger.endFunction();
	}
	
	@Override
	public boolean stepOn(Animal a) {
		Logger.startFunction(this, "stepOn", a);
		return Logger.endFunction(pair.entry.stepOn(a));
	}
	
	@Override
	public Animal getAnimal() {
		Logger.startFunction(this, "getAnimal");
		return Logger.endFunction(pair.entry.getAnimal());
	}
	

}
