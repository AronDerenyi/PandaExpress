package hu.bme.iit.beta.pandaexpress.debug.interpreter.command;

import hu.bme.iit.beta.pandaexpress.debug.interpreter.Environment;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class TestCommand implements Command {

	@Override
	public String getName() {
		return "test";
	}

	@Override
	public void call(String[] arguments, OutputStream output, Environment environment) {
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(output));

		if (arguments.length != 3) {
			writer.println("Test requires 2 arguments");
			writer.flush();
			return;
		}

		writer.println("Testing the output of \"" + arguments[1] + "\" against \"" + arguments[2] + "\"");

		writer.flush();
	}
}
