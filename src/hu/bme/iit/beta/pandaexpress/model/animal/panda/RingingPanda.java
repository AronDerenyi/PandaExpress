package hu.bme.iit.beta.pandaexpress.model.animal.panda;


public class RingingPanda extends Panda {

	/**
	 * Overrides the animal's hearRinging function (for further information
	 * read the documentation of the animal's hearRinging method).
	 *
	 * Unfollows and releases all of it's related animals.
	 */
	@Override
	public void hearRinging() {
		unfollow();
		release();
	}
}
