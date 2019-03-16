package hu.bme.iit.beta.pandaexpress.debug;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Logger {
    private static Map<Object, String> gameObjects;
    private static Stack<String> functionStack;

    static {
        gameObjects = new HashMap<>();
        functionStack = new Stack<String>();
    }

    public static void add(Object newObject, String name){
        gameObjects.put(newObject, name);
    }

    public static void startFunction(String functionName, Object ...params){
        //print tabs to follow stack tree
        writeTabs();

        // print "> function()
        functionStack.push(functionName);
        System.out.print("> " + functionName + "( ");

        // if there are params, print parameters [param1] [param2] etc.
        for(Object param : params){
            System.out.print("[" + getObjectString(param) + "] ");
        }

        // linebreak
        System.out.print(")\n");
    }

    public static void endFunction(){
        endFunction(null);
    }

    private static String getObjectString(Object obj){
        if(obj == null) return "null";
        String parameterString;
        // if parameter is an object in game:
        if((parameterString = gameObjects.get(obj)) != null)
            return parameterString;

        // then it must be a native type, with an appropriate toString method
        return obj.toString();
    }

    private static void writeTabs(){
        for(String function : functionStack)
            System.out.print("\t");
    }

    public static void endFunction(Object returnValue){
        String func = functionStack.pop();
        writeTabs();
        System.out.print("< ");
        if(returnValue != null)
            System.out.print("return " + getObjectString(returnValue));
        System.out.print("\n");
    }
}
