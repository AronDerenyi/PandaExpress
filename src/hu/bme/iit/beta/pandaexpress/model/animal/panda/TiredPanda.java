package hu.bme.iit.beta.pandaexpress.model.animal.panda;

import hu.bme.iit.beta.pandaexpress.debug.Logger;
import hu.bme.iit.beta.pandaexpress.model.animal.Animal;
import hu.bme.iit.beta.pandaexpress.model.tile.Tile;

public class TiredPanda extends Panda {

	private boolean sitting = false;

	/**
	 * The TiredPanda's constructor which requires an
	 * initial tile for the panda to move to.
	 *
	 * @param tile The panda's initial tile
	 */
	public TiredPanda(Tile tile) {
		super(tile);
		Logger.startFunction(this, "TiredPanda", tile);
		Logger.endFunction();
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
	 * @param tile The tile where the panda should move
	 */
	@Override
	public void move(Tile tile) {
		Logger.startFunction(this, "move", tile);

		// If the panda is sitting it should not be able to move
		if (sitting) {
			Logger.endFunction();
			return;
		}

		super.move(tile);

		for (Tile neighbor : getTile().getNeighbors()) {
			boolean sit = neighbor.sitOn(this);
			if (sit) {
				sitting = true;
				unfollow();
				release();
				break;
			}
		}

		Logger.endFunction();
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
		Logger.startFunction(this, "follow", animal);
		return Logger.endFunction(!sitting && super.follow(animal));
	}

	/**
	 * Overrides the Panda's follow function (for further information
	 * read the documentation of the Panda's follow method).
	 *
	 * Removes the panda's ability to handle the step event if it's sitting.
	 */
	@Override
	public void step() {
		Logger.startFunction(this, "step");
		if (!sitting) super.step();
		Logger.endFunction();
	}
}
