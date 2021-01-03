package com.javarush.task.task16.task1617;

/* 
Отсчет на гонках
*/

public class Solution {
    public static volatile int numSeconds = 4;

    public static void main(String[] args) throws InterruptedException {
        RacingClock clock = new RacingClock();
        //add your code here - добавь код тут
        Thread.sleep(3500);
        if (clock.isAlive()) clock.interrupt();
    }

    public static class RacingClock extends Thread {
        public RacingClock() {
            start();
        }

        public void run() {
            //add your code here - добавь код тут
            try {
                for (int i = 0; i < numSeconds; i++) {
                    //numSeconds--;
                    System.out.print((numSeconds - i) + " ");

                    Thread.sleep(1000);
                }
                System.out.print("Марш!");
            }catch (InterruptedException ie){
                System.out.print(" Прервано");
            }

        }
    }
}
