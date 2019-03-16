package hu.bme.iit.beta.pandaexpress.model.animal.panda;

import hu.bme.iit.beta.pandaexpress.debug.Logger;
import hu.bme.iit.beta.pandaexpress.model.animal.Animal;
import hu.bme.iit.beta.pandaexpress.model.tile.Tile;

public class TiredPanda extends Panda {

	private boolean sitting = false;

	public TiredPanda(Tile tile) {
		super(tile);
		Logger.startFunction("TiredPanda", tile);
		Logger.endFunction();
	}

	@Override
	public void move(Tile tile) {
		Logger.startFunction("move", tile);

		super.move(tile);

		for (Tile neighbor : getTile().getNeighbors()) {
			boolean sit = neighbor.sitOn(this);
			if (sit) {
				sitting = true;
				unfollow();
				release();
				break;
			}
		}

		Logger.endFunction();
	}

	@Override
	public boolean follow(Animal animal) {
		Logger.startFunction("follow", animal);
		if (sitting) {
			return Logger.endFunction(false);
		} else {
			return Logger.endFunction(super.follow(animal));
		}
	}

	@Override
	public void step() {
		Logger.startFunction("step");
		if (!sitting) super.step();
		Logger.endFunction();
	}
}
