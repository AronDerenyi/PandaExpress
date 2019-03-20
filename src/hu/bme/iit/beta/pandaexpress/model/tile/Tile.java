package hu.bme.iit.beta.pandaexpress.model.tile;

import hu.bme.iit.beta.pandaexpress.debug.Logger;
import hu.bme.iit.beta.pandaexpress.model.animal.Animal;

import java.util.LinkedList;
import java.util.List;

public class Tile {

	private List<Tile> neighbors = new LinkedList<>();
	private Animal animal = null;

	public Tile() {
		Logger.startFunction(this, "Tile");
		Logger.endFunction();
	}

	public Tile[] getNeighbors() {
		Logger.startFunction(this, "getNeighbors");
		return Logger.endFunction(neighbors.toArray(new Tile[0]));
	}

	public void connectNeighbor(Tile neighbor) {
		Logger.startFunction(this, "connectNeighbor", neighbor);
		neighbors.add(neighbor);
		neighbor.neighbors.add(this);
		Logger.endFunction();
	}

	public Animal getAnimal() {
		Logger.startFunction(this, "getAnimal");
		return Logger.endFunction(animal);
	}

	public void setAnimal(Animal animal) {
		Logger.startFunction(this, "setAnimal", animal);
		this.animal = animal;
		Logger.endFunction();
	}

	public boolean stepOn(Animal animal) {
		Logger.startFunction(this, "stepOn", animal);
		if (this.animal == null) {
			animal.replaceTile(this);
			return Logger.endFunction(true);
		} else {
			return Logger.endFunction(false);
		}
	}

	public boolean sitOn(Animal animal) {
		Logger.startFunction(this, "sitOn", animal);
		return Logger.endFunction(false);
	}
}
