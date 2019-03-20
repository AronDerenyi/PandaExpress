package hu.bme.iit.beta.pandaexpress.model.tile;

import hu.bme.iit.beta.pandaexpress.debug.Logger;
import hu.bme.iit.beta.pandaexpress.model.animal.Animal;

public class WeakTile extends Tile{
	
	private int lives = 20;

    private void breakTile() {
		Logger.startFunction(this, "breakTile");
		if(lives > 0) {
			lives--;
		} else {
			lives = 0;
		}

		Animal animalOnTile = getAnimal();
		if(lives == 0 && animalOnTile != null) {
			animalOnTile.die();
		}
		
		Logger.endFunction();
	}

	public void setLives(int lives){
    	Logger.startFunction(this, "setLives", lives);
		this.lives = lives;
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
