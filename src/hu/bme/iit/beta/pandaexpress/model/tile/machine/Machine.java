package hu.bme.iit.beta.pandaexpress.model.tile.machine;

import hu.bme.iit.beta.pandaexpress.debug.Logger;
import hu.bme.iit.beta.pandaexpress.model.Steppable;
import hu.bme.iit.beta.pandaexpress.model.tile.Tile;


public abstract class Machine extends Tile implements Steppable {

	private int[] whenToMakeNoise;
	private int counter;

	// Constructor
	public Machine(int[] whenToMakeNoise) {
		Logger.startFunction(this, "Machine", whenToMakeNoise);
		this.whenToMakeNoise= whenToMakeNoise;
		counter= 0;
		Logger.endFunction();
	}
	
	// makeNose abstract method
	protected abstract void makeNoise();
	
	// Steppable step override - make Noise when its needed
	@Override
	public void step() {
		Logger.startFunction(this, "step");


		for(int shallMakeNoise : whenToMakeNoise) {
			if(counter == shallMakeNoise) {
				makeNoise();
			}
		}

		counter++;
		Logger.endFunction();
	}
}
