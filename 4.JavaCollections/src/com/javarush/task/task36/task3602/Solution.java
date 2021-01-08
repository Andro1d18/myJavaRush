package com.javarush.task.task36.task3602;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

/* 
Найти класс по описанию Ӏ Java Collections: 6 уровень, 6 лекция
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
//        Method[] methods = Collections.class.getDeclaredMethods();
//        for (Method method :
//                methods) {
//            System.out.println(method.getName());
//        }
        Class[] classes = Collections.class.getDeclaredClasses();
        for (Class clazz :
                classes) {
            clazz.getSuperclass()
            System.out.println(clazz.getName());
        }
        return null;
    }
}
