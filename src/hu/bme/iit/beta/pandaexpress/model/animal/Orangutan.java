package hu.bme.iit.beta.pandaexpress.model.animal;


import hu.bme.iit.beta.pandaexpress.model.Stage;
import hu.bme.iit.beta.pandaexpress.model.Steppable;
import hu.bme.iit.beta.pandaexpress.model.tile.Entry;
import hu.bme.iit.beta.pandaexpress.model.tile.Tile;

public class Orangutan extends Animal implements Steppable {
	
	private int frozen;
	
	/**
	 * Overrides the animal's move function (for further information
	 * read the documentation of the animal's move method).
	 *
	 * This method also check's for animals standing on the tile where
	 * the orangutan should move. If there is an animal, it tries to grab it
	 * and make it follow the orangutan.
	 *
	 * @param tile The tile where the orangutan should move
	 */
	@Override
	public void move(Tile tile) {

		// If the orangutan is exiting, it should not be able to move
		if (exiting) {
			return;
		}

		// Grab the animal on the tile the Orangutan tries to step on
		Animal animal = tile.getAnimal();
		Animal prevFollowedBy = getFollowedBy();
		boolean grabbed = false;
		if (animal != null && frozen == 0) {
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

	}

	/**
	 * Overrides the animal's follow function (for further information
	 * read the documentation of the animal's follow method).
	 *
	 * Removes the orangutan's ability to follow anyone.
	 *
	 * @param animal The animal to follow
	 * @return False as the orangutan can not follow anyone
	 */
	@Override
	public boolean follow(Animal animal) {
		return false;
	}

	/**
	 * Overrides the animal's exit function (for further information
	 * read the documentation of the animal's exit method).
	 *
	 * Starts the exiting sequence by setting the exiting flag to true.
	 */
	@Override
	public void exit() {
		exiting = true;
	}

	/**
	 * Implements the Steppable interface's step method (for further information
	 * read the documentation of the Steppable interface).
	 *
	 * This makes the animal lead out it's followers and move to the entry
	 * if the exiting flag is true.
	 */
	@Override
	public void step() {

		if (exiting) {
			// Tries to move to the entry
			Tile tile = getTile();
			Entry entry = Stage.getEntry();
			boolean stepped = entry.stepOn(this);

			// Successfully moved to the entry tile so it's no longer exiting and it let's go of it's followers
			if (stepped) {
				Animal followedBy = getFollowedBy();
				if (followedBy != null) {
					followedBy.exit(); // mondjuk
					followedBy.move(tile);
					followedBy.unfollow();
				}
				exiting = false;
			}
		}

	}
	
	@Override
	public void replaceTile(Tile tile) {
		
		if(frozen == 0) {
			super.replaceTile(tile);
		} else {
			frozen--;
		}

	}
	
	public int getFrozen() {
		return frozen;
	}
	
	public void setFrozen(int c) {
		frozen= c;
	}
}
