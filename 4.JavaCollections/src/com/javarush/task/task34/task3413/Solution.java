package com.javarush.task.task34.task3413;

/* 
Кеш на основании SoftReference
*/

public class Solution {
    public static void main(String[] args) {

        float fi =3234.12344f;
        int x = (int) fi;
        System.out.println(x);

        long l = 40002;
        short s = (short) l;
        System.out.println(s);

        SoftCache cache = new SoftCache();

//        for (long i = 0; i < 2_500_000; i++) {
//            cache.put(i, new AnyObject(i, null, null));
//
//        }
    }
}