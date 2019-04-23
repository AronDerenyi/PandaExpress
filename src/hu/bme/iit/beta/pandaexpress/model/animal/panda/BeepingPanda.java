package hu.bme.iit.beta.pandaexpress.model.animal.panda;

import hu.bme.iit.beta.pandaexpress.debug.Logger;
import hu.bme.iit.beta.pandaexpress.model.tile.Tile;

public class BeepingPanda extends Panda {

	/**
	 * Overrides the animal's hearBeeping function (for further information
	 * read the documentation of the animal's hearBeeping method).
	 *
	 * Steps on the current tile again. This will make a weak tile lose lives.
	 */
	@Override
	public void hearBeeping() {
		Logger.startFunction(this, "hearBeeping");
		getTile().stepOn(this);
		Logger.endFunction();
	}
}
