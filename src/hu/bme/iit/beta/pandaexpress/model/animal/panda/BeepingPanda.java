package hu.bme.iit.beta.pandaexpress.model.animal.panda;

/**
 * Beeping Panda class
 * Represents the Pandas who are afraid of the beeping of the chocolate machines.
 */
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

