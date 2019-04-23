package hu.bme.iit.beta.pandaexpress.debug.interpreter.command;

import hu.bme.iit.beta.pandaexpress.debug.interpreter.Environment;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class ResetCommand implements Command {

	@Override
	public String getName() {
		return "reset";
	}

	@Override
	public void call(String[] arguments, OutputStream output, Environment environment) {
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(output));

		if (arguments.length != 1) {
			writer.println("Reset doesn't have arguments");
			writer.flush();
			return;
		}

		try {
			environment.clear();
		} catch (Exception e) {
			writer.println("Failed to reset the environment: " +
					e.getClass().getSimpleName() + " - " + e.getMessage());
			writer.flush();
		}
	}
}
