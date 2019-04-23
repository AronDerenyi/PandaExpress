package hu.bme.iit.beta.pandaexpress.model.tile;

import hu.bme.iit.beta.pandaexpress.model.animal.Animal;

public class Exit extends Tile {


	/**
	 * This function overrides the Tile base class' stepOn function.
	 * It calls the exit function of the animal given in the parameter.
	 * @param animal the Animal that tries to step on this Tile
	 * @return always a true boolean value, because the animal successfully steps on it
	 */
	@Override
	public boolean stepOn(Animal animal) {
		animal.exit();
		return true;
	}


}