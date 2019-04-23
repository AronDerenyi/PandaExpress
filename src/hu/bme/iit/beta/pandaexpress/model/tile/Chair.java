package hu.bme.iit.beta.pandaexpress.model.tile;

import hu.bme.iit.beta.pandaexpress.model.animal.Animal;

/**
 * Chair class
 * Represents the tiles that has a chair on them. An animal can sit on it if it is not occupied yet.
 * If an animal has sit on it, he occupies the chair and never stands up.
 */
public class Chair extends Tile {

	/**
	 * Chair constructor
	 * Does nothing
	 */
	public Chair() {
	}

	/**
	 * Tile stepOn override
	 * Does nothing and returns false, because animals can't step on a chair
	 * @param a The animal that tries to step on the chair
	 * @return Always false
	 */
	@Override
	public boolean stepOn(Animal a) {
		return false;
	}

	/**
	 * Tile sitOn override
	 * Make an animal sit on the chair unless someone is already sitting on it
	 * Returns whether the animal has sit on the chair or not
	 * @param a The animal that tries to sit on the chair
	 * @return True if the animal has sit on the chair, false otherwise
	 */
	@Override
	public boolean sitOn(Animal a) {
		if(getAnimal() == null) {
			a.replaceTile(this);
			return true;
		} else {
			return false;
		}
	}

}
