package hu.bme.iit.beta.pandaexpress.model.tile;

import hu.bme.iit.beta.pandaexpress.debug.Logger;
import hu.bme.iit.beta.pandaexpress.model.animal.Animal;

public class WeakTile extends Tile{
	
	private int lives = 20;
	
	// decrease the Tile's lives and if lives=0, then the animal standing on the tile dies
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
    
    // set lives of WeakTile
	public void setLives(int lives){
    	Logger.startFunction(this, "setLives", lives);
		this.lives = lives;
		Logger.endFunction();
	}
	
	// Tile stepOn override - When an animal steps on the WeakTile it calls it's breakTile method
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
