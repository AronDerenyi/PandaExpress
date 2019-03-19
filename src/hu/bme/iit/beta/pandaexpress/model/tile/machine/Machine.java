package hu.bme.iit.beta.pandaexpress.model.tile.machine;

import hu.bme.iit.beta.pandaexpress.debug.Logger;
import hu.bme.iit.beta.pandaexpress.model.Steppable;
import hu.bme.iit.beta.pandaexpress.model.tile.Tile;


public abstract class Machine extends Tile implements Steppable {

	private int[] whenToMakeNoise;
	private int counter;

	public Machine(int[] whenToMakeNoise) {
		Logger.startFunction(this, "Machine", whenToMakeNoise);
		this.whenToMakeNoise= whenToMakeNoise;
		counter= 0;
		Logger.endFunction();
	}
	
	protected abstract void makeNoise();

	@Override
	public void step() {
		Logger.startFunction(this, "step");
		counter++;
		
		for(int i=0; i<whenToMakeNoise.length; i++) {
			if(counter == whenToMakeNoise[i]) {
				makeNoise();
			}
		}
		
		Logger.endFunction();
	}
}
