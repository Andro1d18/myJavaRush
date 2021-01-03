package com.javarush.task.task19.task1912;

/* 
Ридер обертка 2
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream consoleStream = System.out;
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        testString.printSomething();

        System.setOut(consoleStream);
        String str = outputStream.toString();



        System.out.println(str.replaceAll("tt","??"));
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a ttext for tt testing");
        }
    }
}
