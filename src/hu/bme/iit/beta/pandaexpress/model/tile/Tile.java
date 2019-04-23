package hu.bme.iit.beta.pandaexpress.model.tile;

import hu.bme.iit.beta.pandaexpress.debug.Logger;
import hu.bme.iit.beta.pandaexpress.model.animal.Animal;

import java.util.LinkedList;
import java.util.List;

public class Tile {

	private List<Tile> neighbors = new LinkedList<>();
	private Animal animal = null;
	
	// return neighbor
	public Tile[] getNeighbors() {
		Logger.startFunction(this, "getNeighbors");
		return Logger.endFunction(neighbors.toArray(new Tile[0]));
	}
	
	// connects neighbor
	public void connectNeighbor(Tile neighbor) {
		Logger.startFunction(this, "connectNeighbor", neighbor);
		if(neighbors.contains(neighbor)) return;
		neighbors.add(neighbor);
		neighbor.neighbors.add(this);
		Logger.endFunction();
	}
	
	// returns animal
	public Animal getAnimal() {
		Logger.startFunction(this, "getAnimal");
		return Logger.endFunction(animal);
	}
	
	// sets animal
	public void setAnimal(Animal animal) {
		Logger.startFunction(this, "setAnimal", animal);
		this.animal = animal;
		Logger.endFunction();
	}
	
	// animal steps on this tile
	public boolean stepOn(Animal animal) {
		Logger.startFunction(this, "stepOn", animal);
		if (this.animal == null) {
			animal.replaceTile(this);
			return Logger.endFunction(true);
		} else {
			return Logger.endFunction(false);
		}
	}
	
	// animal tries tho sit on this tile
	public boolean sitOn(Animal animal) {
		Logger.startFunction(this, "sitOn", animal);
		return Logger.endFunction(false);
	}
}
