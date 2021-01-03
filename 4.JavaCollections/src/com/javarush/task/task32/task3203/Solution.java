package com.javarush.task.task32.task3203;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.IntegerProperty;


/*
Пишем стек-трейс
*/
public class Solution {
    public static void main(String[] args) {
        String text = getStackTrace(new IndexOutOfBoundsException("fff"));
        System.out.println(text);

    }
//тестировал для Сычева
    public Solution(String st, int i){

    }

    public static String getStackTrace(Throwable throwable) {
        StringWriter w = new StringWriter();
        throwable.printStackTrace(new PrintWriter(w));
        return w.toString();
    }

}