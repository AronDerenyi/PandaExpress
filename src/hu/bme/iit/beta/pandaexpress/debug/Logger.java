package hu.bme.iit.beta.pandaexpress.debug;

import java.util.*;

public class Logger {

	private static Map<Object, String> aliases = new HashMap<>();
	private static Deque<String> functionStack = new LinkedList<>();
	private static boolean enabled = true;

	public static boolean isEnabled() {
		return enabled;
	}

	/**
	 * After called, Logger functions will be able to print on stdout
	 */
	public static void enable() {
		enabled = true;
	}

	/**
	 * After called, Logger won't print on stdout
	 */
	public static void disable() {
		enabled = false;
	}

	/**
	 * Saves a new object in Logger's database
	 * Whenever this object should be printed, Logger will use its alias
	 * @param object the object that is to be saved
	 * @param alias the string that should be printed, when object should be printed
	 * @param <T> the type of the object
	 * @returns the object
	 */
	public static <T> T addAlias(T object, String alias) {
		aliases.put(object, alias);
		return object;
	}

	/**
	 * Prints on stdout, that a function was called
	 * << object.functionName(param1, param2, param3) { \n
	 * @param object tha objects that's function was called
	 * @param functionName the name of the function that was called
	 * @param params an array of parameters that was passed to the function
	 */
	public static void startFunction(Object object, String functionName, Object... params) {
		//print tabs to follow stack tree
		printTabs();
		functionStack.push(functionName);

		// print "object.function()"
		if(object != null)
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

	/**
	 * Same as above, used when the object that's function was called is irrelevant
	 */
	public static void startFunction(String functionName, Object... params) {
		startFunction(null, functionName, params);
	}

	/**
	 * Prints on stdout, that a function ended
	 * << return object; \n }
	 * @param returnValue the object that the function returned
	 * @param <T> the type of the returned object
	 * @returns the object that the function would return otherwise
	 */
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

	/**
	 * Prints on stdout, that a void function ended
	 */
	public static void endFunction() {
		endFunction(null);
	}

	/**
	 * Stringifies an object
	 * @param obj the object that should be stringified
	 * @return null, when object was null
	 * @return the object's alias if it's stored
	 * @return standard toString value otherwise
	 */
	private static String objectToString(Object obj) {
		if (obj == null) return "null";
		return aliases.getOrDefault(obj, obj.toString());
	}

	/**
	 * logs string on stdout, when Logger is enabled
	 * @param object
	 */
	private static void print(Object object) {
		if (enabled) {
			System.out.print(object);
		}
	}

	/**
	 * Prints tabulators to make the printed function stack tree readable
	 */
	private static void printTabs() {
		if (enabled) {
			functionStack.forEach((function) -> System.out.print("\t"));
		}
	}

	/**
	 * Prints a newline character if Logger is enabled
	 */
	private static void printNewLine() {
		if (enabled) {
			System.out.println();
		}
	}
}
