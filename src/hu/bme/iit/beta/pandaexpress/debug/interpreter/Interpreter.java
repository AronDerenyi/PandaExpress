package hu.bme.iit.beta.pandaexpress.debug.interpreter;

import hu.bme.iit.beta.pandaexpress.debug.interpreter.command.Command;
import hu.bme.iit.beta.pandaexpress.debug.interpreter.command.PrintCommand;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Interpreter {

	private static Command[] COMMANDS = {
			new PrintCommand()
	};

	private static String[] toArgumentArray(String arguments) {
		List<String> argumentList = new LinkedList<>();
		argumentList.add(arguments);

		arguments = arguments.trim();
		while (!arguments.isEmpty()) {
			String argument;
			int nextArgument;

			if (arguments.charAt(0) == '\'') {
				int nextQuotes = arguments.indexOf('\'');
				argument = arguments.substring(1, nextQuotes);
				nextArgument = nextQuotes + 1;
			} else if (arguments.charAt(0) == '\"') {
				int nextQuotes = arguments.indexOf('\"');
				argument = arguments.substring(1, nextQuotes);
				nextArgument = nextQuotes + 1;
			} else {
				int nextSpace = arguments.indexOf(' ');
				argument = arguments.substring(nextSpace);
				nextArgument = nextSpace + 1;
			}

			argumentList.add(argument);
			arguments = arguments.substring(nextArgument).trim();
		}

		return argumentList.toArray(new String[0]);
	}

	private static void call(String name, String[] arguments, OutputStream output, Environment environment) {
		for (Command command : COMMANDS) {
			if (Objects.equals(command.getName(), name)) {
				command.call(arguments, output, environment);
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
				String arguments = firstSpace == -1 ? "" : line.substring(firstSpace + 1);

				String[] argumentArray = toArgumentArray(arguments);
				call(name, argumentArray, output, environment);
			}
		} catch (IOException e) {
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(output));
			writer.println("Error while reading commands.");
			writer.flush();
		}
	}
}
