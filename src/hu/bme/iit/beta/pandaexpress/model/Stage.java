package hu.bme.iit.beta.pandaexpress.model;

import hu.bme.iit.beta.pandaexpress.debug.Logger;
import hu.bme.iit.beta.pandaexpress.model.tile.Entry;

public class Stage {

	private static final Object STAGE_LABEL = "Stage";

	private static int score = 0;
	private static Entry entry = null;

	public static Entry getEntry() {
		Logger.startFunction(STAGE_LABEL, "getEntry");
		return Logger.endFunction(entry);
	}

	public static void setEntry(Entry entry) {
		Logger.startFunction(STAGE_LABEL, "setEntry", entry);
		Stage.entry = entry;
		Logger.endFunction();
	}

	public static void score(int amount) {
		Logger.startFunction(STAGE_LABEL, "score", amount);
		score += amount;
		Logger.endFunction();
	}
}
