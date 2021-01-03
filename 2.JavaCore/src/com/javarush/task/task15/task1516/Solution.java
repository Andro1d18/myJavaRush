package com.javarush.task.task15.task1516;

/* 
Значения по умолчанию
*/

public class Solution {
    int intVar ;//= 1;
    double doubleVar ;//= 1.2;
    Double DoubleVar ;//= new Double(1.33);
    boolean booleanVar ;//= true;
    Object ObjectVar ;//= new Object();
    Exception ExceptionVar ;//= new Exception();
    String StringVar ;//= " s";

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.intVar);
        System.out.println(sol.doubleVar);
        System.out.println(sol.DoubleVar);
        System.out.println(sol.booleanVar);
        System.out.println(sol.ObjectVar);
        System.out.println(sol.ExceptionVar);
        System.out.println(sol.StringVar);
    }
}
