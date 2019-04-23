package hu.bme.iit.beta.pandaexpress;

import hu.bme.iit.beta.pandaexpress.debug.Menu;

public class Main {

	/**
	 * Entry point of the application
	 * @param args
	 */
	public static void main(String[] args) {
		Menu menu = new Menu();
		menu.chooseMenuItems();
		System.out.println("Goodbye");
	}
}