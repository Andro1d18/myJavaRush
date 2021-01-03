package com.javarush.task.task14.task1419;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/* 
Нашествие исключений
*/

public class Solution {
    public static List<Exception> exceptions = new ArrayList<Exception>();

    public static void main(String[] args) {
        initExceptions();

        for (Exception exception : exceptions) {
            System.out.println(exception);
        }
    }

    private static void initExceptions() {   //it's first exception
        try {
            float i = 1 / 0;

        } catch (Exception e) {
            exceptions.add(e);
        }

        //напишите тут ваш код

        try{
            File file = new File("C:\\asdasd\\rer.txt");
            FileInputStream fileInputStream = new FileInputStream(file);
        } catch (Exception e){
            exceptions.add(e);
        }

        try {
            char[] chars = new char[1];
            char ch = chars[2];
        }catch (Exception e){
            exceptions.add(e);
        }
        exceptions.add(new IllegalAccessException());
        exceptions.add(new EOFException());
        exceptions.add(new NoSuchFieldException());
        exceptions.add(new NoSuchMethodException());
        exceptions.add(new ClassCastException());


    }
}
