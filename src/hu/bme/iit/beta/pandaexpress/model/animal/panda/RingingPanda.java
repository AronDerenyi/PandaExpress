package hu.bme.iit.beta.pandaexpress.model.animal.panda;

import hu.bme.iit.beta.pandaexpress.debug.Logger;
import hu.bme.iit.beta.pandaexpress.model.tile.Tile;

public class RingingPanda extends Panda {

	/**
	 * Overrides the animal's hearRinging function (for further information
	 * read the documentation of the animal's hearRinging method).
	 *
	 * Unfollows and releases all of it's related animals.
	 */
	@Override
	public void hearRinging() {
		Logger.startFunction(this, "hearRinging");
		unfollow();
		release();
		Logger.endFunction();
	}
}
