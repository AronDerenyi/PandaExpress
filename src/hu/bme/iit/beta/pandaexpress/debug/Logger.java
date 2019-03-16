package hu.bme.iit.beta.pandaexpress.debug;

import java.util.*;

public class Logger {

	private static Map<Object, String> aliases = new HashMap<>();
	private static Deque<String> functionStack = new LinkedList<>();

	public static void addAlias(Object object, String alias) {
		aliases.put(object, alias);
	}

	public static void startFunction(String functionName, Object... params) {
		//print tabs to follow stack tree
		writeTabs();
		functionStack.push(functionName);

		// print "function()"
		System.out.print(functionName + "(");

		// if there are params, print parameters "param1, param2" etc.
		for (Object param : params) {
			System.out.print(objectToString(param));
			if (param != params[params.length - 1]) {
				System.out.print(", ");
			}
		}

		System.out.print(") {");

		// linebreak
		System.out.println();
	}

	public static <T> T endFunction(T returnValue) {
		functionStack.pop();
		writeTabs();

		System.out.print("}");
		if (returnValue != null) System.out.print(" return " + objectToString(returnValue));
		System.out.println();

		return returnValue;
	}

	public static void endFunction() {
		endFunction(null);
	}

	private static String objectToString(Object obj) {
		if (obj == null) return "null";
		return aliases.getOrDefault(obj, obj.toString());
	}

	private static void writeTabs() {
		functionStack.forEach((function) -> System.out.print("\t"));
	}
}
