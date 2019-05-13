package hu.bme.iit.beta.pandaexpress.controller;

import hu.bme.iit.beta.pandaexpress.model.Steppable;

import java.util.*;

/**
 * Timer class
 * Responsible for starting and stepping all the steppable objects in the game.
 */
public class Timer {
    /**
     * Nested class for a TimerTask thread that runs the tick method when called by the timer
     */
    class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            tick();
        }
    }

    /**
     * List of references to the steppable objects in the game
     */
    private Set<Steppable> objects;
    /**
     * Timer that starts the timer task and runs it every couple of milliseconds
     */
    private java.util.Timer timer;
    /**
     * The timer task that the timer runs
     */
    private MyTimerTask timerTask;

    /**
     * Default constructor. Initializes the object list, the timer and the timer task.
     */
    public Timer() {
        objects = Collections.synchronizedSet(new HashSet<>());
        timer = new java.util.Timer(true);
        timerTask = new MyTimerTask();
    }

    /**
     * Starts the timer
     */
    public void start() {
        long timeSpan = 400;
        timer.scheduleAtFixedRate(timerTask, 1000, timeSpan);
    }

    /**
     * Calls the step method of each object in the objects list
     * Also calls the Controller's step method
     */
    private void tick() {
        synchronized (objects) {
            for (Steppable object : objects) {
                object.step();
            }
            Controller.getInstance().step();
        }
    }

    /**
     * Stops the timer
     */
    public void stop() {
        timer.cancel();
    }

    /**
     * Registers a steppable object
     * @param object The object to be registered
     */
    public void addSteppable(Steppable object) {
        objects.add(object);
    }

    /**
     * Unregisters a steppable object
     * @param object The object to be unregistered
     */
    public void removeSteppable(Steppable object) {
        objects.remove(object);
    }
}
