package hu.bme.iit.beta.pandaexpress.model.animal.panda;

/**
 * Ringing Panda class
 * Represents the Pandas who are afraid of the ringing of the slot machines.
 */
public class RingingPanda extends Panda {

	/**
	 * Overrides the animal's hearRinging function (for further information
	 * read the documentation of the animal's hearRinging method).
	 *
	 * Releases all the animals who follow him.
	 */
	@Override
	public void hearRinging() {
		release();
	}
}
