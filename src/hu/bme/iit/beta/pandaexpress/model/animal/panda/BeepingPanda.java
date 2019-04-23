package hu.bme.iit.beta.pandaexpress.model.animal.panda;

public class BeepingPanda extends Panda {

	/**
	 * Overrides the animal's hearBeeping function (for further information
	 * read the documentation of the animal's hearBeeping method).
	 *
	 * Steps on the current tile again. This will make a weak tile lose lives.
	 */
	@Override
	public void hearBeeping() {
		getTile().stepOn(this);
	}
}

