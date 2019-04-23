package hu.bme.iit.beta.pandaexpress.debug.interpreter;

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
		if (contains(name)) throw new IllegalArgumentException("An object with the given name already exists");
		if (contains(object)) throw new IllegalArgumentException("The given object already exists");

		objectsByName.put(name, object);
		namesByObject.put(object, name);
	}

	public Object get(String name) {
		if (!contains(name))  throw new IllegalArgumentException("No object with the given name exist");
		return objectsByName.get(name);
	}

	public String getName(Object object) {
		if (!contains(object))  throw new IllegalArgumentException("The given object doesn't exists");
		return namesByObject.get(object);
	}

	public void remove(String name) {
		if (!contains(name))  throw new IllegalArgumentException("No object with the given name exist");
		Object object = objectsByName.remove(name);
		namesByObject.remove(object);
	}

	public void remove(Object object) {
		if (!contains(object))  throw new IllegalArgumentException("The given object doesn't exists");
		String name = namesByObject.remove(object);
		objectsByName.remove(name);
	}

	public void clear() {
		objectsByName.clear();
		namesByObject.clear();
	}
}
