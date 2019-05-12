package hu.bme.iit.beta.pandaexpress.model.tile;

import hu.bme.iit.beta.pandaexpress.model.animal.Animal;

import java.util.LinkedList;
import java.util.List;

public class Tile {

	/**
	 * Private attributes
	 * The list of tiles that are connected to this
	 * The animal, that is currently standing on this tile
	 */
	private List<Tile> neighbors = new LinkedList<>();
	private Animal animal = null;
	private int positionX;
	private int positionY;

	/**
	 * A getter function for the neighbours private attribute
	 * @return the array of tiles that are connected to this
	 */
	public Tile[] getNeighbors() {
		return neighbors.toArray(new Tile[0]);
	}

	/**
	 * This method connects the tile in the parameter to this one, i.e. adds it to both of their neighbours list.
	 * If it is already connected, then it returns without doing anything.
	 * @param neighbor the tile that is to be connected
	 */
	public void connectNeighbor(Tile neighbor) {
		if(neighbors.contains(neighbor)) return;

		neighbors.add(neighbor);
		neighbor.neighbors.add(this);
	}

	/**
	 * Getter method for the animal private attribute.
	 * @return the animal that is currently standing on this tile
	 */
	public Animal getAnimal() {
		return animal;
	}

	/**
	 * Setter method for the animal private attribute.
	 * Sets the animal attribute to the one given in the parameter.
	 * @param animal the Animal that is to be set
	 */
	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	/**
	 * This function is to be called when an animal tries to step on this tile.
	 * If the tile was empty it returns true, if it wasn't then it returns false.
	 * @param animal the Animal that tries to step on this Tile
	 * @return a boolean value, tue or false depending on whether or not there was an animal on the tile
	 */
	public boolean stepOn(Animal animal) {
		if (this.animal == null) {
			animal.replaceTile(this);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This function is to be called when an animal tries to sit on this tile.
	 * It returns a boolean value indicating whether or not this action is possible.
	 * @param animal the Animal that tries to sit on this Tile
	 * @return always false boolean value, because Animals can only sit on Chairs
	 */
	public boolean sitOn(Animal animal) {
		return false;
	}
	public final void setPosition(int x, int y){
		positionX = x;
		positionY = y;
	}

	public final int getPositionX(){ return positionX; }
	public final int getPositionY(){ return positionY; }

}