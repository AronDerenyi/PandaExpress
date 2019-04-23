package hu.bme.iit.beta.pandaexpress.model;

import hu.bme.iit.beta.pandaexpress.debug.Logger;
import hu.bme.iit.beta.pandaexpress.model.tile.Entry;

public class Stage {

	/**
	 * Label for Logger
	 */
	private static final Object STAGE_LABEL = "Stage";

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

	/**
	 * Singleton behaviour
	 * Creates an instance of Stage if none was created before
	 * @return the created instance
	 */
	public static Stage getInstance(){
		Logger.startFunction(STAGE_LABEL, "getInstance");
		if(instance == null)
			return Logger.endFunction(instance = new Stage());
		return Logger.endFunction(instance);
	}

	/**
	 * getter for entry
	 * @return entry of the game
	 */
	public static Entry getEntry() {
		Logger.startFunction(STAGE_LABEL, "getEntry");
		return Logger.endFunction(entry);
	}

	/**
	 * setter for entry
	 * @param entry
	 */
	public static void setEntry(Entry entry) {
		Logger.startFunction(STAGE_LABEL, "setEntry", entry);
		Stage.entry = entry;
		Logger.endFunction();
	}

	/**
	 * increments gained score by the given amount
	 * @param amount how much the game score shall be incremented
	 */
	public static void score(int amount) {
		Logger.startFunction(STAGE_LABEL, "score", amount);
		score += amount;
		Logger.endFunction();
	}
}
