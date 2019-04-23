package hu.bme.iit.beta.pandaexpress.debug.interpreter;

import hu.bme.iit.beta.pandaexpress.debug.interpreter.command.Command;
import hu.bme.iit.beta.pandaexpress.debug.interpreter.command.PrintCommand;

import java.io.*;
import java.util.Objects;

public class Interpreter {

	private static Command[] COMMANDS = {
			new PrintCommand()
	};

	private static void call(String name, String params, OutputStream output, Environment environment) {
		for (Command command : COMMANDS) {
			if (Objects.equals(command.getName(), name)) {
				command.call(params, output, environment);
				return;
			}
		}

		PrintWriter writer = new PrintWriter(new OutputStreamWriter(output));
		writer.println("Invalid command: " + name);
		writer.flush();
	}

	public Interpreter(InputStream input, OutputStream output, Environment environment) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));

		try {
			String line;
			while ((line = reader.readLine()) != null) {
				line = line.trim();
				int firstSpace = line.indexOf(' ');

				String name = firstSpace == -1 ? line : line.substring(0, firstSpace);
				String parameter = firstSpace == -1 ? "" : line.substring(firstSpace + 1);

				call(name, parameter, output, environment);
			}
		} catch (IOException e) {
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(output));
			writer.println("Error while reading commands.");
			writer.flush();
		}
	}
}
