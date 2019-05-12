package hu.bme.iit.beta.pandaexpress.debug.interpreter;

import hu.bme.iit.beta.pandaexpress.model.Stage;

import java.util.HashMap;
import java.util.Map;

public class Environment {

	private final Map<String, Object> objectsByName = new HashMap<>();
	private final Map<Object, String> namesByObject = new HashMap<>();

	public boolean contains(String name) {
		return objectsByName.containsKey(name);
	}

	public boolean contains(Object object) {
		return namesByObject.containsKey(object);
	}

	public void add(String name, Object object) {
		if (contains(name)) throw new EnvironmentException("An object with the given name already exists: \"" + name + "\"");
		if (contains(object)) throw new EnvironmentException("The given object already exists: \"" + object + "\"");

		objectsByName.put(name, object);
		namesByObject.put(object, name);
	}

	public Object get(String name) {
		if (!contains(name))  throw new EnvironmentException("No object with the given name exist: \"" + name + "\"");
		return objectsByName.get(name);
	}

	public String getName(Object object) {
		if (!contains(object))  throw new EnvironmentException("The given object doesn't exists: \"" + object + "\"");
		return namesByObject.get(object);
	}

	public void remove(String name) {
		if (!contains(name))  throw new EnvironmentException("No object with the given name exist: \"" + name + "\"");
		Object object = objectsByName.remove(name);
		namesByObject.remove(object);
	}

	public void remove(Object object) {
		if (!contains(object))  throw new EnvironmentException("The given object doesn't exists: \"" + object + "\"");
		String name = namesByObject.remove(object);
		objectsByName.remove(name);
	}

	public void clear() {
		objectsByName.clear();
		namesByObject.clear();
		Stage.getInstance().resetScore();
	}

	public static class EnvironmentException extends RuntimeException {

		public EnvironmentException() {
			super();
		}

		public EnvironmentException(String message) {
			super(message);
		}

		public EnvironmentException(String message, Throwable cause) {
			super(message, cause);
		}

		public EnvironmentException(Throwable cause) {
			super(cause);
		}

		protected EnvironmentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
			super(message, cause, enableSuppression, writableStackTrace);
		}
	}
}
