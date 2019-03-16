package hu.bme.iit.beta.pandaexpress.model.animal.panda;

import hu.bme.iit.beta.pandaexpress.debug.Logger;
import hu.bme.iit.beta.pandaexpress.model.tile.Tile;

public class BeepingPanda extends Panda {

	public BeepingPanda(Tile tile) {
		super(tile);
		Logger.startFunction(this, "BeepingPanda", tile);
		Logger.endFunction();
	}

	@Override
	public void hearBeeping() {
		Logger.startFunction(this, "hearBeeping");
		getTile().stepOn(this);
		Logger.endFunction();
	}
}
