package com.javarush.task.task20.task2012;

import java.io.*;

/* 
OutputToConsole
*/
public class Solution {
    public static String greeting = "Hello world";

    /**
     * OutputToConsole is an inner base class for improving your attentiveness.
     * An OutputToConsole object encapsulates the information needed
     * for displaying the [greeting] variable to the console.
     * @author JavaRush
     */
    public static class OutputToConsole implements Externalizable {
        private int counter;

        /**
         * @param out A stream for externalization
         * @throws java.io.IOException
         */
        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeInt(counter);
        }

        /**
         * @param in A stream for de-externalization
         * @throws java.io.IOException
         * @throws ClassNotFoundException
         */
        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            counter = in.readInt();
        }

        public OutputToConsole() {
        }

        /**
         * Class constructor that sets the private counter field.
         */


        public OutputToConsole(int counter) {
            this.counter = counter;
        }

        /**
         * Prints greeting message to console counter times.
         */
        public void printMessage() {
            for (int i = 0; i < counter; i++) {
                System.out.println(greeting);
            }
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        OutputToConsole outputToConsole = new OutputToConsole(5);
        outputToConsole.printMessage();

        FileOutputStream fos = new FileOutputStream("D:\\javarush\\javaOne.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(outputToConsole);

        FileInputStream fis = new FileInputStream("D:\\javarush\\javaOne.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);

        OutputToConsole outputToConsoleRead = (OutputToConsole) ois.readObject();
        System.out.println();
        outputToConsoleRead.printMessage();

        fis.close();
        fos.close();
        oos.close();
        ois.close();
    }
}
