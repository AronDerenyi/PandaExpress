package hu.bme.iit.beta.pandaexpress.debug.interpreter.command;

import hu.bme.iit.beta.pandaexpress.debug.interpreter.Environment;
import hu.bme.iit.beta.pandaexpress.debug.interpreter.Interpreter;

import java.io.*;

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

		try {
			InputStream fileInput = new FileInputStream(arguments[1]);
			new Interpreter(fileInput, output, environment);
		} catch (Exception e) {
			writer.println("Failed to load and run \"" + arguments[1] + "\": " + e.getMessage());
			writer.flush();
		}
	}
}
