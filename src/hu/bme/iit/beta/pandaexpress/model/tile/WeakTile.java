package hu.bme.iit.beta.pandaexpress.model.tile;

import hu.bme.iit.beta.pandaexpress.debug.Logger;
import hu.bme.iit.beta.pandaexpress.model.animal.Animal;

public class WeakTile extends Tile{
	
	private int lives;
	
	public WeakTile(int lives) {
		Logger.startFunction(this, "WeakTile");
		this.lives= lives;
		Logger.endFunction();
	}
	
	private void breakTile() {
		Logger.startFunction(this, "break");
		if(lives > 0) {
			lives--;
		} else {
			lives = 0;
		}
		
		if(lives == 0 && getAnimal() != null) {
			getAnimal().die();
		}
		
		Logger.endFunction();
	}
	
	@Override
	public boolean stepOn(Animal a) {
		
		Logger.startFunction(this, "stepOn", a);
		
		if(getAnimal() == null) {
			a.replaceTile(this);
			breakTile();
			
			return Logger.endFunction(true);
		} else {
			return Logger.endFunction(false);
		}
	}
	

}
