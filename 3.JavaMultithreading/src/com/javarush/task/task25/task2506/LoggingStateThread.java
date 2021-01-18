package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread {
    private Thread thread;

    public LoggingStateThread(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        Thread.State currentState = thread.getState();
        System.out.println(currentState);
        super.run();
        while (!currentState.equals(State.TERMINATED)){
            Thread.State newState = thread.getState();
            if (currentState != newState){
                System.out.println(newState);
                currentState = newState;
            }
            //System.out.println(thread.getState());
        }
        this.interrupt();
    }
}
