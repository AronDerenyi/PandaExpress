package hu.bme.iit.beta.pandaexpress.model;


import hu.bme.iit.beta.pandaexpress.model.tile.Entry;

public class Stage {


	/**
	 * Gained score during the game
	 * The amount pandas that have been escorted through exit
	 */
	private int score = 0;
	/**
	 * The entry of the stage
	 */
	private Entry entry = null;
	/**
	 * Static instance for singleton behavior
	 */
	private static Stage instance;
	
	/**
	 * private empty Constructor
	 */
	private Stage() {}

	/**
	 * Singleton behaviour
	 * Creates an instance of Stage if none was created before
	 * @return the created instance
	 */
	public static Stage getInstance(){
		if(instance == null) instance = new Stage();
		return instance;
	}

	/**
	 * getter for entry
	 * @return entry of the game
	 */
	public Entry getEntry() {
		return entry;
	}

	/**
	 * setter for entry
	 * @param entry the entry tile to be set
	 */
	public void setEntry(Entry entry) {
		this.entry = entry;
	}

	/**
	 * increments gained score by the given amount
	 * @param amount how much the game score shall be incremented
	 */
	public void score(int amount) {
		score += amount;
	}

	/**
	 * Resets the score
	 */
	public void resetScore() { score = 0; }

	/**
	 * getter: returns score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * loads the level specified in the parameter (this method currently not used)
	 */
	public void load(int level) {

	}
}
