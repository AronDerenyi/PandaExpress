package hu.bme.iit.beta.pandaexpress.model.animal;

import hu.bme.iit.beta.pandaexpress.debug.Logger;
import hu.bme.iit.beta.pandaexpress.model.tile.Tile;

abstract public class Animal {

	private boolean dead = false;

	private Tile tile = null;

	private Animal following = null;
	private Animal followedBy = null;

	public Animal() {
		Logger.startFunction(this, "Animal");
		Logger.endFunction();
	}

	public boolean isDead() {
		Logger.startFunction(this, "isDead");
		return Logger.endFunction(dead);
	}

	public void die() {
		Logger.startFunction(this, "die");

		unfollow();
		release();
		leaveTile();
		dead = true;

		Logger.endFunction();
	}

	public Tile getTile() {
		Logger.startFunction(this, "getTile");
		return Logger.endFunction(tile);
	}

	public void leaveTile() {
		Logger.startFunction(this, "leaveTile");

		if (tile != null) {
			tile.setAnimal(null);
		}

		Logger.endFunction();
	}

	public void replaceTile(Tile tile) {
		Logger.startFunction(this, "replaceTile", tile);

		leaveTile();
		tile.setAnimal(this);
		this.tile = tile;

		Logger.endFunction();
	}

	public void move(Tile tile) {
		Logger.startFunction(this, "move", tile);

		Tile prevTile = this.tile;
		boolean stepped = tile.stepOn(this);
		if (stepped && prevTile != null && followedBy != null) {
			followedBy.move(prevTile);
		}

		Logger.endFunction();
	}

	public Animal getFollowing() {
		Logger.startFunction(this, "getFollowing");
		return Logger.endFunction(following);
	}

	public Animal getFollowedBy() {
		Logger.startFunction(this, "getFollowedBy");
		return Logger.endFunction(followedBy);
	}

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

	public void unfollow() {
		Logger.startFunction(this, "unfollow");

		if (following != null) {
			following.followedBy = null;
			following = null;
		}

		Logger.endFunction();
	}

	public void release() {
		Logger.startFunction(this, "release");

		if (followedBy != null) {
			followedBy.release();
			followedBy.unfollow();
		}

		Logger.endFunction();
	}

	public void hearBeeping() {
		Logger.startFunction(this, "hearBeeping");
		Logger.endFunction();
	}

	public void hearRinging() {
		Logger.startFunction(this, "hearRinging");
		Logger.endFunction();
	}

	public void exit() {
		Logger.startFunction(this, "exit");
		Logger.endFunction();
	}
}
