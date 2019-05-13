package hu.bme.iit.beta.pandaexpress.model.animal;

import hu.bme.iit.beta.pandaexpress.debug.Logger;
import hu.bme.iit.beta.pandaexpress.model.Steppable;
import hu.bme.iit.beta.pandaexpress.model.tile.Tile;

/**
 * Base class for all the animals.
 */
abstract public class Animal implements Steppable {
	/**
	 * A flag that stores whether the animal is dead
	 */
	private boolean dead = false;

	/**
	 * A flag that stores whether the animal is in "exiting mode"
	 */
	protected boolean exiting = false;

	/**
	 * The tile that the animal is standing on
	 */
	private Tile tile = null;

	/**
	 * Animal behind and in front of the current animal
	 */
	private Animal following = null;
	private Animal followedBy = null;

	/**
	 * Returns the "dead" flag of the animal which is if true,
	 * then the animal is considered dead.
	 *
	 * @return The dead flag
	 */
	public boolean isDead() {
		return dead;
	}

	/**
	 * Getter for exiting property
	 * @return exiting flag
	 */
	public boolean isExiting(){
		return exiting;
	}

	/**
	 * Kills the animal: sets the "dead" flag to true, unfollows
	 * and releases every related animal and leaves it's current tile.
	 */
	public void die() {
		unfollow();
		release();
		leaveTile();
		dead = true;
	}

	/**
	 * Returns the current tile (this is the tile on which the animal stands)
	 *
	 * @return The current tile
	 */
	public Tile getTile() {
		return tile;
	}

	/**
	 * Makes the animal leave it's current tile.
	 */
	public void leaveTile() {
		if (tile != null) {
			tile.setAnimal(null);
		}
	}

	/**
	 * Replaces the current tile with the given one.
	 *
	 * @param tile The new current tile
	 */
	public void replaceTile(Tile tile) {
		leaveTile();
		tile.setAnimal(this);
		this.tile = tile;
	}

	/**
	 * Tells the animal to move to a given tile. This is not equivalent
	 * to the replaceTile() as this does not guarantee that the move
	 * request will be successful.
	 *
	 * @param tile The tile where the animal should move
	 */
	public void move(Tile tile) {
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
	}

	/**
	 * Returns the animal which this animal is following.
	 *
	 * @return The animal which this animal is following.
	 */
	public Animal getFollowing() {
		return following;
	}

	/**
	 * Returns the animal following this animal.
	 *
	 * @return The animal following this animal
	 */
	public Animal getFollowedBy() {
		return followedBy;
	}

	/**
	 * Tries to make this animal follow the given animal.
	 * Returns true if this animal successfully started following
	 * the given animal.
	 *
	 * @param animal The animal to follow
	 * @return Whether or not this animal could follow the given animal
	 */
	public boolean follow(Animal animal) {
		if (animal == this) throw new RuntimeException("Error");

		unfollow();
		if (animal.followedBy != null) {
			animal.followedBy.unfollow();
		}

		following = animal;
		following.followedBy = this;

		return true;
	}

	/**
	 * Makes the animal stop following the animal it is currently following.
	 */
	public void unfollow() {
		if (following != null) {
			following.followedBy = null;
			following = null;
		}
	}

	/**
	 * Releases the animal that's following this animal and
	 * tells it to release it's followers too.
	 */
	public void release() {
		if (followedBy != null) {
			followedBy.release();
			followedBy.unfollow();
		}
	}

	/**
	 * This tells the animal that it should hear a beeping sound.
	 */
	public void hearBeeping(){ }

	/**
	 * This tells the animal that it should hear a ringing sound.
	 */
	public void hearRinging() { }

	/**
	 * Tells the animal that it should exit or at least start the
	 * exiting sequence (this mostly happens if the animal steps on
	 * an exit tile).
	 */
	public abstract void exit();

	/**
	 * Returns whether the animal can be dominated. Generally
	 * an animal cannot be dominated.
	 * @return The animal can be dominated
	 */
	public boolean dominate() {
		return false;
	}
}
