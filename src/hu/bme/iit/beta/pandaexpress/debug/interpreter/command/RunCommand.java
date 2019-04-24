package hu.bme.iit.beta.pandaexpress.debug.interpreter.command;

import hu.bme.iit.beta.pandaexpress.debug.interpreter.Environment;
import hu.bme.iit.beta.pandaexpress.model.Stage;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public class RunCommand implements Command {

	@Override
	public String getName() {
		return "run";
	}

	@Override
	public void call(String[] arguments, OutputStream output, Environment environment) {
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(output));

		if (arguments.length < 3) {
			writer.println("Run requires at least 2 arguments");
			writer.flush();
			return;
		}

		try {
			Object object = getObject(arguments[1], environment);
			Method method = getMethod(object.getClass(), arguments[2], arguments.length - 3);

			Object[] parameters = new Object[arguments.length - 3];
			for (int i = 0; i < parameters.length; i++) {
				parameters[i] = getParameter(
						method.getParameterTypes()[i],
						arguments[i + 3],
						environment
				);
			}

			Object returnValue = method.invoke(object, parameters);
			if (method.getReturnType() != void.class && method.getReturnType() != Void.class) {
				writer.println(toString(returnValue, environment));
				writer.flush();
			}
		} catch (Exception e) {

			Throwable error;
			if (e instanceof InvocationTargetException) {
				error = ((InvocationTargetException) e).getTargetException();
			} else {
				error = e;
			}

			writer.println("Failed to run " + arguments[1] + "." + arguments[2] + ": " +
					error.getClass().getSimpleName() + " - " + error.getMessage());
			writer.flush();
		}
	}

	private static Method getMethod(Class c, String name, int parameterCount) throws NoSuchMethodException {
		for (Method method : c.getMethods()) {
			if (Objects.equals(method.getName(), name) && method.getParameterCount() == parameterCount) {
				return method;
			}
		}
		throw new NoSuchMethodException("Name: " + name + ", Parameter count: " + parameterCount);
	}

	private static Object getObject(String name, Environment environment) {
		if (Objects.equals(name, "Stage")) {
			return Stage.getInstance();
		} else {
			return environment.get(name);
		}
	}

	private static String toString(Object object, Environment environment) {
		if (object == null) {
			return "null";
		} else if (object == Stage.getInstance()) {
			return "Stage";
		} else {
			try {
				return environment.getName(object);
			} catch (Environment.EnvironmentException e) {
				return object.toString();
			}
		}
	}

	private static Object getParameter(Class type, String stringValue, Environment environment) {
		if (type == byte.class || type == Byte.class) {
			return Byte.parseByte(stringValue);
		} else if (type == short.class || type == Short.class) {
			return Short.parseShort(stringValue);
		} else if (type == int.class || type == Integer.class) {
			return Integer.parseInt(stringValue);
		} else if (type == long.class || type == Long.class) {
			return Long.parseLong(stringValue);
		} else if (type == float.class || type == Float.class) {
			return Float.parseFloat(stringValue);
		} else if (type == double.class || type == Double.class) {
			return Double.parseDouble(stringValue);
		} else if (type == char.class || type == Character.class) {
			if (stringValue.length() != 1) {
				throw new IllegalArgumentException("Can't convert \" " + stringValue + " \" to character");
			}
			return stringValue;
		} else if (type == boolean.class || type == Boolean.class) {
			switch (stringValue) {
				case "true":
					return true;
				case "false":
					return false;
				default:
					throw new IllegalArgumentException("Can't convert \" " + stringValue + " \" to boolean");
			}
		} else if (type == String.class) {
			return stringValue;
		} else {
			return getObject(stringValue, environment);
		}
	}
}
