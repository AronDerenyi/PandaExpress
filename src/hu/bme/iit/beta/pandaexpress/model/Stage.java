package hu.bme.iit.beta.pandaexpress.model;


import hu.bme.iit.beta.pandaexpress.model.tile.Entry;

public class Stage {


	/**
	 * Gained score during the game
	 * The amount pandas that have been escorted through exit
	 */
	private static int score = 0;
	/**
	 * The entry of the stage
	 */
	private static Entry entry = null;
	/**
	 * Static instance for singleton behavior
	 */
	private static Stage instance;
	
	private Stage() {}

	/**
	 * Singleton behaviour
	 * Creates an instance of Stage if none was created before
	 * @return the created instance
	 */
	public static Stage getInstance(){
		if(instance == null)
			return instance = new Stage();
		return instance;
	}

	/**
	 * getter for entry
	 * @return entry of the game
	 */
	public static Entry getEntry() {
		return entry;
	}

	/**
	 * setter for entry
	 * @param entry the entry tile to be set
	 */
	public static void setEntry(Entry entry) {
		Stage.entry = entry;
	}

	/**
	 * increments gained score by the given amount
	 * @param amount how much the game score shall be incremented
	 */
	public static void score(int amount) {
		score += amount;
	}

	/**
	 * Resets the score
	 */
	public static void resetScore() { score = 0; }

	public static int getScore() {
		return score;
	}
	
	public static void load(int level) {
		
	}
}
