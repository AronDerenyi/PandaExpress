package hu.bme.iit.beta.pandaexpress.model.animal;

import hu.bme.iit.beta.pandaexpress.debug.Logger;
import hu.bme.iit.beta.pandaexpress.model.tile.Tile;

public class Animal {

	private boolean dead = false;

	private Tile tile = null;

	private Animal following = null;
	private Animal followedBy = null;

	public Animal() {
		Logger.startFunction("Animal");
		Logger.endFunction();
	}

	public boolean isDead() {
		Logger.startFunction("isDead");
		return Logger.endFunction(dead);
	}

	public void die() {
		Logger.startFunction("die");

		unfollow();
		release();
		dead = true;

		Logger.endFunction();
	}

	public Tile getTile() {
		Logger.startFunction("getTile");
		return Logger.endFunction(tile);
	}

	public void leaveTile() {
		Logger.startFunction("leaveTile");

		if (tile != null) {
			tile.setAnimal(this);
		}

		Logger.endFunction();
	}

	public void replaceTile(Tile tile) {
		Logger.startFunction("replaceTile", tile);

		leaveTile();
		tile.setAnimal(this);

		Logger.endFunction();
	}

	public void move(Tile tile) {
		Logger.startFunction("move", tile);

		Tile prevTile = this.tile;
		boolean stepped = tile.stepOn(this);
		if (stepped && prevTile != null && followedBy != null) {
			followedBy.move(prevTile);
		}

		Logger.endFunction();
	}

	public Animal getFollowing() {
		Logger.startFunction("getFollowing");
		return Logger.endFunction(following);
	}

	public Animal getFollowedBy() {
		Logger.startFunction("getFollowedBy");
		return Logger.endFunction(followedBy);
	}

	public boolean follow(Animal animal) {
		Logger.startFunction("follow", animal);

		unfollow();
		if (animal.followedBy != null) {
			animal.followedBy.unfollow();
		}

		following = animal;
		following.followedBy = this;

		return Logger.endFunction(true);
	}

	public void unfollow() {
		Logger.startFunction("unfollow");

		if (following != null) {
			following.followedBy = null;
			following = null;
		}

		Logger.endFunction();
	}

	public void release() {
		Logger.startFunction("release");

		if (followedBy != null) {
			followedBy.release();
			followedBy.unfollow();
		}

		Logger.endFunction();
	}

	public void hearBeeping() {
		Logger.startFunction("hearBeeping");
		Logger.endFunction();
	}

	public void hearRinging() {
		Logger.startFunction("hearRinging");
		Logger.endFunction();
	}

	public void exit() {
		Logger.startFunction("exit");
		Logger.endFunction();
	}
}
