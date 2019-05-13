package hu.bme.iit.beta.pandaexpress.controller;

import hu.bme.iit.beta.pandaexpress.model.Steppable;

import java.util.*;

public class Timer {
    class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            tick();
        }
    }
    private Set<Steppable> objects;
    private java.util.Timer timer;
    private MyTimerTask timerTask;

    public Timer() {
        objects = Collections.synchronizedSet(new HashSet<>());
        timer = new java.util.Timer(true);
        timerTask = new MyTimerTask();
    }
    public void start() {
        long timeSpan = 400;
        timer.scheduleAtFixedRate(timerTask, 1000, timeSpan);
    }
    private void tick() {
        synchronized (objects) {
            for (Steppable object : objects) {
                object.step();
            }
        }
        Controller.getInstance().step();
    }
    public void stop() {
        timer.cancel();
    }
    public void addSteppable(Steppable object) {
        objects.add(object);
    }
    public void removeSteppable(Steppable object) {
        objects.remove(object);
    }
}
