package hu.bme.iit.beta.pandaexpress.model.tile;

import hu.bme.iit.beta.pandaexpress.debug.Logger;
import hu.bme.iit.beta.pandaexpress.model.animal.Animal;

import java.util.LinkedList;
import java.util.List;

public class Tile {

	private List<Tile> neighbors = new LinkedList<>();
	private Animal animal = null;

	public Tile() {
		Logger.startFunction("Tile");
		Logger.endFunction();
	}

	public Tile[] getNeighbors() {
		Logger.startFunction("getNeighbors");
		return Logger.endFunction(neighbors.toArray(new Tile[0]));
	}

	public void connectNeighbor(Tile neighbor) {
		Logger.startFunction("connectNeighbor", neighbor);
		neighbors.add(neighbor);
		Logger.endFunction();
	}

	public Animal getAnimal() {
		Logger.startFunction("getAnimal");
		return Logger.endFunction(animal);
	}

	public void setAnimal(Animal animal) {
		Logger.startFunction("setAnimal", animal);
		this.animal = animal;
		Logger.endFunction();
	}

	public boolean stepOn(Animal animal) {
		Logger.startFunction("stepOn", animal);
		if (this.animal == null) {
			animal.replaceTile(this);
			return Logger.endFunction(true);
		} else {
			return Logger.endFunction(false);
		}
	}

	public boolean sitOn(Animal animal) {
		Logger.startFunction("sitOn", animal);
		return Logger.endFunction(false);
	}
}
