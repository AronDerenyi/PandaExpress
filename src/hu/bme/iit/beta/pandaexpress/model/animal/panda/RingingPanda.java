package hu.bme.iit.beta.pandaexpress.model.animal.panda;


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
