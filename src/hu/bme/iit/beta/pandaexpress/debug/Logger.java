package hu.bme.iit.beta.pandaexpress.debug;

import java.util.*;

public class Logger {

	private static Map<Object, String> aliases = new HashMap<>();
	private static Deque<String> functionStack = new LinkedList<>();
	private static boolean enabled = true;

	public static boolean isEnabled() {
		return enabled;
	}

	public static void enable() {
		enabled = true;
	}

	public static void disable() {
		enabled = false;
	}

	public static <T> T addAlias(T object, String alias) {
		aliases.put(object, alias);
		return object;
	}

	public static void startFunction(Object object, String functionName, Object... params) {
		//print tabs to follow stack tree
		printTabs();
		functionStack.push(functionName);

		// print "object.function()"
		print(objectToString(object) + ".");
		print(functionName + "(");

		// if there are params, print parameters "param1, param2" etc.
		for (Object param : params) {
			print(objectToString(param));
			if (param != params[params.length - 1]) {
				print(", ");
			}
		}

		print(") {");

		// linebreak
		printNewLine();
	}

	public static void startFunction(String functionName, Object... params) {
		startFunction(null, functionName, params);
	}

	public static <T> T endFunction(T returnValue) {
		if (returnValue != null) {
			printTabs();
			print("return " + objectToString(returnValue));
			printNewLine();
		}

		functionStack.pop();
		printTabs();

		print("}");
		printNewLine();

		return returnValue;
	}

	public static void endFunction() {
		endFunction(null);
	}

	private static String objectToString(Object obj) {
		if (obj == null) return "null";
		return aliases.getOrDefault(obj, obj.toString());
	}

	private static void print(Object object) {
		if (enabled) {
			System.out.print(object);
		}
	}

	private static void printTabs() {
		if (enabled) {
			functionStack.forEach((function) -> System.out.print("\t"));
		}
	}

	private static void printNewLine() {
		if (enabled) {
			System.out.println();
		}
	}
}
