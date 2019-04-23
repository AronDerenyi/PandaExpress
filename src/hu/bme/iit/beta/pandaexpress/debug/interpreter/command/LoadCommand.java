package hu.bme.iit.beta.pandaexpress.debug.interpreter.command;

import hu.bme.iit.beta.pandaexpress.debug.interpreter.Environment;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class LoadCommand implements Command {

	@Override
	public String getName() {
		return "load";
	}

	@Override
	public void call(String[] arguments, OutputStream output, Environment environment) {
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(output));

		if (arguments.length != 2) {
			writer.println("Load requires 1 argument");
			writer.flush();
			return;
		}

		writer.println("Loading and running file: \"" + arguments[1] + "\"");

		writer.flush();
	}
}
