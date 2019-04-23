package hu.bme.iit.beta.pandaexpress.debug.interpreter.command;

import hu.bme.iit.beta.pandaexpress.debug.interpreter.Environment;
import hu.bme.iit.beta.pandaexpress.debug.interpreter.Interpreter;

import java.io.*;
import java.util.Objects;

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

		try {
			InputStream fileInput = new FileInputStream(arguments[1]);
			ByteArrayOutputStream fileOutput = new ByteArrayOutputStream();
			new Interpreter(fileInput, fileOutput, environment);
			fileInput.close();
			fileOutput.close();

			String[] outputLines = fileOutput.toString().split("\n");
			BufferedReader compareReader = new BufferedReader(new InputStreamReader(new FileInputStream(arguments[2])));

			int outputLineIndex = 0;
			int lineCount = 0;

			String outputLine;
			String compareLine;
			while ((compareLine = compareReader.readLine()) != null) {
				compareLine = compareLine.trim();
				lineCount++;

				if (compareLine.isEmpty()) {
					continue;
				}

				if (outputLineIndex == outputLines.length) {
					writer.println("Not enough output from the loaded file.");
					writer.flush();
					break;
				}

				outputLine = outputLines[outputLineIndex].trim();
				outputLineIndex++;

				if (!Objects.equals(outputLine, compareLine)) {
					writer.println("The output line didn't match the expected line on line " + lineCount + ": \""
							+ outputLine + "\" != \"" + compareLine + "\"");
					writer.flush();
					break;
				}
			}
			compareReader.close();

			if (outputLineIndex < outputLines.length) {
				writer.println("More output than expected");
				writer.flush();
			}
		} catch (Exception e) {
			writer.println("Failed to load and run \"" + arguments[1] + "\" and compare to \"" + arguments[2] + "\": " +
					e.getClass().getSimpleName() + " - " + e.getMessage());
			writer.flush();
		}
	}
}
