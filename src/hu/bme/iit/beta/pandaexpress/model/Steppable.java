package hu.bme.iit.beta.pandaexpress.model;

/**
 * Steppable interface
 * All classes implements this interface that have a behaviour that occurs from time to time.
 */
public interface Steppable {
	/**
	 * The function that is called on stepping
	 */
	void step();
}
