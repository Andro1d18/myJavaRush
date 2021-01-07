package com.javarush.task.task09.task0906;

/* 
Логирование стек-трейса
Реализовать метод log.
Он должен выводить на экран имя класса и имя метода (в котором вызывается метод log), а также переданное сообщение.
Имя класса, имя метода и сообщение разделить двоеточием с пробелом.

Пример вывода:
com.javarush.task.task09.task0906.Solution: main: In main method


*/

public class Solution {
    public static void main(String[] args) {
        log("In main method");
    }

    public static void log(String text) {
        //напишите тут ваш код
        StackTraceElement[] stackTraceElement = Thread.currentThread().getStackTrace();
        //System.out.println(Solution.getClass().getSimpleName());
        System.out.print(stackTraceElement[2].getClassName());
        System.out.print(": " +stackTraceElement[2].getMethodName());
        System.out.println(": " + text);
//        for (StackTraceElement st :
//                stackTraceElement) {
//            System.out.println(st.getClassName()+ " + " + st.getMethodName());
//        }
//        System.out.println();
    }
}
