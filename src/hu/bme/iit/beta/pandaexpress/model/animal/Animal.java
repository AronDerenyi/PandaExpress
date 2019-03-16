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
		dead = true;
		Logger.endFunction();
	}

	public Tile getTile() {
		Logger.startFunction("getTile");
		return Logger.endFunction(tile);
	}

	public void leaveTile() {
		Logger.startFunction("leaveTile");
		tile.setAnimal(this);
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
		Tile prev = this.tile;
		if (tile.stepOn(this)) followedBy.move(prev);
		Logger.endFunction();
	}

	public boolean follow(Animal animal) {
		Logger.startFunction("follow", animal);
		following = animal;
		following.followedBy = this;
		return Logger.endFunction(true);
	}

	public void unfollow() {
		Logger.startFunction("unfollow");
		following.followedBy = null;
		following = null;
		Logger.endFunction();
	}

	public void release() {
		Logger.startFunction("release");
		followedBy.unfollow();
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
