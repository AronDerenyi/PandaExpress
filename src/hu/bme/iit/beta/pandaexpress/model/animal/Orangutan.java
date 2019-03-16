package hu.bme.iit.beta.pandaexpress.model.animal;

import hu.bme.iit.beta.pandaexpress.debug.Logger;
import hu.bme.iit.beta.pandaexpress.model.Stage;
import hu.bme.iit.beta.pandaexpress.model.Steppable;
import hu.bme.iit.beta.pandaexpress.model.tile.Entry;
import hu.bme.iit.beta.pandaexpress.model.tile.Tile;

public class Orangutan extends Animal implements Steppable {

	private boolean exiting = false;

	public Orangutan(Tile tile) {
		Logger.startFunction("Orangutan", tile);
		move(tile);
		Logger.endFunction();
	}

	public void move(Tile tile) {
		Logger.startFunction("move", tile);

		// Grab the animal on the tile the Orangutan tries to step on
		Animal animal = tile.getAnimal();
		Animal prevFollowedBy = getFollowedBy();
		boolean grabbed = false;
		if (animal != null) {
			grabbed = animal.follow(this);
			if (grabbed) {
				animal.release();
				animal.leaveTile();
			}
		}

		// Actually move
		super.move(tile);

		// Make the previously following animal follow the currently following animal
		if (grabbed && prevFollowedBy != null) {
			prevFollowedBy.follow(getFollowedBy());
		}

		Logger.endFunction();
	}

	public boolean follow(Animal animal) {
		Logger.startFunction("follow", animal);
		return Logger.endFunction(false);
	}

	public void exit() {
		Logger.startFunction("exit");
		exiting = true;
		Logger.endFunction();
	}

	@Override
	public void step() {
		Logger.startFunction("step");

		if (exiting) {
			// Tries to move to the entry
			Tile tile = getTile();
			Entry entry = Stage.getEntry();
			boolean stepped = entry.stepOn(this);

			// Successfully moved to the entry tile so it's no longer exiting and it let's go of it's followers
			if (stepped) {
				Animal followedBy = getFollowedBy();
				if (followedBy != null) {
					followedBy.move(tile);
					followedBy.unfollow();
				}
				exiting = false;
			}
		}

		Logger.endFunction();
	}
}
