package hu.bme.iit.beta.pandaexpress.model.animal;


import hu.bme.iit.beta.pandaexpress.model.Stage;
import hu.bme.iit.beta.pandaexpress.model.Steppable;
import hu.bme.iit.beta.pandaexpress.model.tile.Entry;
import hu.bme.iit.beta.pandaexpress.model.tile.Tile;

/**
 * Orangutan class
 * Represents the orangutans.
 * Orangutans are controlled by the player, they can catch pandas and lead them.
 */
public class Orangutan extends Animal implements Steppable {
	
	/**
	 *  Flag that stores whether Orangutan is frozen
	 */
	private int frozen;
	
	/**
	 * Overrides the animal's move function (for further information
	 * read the documentation of the animal's move method).
	 *
	 * This method also check's for animals standing on the tile where
	 * the orangutan should move. If there is an animal, it tries to grab it
	 * and make it follow the orangutan. If it cannot grab it, it tries to dominate it
	 * and steal the animals that follow him.
	 *
	 * @param tile The tile where the orangutan should move
	 */
	@Override
	public void move(Tile tile) {
		if (exiting) return;

		Animal animal = tile.getAnimal();
		Animal prevFollowedBy = getFollowedBy();

		if (frozen == 0 && animal != null) {
			if (animal.follow(this)) {
				animal.release();
				animal.leaveTile();
				super.move(tile);
				if (prevFollowedBy != null) {
					prevFollowedBy.follow(animal);
				}
			} else if (getFollowedBy() == null) {
				Animal followingAnimal = animal.getFollowedBy();
				if (animal.dominate()) {
					Tile prevTile = getTile();
					super.move(tile);
					followingAnimal.follow(this);
					animal.move(prevTile);
				}
			}
		} else {
			super.move(tile);
		}
	}

	/**
	 * Overrides the animal's dominate method.
	 * The orangutan can be dominated
	 */
	@Override
	public boolean dominate() {
		if (getFollowedBy() != null) {
			getFollowedBy().unfollow();
			leaveTile();
			setFrozen(3);
			return true;
		} else {
			return false;
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
			Entry entry = Stage.getInstance().getEntry();
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
	
	/**
	 * Overrides the Animal class replaceTile function. If the Orangutan is frozen then it can't move
	 */
	@Override
	public void replaceTile(Tile tile) {
		super.replaceTile(tile);
		if (frozen > 0) frozen--;
	}
	
	/**
	 * getter: returns value of frozen
	 */
	public int getFrozen() {
		return frozen;
	}
	
	/**
	 * setter: sets value of frozen
	 */
	public void setFrozen(int c) {
		frozen= c;
	}
}
