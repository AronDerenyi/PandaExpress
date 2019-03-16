package hu.bme.iit.beta.pandaexpress.model.animal.panda;

import hu.bme.iit.beta.pandaexpress.debug.Logger;
import hu.bme.iit.beta.pandaexpress.model.Stage;
import hu.bme.iit.beta.pandaexpress.model.Steppable;
import hu.bme.iit.beta.pandaexpress.model.animal.Animal;
import hu.bme.iit.beta.pandaexpress.model.tile.Tile;

public class Panda extends Animal implements Steppable {

	private boolean exiting = false;

	public Panda(Tile tile) {
		Logger.startFunction("Panda", tile);
		move(tile);
		Logger.endFunction();
	}

	public void exit() {
		Logger.startFunction("exit");

		// Only exit if it's following someone
		if (getFollowing() != null) {
			exiting = true;
		}

		Logger.endFunction();
	}

	@Override
	public void step() {
		Logger.startFunction("step");

		if (exiting) {
			Stage.score(1);

			Tile tile = getTile();
			Animal followedBy = getFollowedBy();

			leaveTile();
			if (followedBy != null) {
				followedBy.move(tile);
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

		Logger.endFunction();
	}
}
