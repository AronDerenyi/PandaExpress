package hu.bme.iit.beta.pandaexpress.model;

import hu.bme.iit.beta.pandaexpress.debug.Logger;
import hu.bme.iit.beta.pandaexpress.model.tile.Entry;

public class Stage {

	private static int score = 0;
	private static Entry entry = null;

	public static Entry getEntry() {
		Logger.startFunction("getEntry");
		return Logger.endFunction(entry);
	}

	public static void setEntry(Entry entry) {
		Logger.startFunction("setEntry", entry);
		Stage.entry = entry;
		Logger.endFunction();
	}

	public static void score(int amount) {
		score += amount;
	}
}
