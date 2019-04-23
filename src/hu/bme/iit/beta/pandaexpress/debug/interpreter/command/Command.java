package hu.bme.iit.beta.pandaexpress.debug.interpreter.command;

import hu.bme.iit.beta.pandaexpress.debug.interpreter.Environment;

import java.io.OutputStream;

public interface Command {

	String getName();

	void call(String params, OutputStream output, Environment environment);
}
