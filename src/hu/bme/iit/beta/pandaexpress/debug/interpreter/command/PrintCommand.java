package hu.bme.iit.beta.pandaexpress.debug.interpreter.command;

import hu.bme.iit.beta.pandaexpress.debug.interpreter.Environment;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class PrintCommand implements Command {

	@Override
	public String getName() {
		return "print";
	}

	@Override
	public void call(String[] arguments, OutputStream output, Environment environment) {
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(output));
		writer.println(arguments[0]);
		writer.flush();
	}
}
