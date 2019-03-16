package hu.bme.iit.beta.pandaexpress.model.animal.panda;

import hu.bme.iit.beta.pandaexpress.debug.Logger;
import hu.bme.iit.beta.pandaexpress.model.tile.Tile;

public class RingingPanda extends Panda {

	public RingingPanda(Tile tile) {
		super(tile);
		Logger.startFunction("RingingPanda", tile);
		Logger.endFunction();
	}

	@Override
	public void hearRinging() {
		Logger.startFunction("hearRinging");
		unfollow();
		release();
		Logger.endFunction();
	}
}
