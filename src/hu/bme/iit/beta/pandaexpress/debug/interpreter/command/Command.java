package hu.bme.iit.beta.pandaexpress.debug.interpreter.command;

import hu.bme.iit.beta.pandaexpress.debug.interpreter.Environment;

import java.io.OutputStream;

public interface Command {

	/**
	 * Creates the command's name which is used to call the command.
	 *
	 * @return The command's name
	 */
	String getName();

	/**
	 * Runs the commands body.
	 *
	 * @param arguments A list of the arguments where the first item is the whole
	 *                     raw argument itself. Arguments can be wrapped in single
	 *                     or double quotes. If an argument is not wrapped in quotes
	 *                     then it is terminated by a space character.
	 *
	 * @param output The output stream where the running command should output it's
	 *                  results.
	 *
	 * @param environment The a shared environment between commands. Commands can place
	 *                       objects here to be used later or in other commands.
	 */
	void call(String[] arguments, OutputStream output, Environment environment);
}
