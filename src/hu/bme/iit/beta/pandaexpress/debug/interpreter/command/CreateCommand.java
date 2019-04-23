package hu.bme.iit.beta.pandaexpress.debug.interpreter.command;

import hu.bme.iit.beta.pandaexpress.debug.interpreter.Environment;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class CreateCommand implements Command {

	@Override
	public String getName() {
		return "create";
	}

	@Override
	public void call(String[] arguments, OutputStream output, Environment environment) {
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(output));

		if (arguments.length != 3) {
			writer.println("Create requires 2 arguments");
			writer.flush();
			return;
		}

		writer.println("Created \"" + arguments[2] + "\" of type \"" + arguments[1] + "\"");
		writer.flush();
	}
}
