package hu.bme.iit.beta.pandaexpress.model.tile;

import hu.bme.iit.beta.pandaexpress.model.animal.Animal;

/**
 * Weak tile class
 * Represents the tiles that can break when animals step on them for too many times.
 * Stores the number which tells that how many steps is needed for it to break.
 * If it is broken, the animals that step on it, fall down and die.
 */
public class WeakTile extends Tile{
	
	private int lives = 20;

	/**
	 * decrease the Tile's lives and if lives=0, then the animal standing on the tile dies
	 */
    private void breakTile() {
		if(lives > 0) {
			lives--;
		} else {
			lives = 0;
		}

		Animal animalOnTile = getAnimal();
		if(lives == 0 && animalOnTile != null) {
			animalOnTile.die();
		}
	}

	/**
	 * setter for lives property
	 */
	public void setLives(int lives){
		this.lives = lives;
	}

	/**
	 * Tile stepOn override - When an animal steps on the WeakTile it calls it's breakTile method
 	 */
	@Override
	public boolean stepOn(Animal a) {
		if(getAnimal() == null) {
			a.replaceTile(this);
			breakTile();
			
			return true;
		} else {
			breakTile();
			return false;
		}
	}

	/**
	 * getter for lives property
	 * @return lives property
	 */
	public int getLives(){
		return lives;
	}
}
