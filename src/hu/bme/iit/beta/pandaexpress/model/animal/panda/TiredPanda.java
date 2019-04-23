package hu.bme.iit.beta.pandaexpress.model.animal.panda;

import hu.bme.iit.beta.pandaexpress.model.animal.Animal;
import hu.bme.iit.beta.pandaexpress.model.tile.Tile;

/**
 * Tired Panda class
 * Represents the Pandas who are tired and will sit on an empty chair when are next to one.
 */
public class TiredPanda extends Panda {

	/**
	 * Whether the panda is sitting or not
	 */
	private boolean sitting = false;

	/**
	 * The TiredPanda's constructor
	 * Calls the constructor of the ancestor class, the Panda. Otherwise does nothing.
	 */
	public TiredPanda() {
		super();
	}

	/**
	 * Overrides the animal's move function (for further information
	 * read the documentation of the animal's move method).
	 *
	 * This method also makes the panda try to sit on every neighbouring tile.
	 * If it could sit down, it releases all of it's relations to
	 * other animals and sets the sitting flag to true.
	 *
	 * If the panda is sitting, it's unable to move.
	 *
	 * @param where The tile where the panda should move
	 */
	@Override
	public void move(Tile where) {
		// If the panda is sitting it should not be able to move
		if (sitting) {
			return;
		}

		super.move(where);

		for (Tile neighbor : getTile().getNeighbors()) {
			boolean sit = neighbor.sitOn(this);
			if (sit) {
				sitting = true;
				unfollow();
				release();
				break;
			}
		}
	}

	/**
	 * Overrides the animal's follow function (for further information
	 * read the documentation of the animal's follow method).
	 *
	 * Removes the panda's ability to follow anyone if it's sitting.
	 *
	 * @param animal The animal to follow
	 * @return False if the panda is sitting otherwise the default behaviour
	 */
	@Override
	public boolean follow(Animal animal) {
		return !sitting && super.follow(animal);
	}

	/**
	 * Overrides the Panda's step function (for further information
	 * read the documentation of the Panda's step method).
	 *
	 * Removes the panda's ability to handle the step event if it's sitting.
	 */
	@Override
	public void step() {
		if (!sitting) super.step();
	}
}
