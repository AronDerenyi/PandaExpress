package hu.bme.iit.beta.pandaexpress.model.animal.panda;

import hu.bme.iit.beta.pandaexpress.debug.Logger;
import hu.bme.iit.beta.pandaexpress.model.Stage;
import hu.bme.iit.beta.pandaexpress.model.Steppable;
import hu.bme.iit.beta.pandaexpress.model.animal.Animal;
import hu.bme.iit.beta.pandaexpress.model.tile.Tile;

abstract public class Panda extends Animal implements Steppable {

	private boolean exiting = false;

	/**
	 * The Panda's constructor which requires an
	 * initial tile for the panda to move to.
	 *
	 * @param tile The panda's initial tile
	 */
	public Panda(Tile tile) {
		Logger.startFunction(this, "Panda", tile);
		move(tile);
		Logger.endFunction();
	}

	/**
	 * Overrides the animal's exit function (for further information
	 * read the documentation of the animal's exit method).
	 *
	 * Starts the exiting sequence by setting the exiting flag to true.
	 */
	@Override
	public void exit() {
		Logger.startFunction(this, "exit");

		// Only exit if it's following someone
		if (getFollowing() != null) {
			exiting = true;
		}

		Logger.endFunction();
	}

	/**
	 * Implements the Steppable interface's step method (for further information
	 * read the documentation of the Steppable interface).
	 *
	 * This makes the panda move randomly if the panda isn't exiting, dead or
	 * following an animal. If the panda is exiting this method makes the panda
	 * leave the stage, increase the score and lead out it's followers.
	 */
	@Override
	public void step() {
		Logger.startFunction(this, "step");

		if (exiting) {
			Stage.score(1);

			Tile tile = getTile();
			Animal followedBy = getFollowedBy();

			leaveTile();
			if (followedBy != null) {
				followedBy.move(tile);
				followedBy.unfollow();
				followedBy.exit();
			}

			die();
		} else if (!isDead() && getFollowing() == null) {
			// Step randomly on 10% of every step
			if (Math.random() < 0.1) {
				Tile tile = getTile();
				if (tile != null) {
					Tile[] neighbors = tile.getNeighbors();
					int index = (int) (neighbors.length * Math.random());
					move(neighbors[index]);
				}
			}
		}

		Logger.endFunction();
	}
}
