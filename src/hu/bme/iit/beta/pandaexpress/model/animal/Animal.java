package hu.bme.iit.beta.pandaexpress.model.animal;

import hu.bme.iit.beta.pandaexpress.debug.Logger;
import hu.bme.iit.beta.pandaexpress.model.tile.Tile;

abstract public class Animal {

	private boolean dead = false;

	private Tile tile = null;

	private Animal following = null;
	private Animal followedBy = null;

	/**
	 * Returns the "dead" flag of the animal which is if true,
	 * then the animal is considered dead.
	 *
	 * @return The dead flag
	 */
	public boolean isDead() {
		Logger.startFunction(this, "isDead");
		return Logger.endFunction(dead);
	}

	/**
	 * Kills the animal: sets the "dead" flag to true, unfollows
	 * and releases every related animal and leaves it's current tile.
	 */
	public void die() {
		Logger.startFunction(this, "die");

		unfollow();
		release();
		leaveTile();
		dead = true;

		Logger.endFunction();
	}

	/**
	 * Returns the current tile (this is the tile on which the animal stands)
	 *
	 * @return The current tile
	 */
	public Tile getTile() {
		Logger.startFunction(this, "getTile");
		return Logger.endFunction(tile);
	}

	/**
	 * Makes the animal leave it's current tile.
	 */
	public void leaveTile() {
		Logger.startFunction(this, "leaveTile");

		if (tile != null) {
			tile.setAnimal(null);
		}

		Logger.endFunction();
	}

	/**
	 * Replaces the current tile with the given one.
	 *
	 * @param tile The new current tile
	 */
	public void replaceTile(Tile tile) {
		Logger.startFunction(this, "replaceTile", tile);

		leaveTile();
		tile.setAnimal(this);
		this.tile = tile;

		Logger.endFunction();
	}

	/**
	 * Tells the animal to move to a given tile. This is not equivalent
	 * to the replaceTile() as this does not guarantee that the move
	 * request will be successful.
	 *
	 * @param tile The tile where the animal should move
	 */
	public void move(Tile tile) {
		Logger.startFunction(this, "move", tile);

		// If the animal is dead it should not be able to move
		if (dead) {
			Logger.endFunction();
			return;
		}

		Tile prevTile = this.tile;
		boolean stepped = tile.stepOn(this);
		if (stepped && prevTile != null && followedBy != null) {
			followedBy.move(prevTile);
		}

		Logger.endFunction();
	}

	/**
	 * Returns the animal which this animal is following.
	 *
	 * @return The animal which this animal is following.
	 */
	public Animal getFollowing() {
		Logger.startFunction(this, "getFollowing");
		return Logger.endFunction(following);
	}

	/**
	 * Returns the animal following this animal.
	 *
	 * @return The animal following this animal
	 */
	public Animal getFollowedBy() {
		Logger.startFunction(this, "getFollowedBy");
		return Logger.endFunction(followedBy);
	}

	/**
	 * Tries to make thia animal follow the given animal.
	 * Returns true if this animal successfully started following
	 * the given animal.
	 *
	 * @param animal The animal to follow
	 * @return Whether or not this animal could follow the given animal
	 */
	public boolean follow(Animal animal) {
		Logger.startFunction(this, "follow", animal);
		unfollow();
		if (animal.followedBy != null) {
			animal.followedBy.unfollow();
		}

		following = animal;
		following.followedBy = this;

		return Logger.endFunction(true);
	}

	/**
	 * Makes the animal stop following the animal it is currently following.
	 */
	public void unfollow() {
		Logger.startFunction(this, "unfollow");

		if (following != null) {
			following.followedBy = null;
			following = null;
		}

		Logger.endFunction();
	}

	/**
	 * Releases the animal that's following this animal and
	 * tells it to release it's followers too.
	 */
	public void release() {
		Logger.startFunction(this, "release");

		if (followedBy != null) {
			followedBy.release();
			followedBy.unfollow();
		}

		Logger.endFunction();
	}

	/**
	 * This tells the animal that it should hear a beeping sound.
	 */
	public void hearBeeping() {
		Logger.startFunction(this, "hearBeeping");
		Logger.endFunction();
	}

	/**
	 * This tells the animal that it should hear a ringing sound.
	 */
	public void hearRinging() {
		Logger.startFunction(this, "hearRinging");
		Logger.endFunction();
	}

	/**
	 * Tells the animal that it should exit or at least start the
	 * exiting sequence (this mostly happens if the animal steps on
	 * an exit tile).
	 */
	public void exit() {
		Logger.startFunction(this, "exit");
		Logger.endFunction();
	}
}
