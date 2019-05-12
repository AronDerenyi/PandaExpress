package hu.bme.iit.beta.pandaexpress.model.animal.panda;
import hu.bme.iit.beta.pandaexpress.model.Stage;
import hu.bme.iit.beta.pandaexpress.model.animal.Animal;
import hu.bme.iit.beta.pandaexpress.model.tile.Tile;

abstract public class Panda extends Animal {
	/**
	 * Overrides the animal's exit function (for further information
	 * read the documentation of the animal's exit method).
	 *
	 * Starts the exiting sequence by setting the exiting flag to true.
	 */
	@Override
	public void exit() {
		// Only exit if it's following someone
		if (getFollowing() != null) {
			exiting = true;
		}
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
		if (exiting) {
			Stage.getInstance().score(1);

			Tile tile = getTile();
			Animal followedBy = getFollowedBy();

			leaveTile();
			if (followedBy != null) {
				followedBy.move(tile);
				followedBy.exit();
				followedBy.unfollow();
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
	}
}
