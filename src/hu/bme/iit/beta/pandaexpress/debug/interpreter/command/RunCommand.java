package hu.bme.iit.beta.pandaexpress.debug.interpreter.command;

import hu.bme.iit.beta.pandaexpress.debug.interpreter.Environment;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class RunCommand implements Command {

	@Override
	public String getName() {
		return "run";
	}

	@Override
	public void call(String[] arguments, OutputStream output, Environment environment) {
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(output));

		if (arguments.length != 3) {
			writer.println("Run requires at least 2 arguments");
			writer.flush();
			return;
		}

		writer.println("Running function \"" + arguments[2] + "\" of \"" + arguments[1] + "\"");

		writer.flush();
	}
}
