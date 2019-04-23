package hu.bme.iit.beta.pandaexpress;

import hu.bme.iit.beta.pandaexpress.debug.Menu;
import hu.bme.iit.beta.pandaexpress.debug.interpreter.Environment;
import hu.bme.iit.beta.pandaexpress.debug.interpreter.Interpreter;

public class Main {

	/**
	 * Entry point of the application
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		Menu menu = new Menu();
		menu.chooseMenuItems();
		System.out.println("Goodbye");
		*/

		new Interpreter(System.in, System.out, new Environment());
	}
}