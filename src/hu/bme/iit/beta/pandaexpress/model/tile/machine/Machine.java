package hu.bme.iit.beta.pandaexpress.model.tile.machine;

import hu.bme.iit.beta.pandaexpress.model.Steppable;
import hu.bme.iit.beta.pandaexpress.model.animal.Animal;
import hu.bme.iit.beta.pandaexpress.model.tile.Tile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Machine abstract class
 * Represents the tiles that has a machine of some sort on them.
 * Ancestor class to the ChocolateMachine and the SlotMachine classes.
 */
public abstract class Machine extends Tile implements Steppable {

	/**
	 * Array of integers that tells which ticks the machine should make a noise
	 */
	private List<Integer> whenToMakeNoise;
	/**
	 * Internal counter that counts the ticks that has passed since the creation of the machine
	 */
	private int steps;

	/**
	 * Constructor
	 * Initializes the machine
	 */
	public Machine() {
		this.whenToMakeNoise = new ArrayList<>();
		steps = 0;
	}

	/**
	 * Sets the whenToMakeNoise private attribute
	 * @param whenToMakeNoise The array of integers that contains the tick numbers when the machine should make a noise
	 */
	public void setWhenToMakeNoise(int[] whenToMakeNoise) {
		for(int noise : whenToMakeNoise)
			addNoise(noise);
	}

	/**
	 * Tile stepOn override
	 * Does nothing and returns false, because animals cannot step on a machine
	 * @param a The animal that tries to step on the machine
	 * @return Always false
	 */
	@Override
	public boolean stepOn(Animal a) {
		return false;
	}

	/**
	 * Abstract method to make a noise
	 */
	public abstract void makeNoise();

	/**
	 * Steppable step override
	 * Makes a noise when it is needed
	 * Iterate through the whenToMakeNoise array to see if the current tick is in it,
	 * if so, the machine shall make noise
	 */
	@Override
	public void step() {
		int max = Collections.max(whenToMakeNoise);

		for(int shallMakeNoise : whenToMakeNoise) {
			if(steps % max == shallMakeNoise) {
				makeNoise();
			}
		}
		steps++;
	}

	public final void addNoise(Integer i){
		whenToMakeNoise.add(i);
	}
}
