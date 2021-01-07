package com.javarush.task.task07.task0706;

import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Улицы и дома
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        int[] houses = new int[15];
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < houses.length; i++) {
            houses[i] = Integer.parseInt(bufferedReader.readLine());
        }
        int countLivesOdd = 0;
        int countLivesEven = 0;
        for (int i = 0; i < houses.length; i++) {
            if (i == 0) {
                countLivesEven += houses[i];
                continue;
            }
            if (i % 2 == 0) {
                countLivesEven += houses[i];
            } else countLivesOdd += houses[i];
        }
//        System.out.println(countLivesEven);
//        System.out.println(countLivesOdd);
        String s = "В домах с нечетными номерами проживает больше жителей.";
        String s1 = "В домах с четными номерами проживает больше жителей.";
        String result = countLivesEven > countLivesOdd? s1: s;
        System.out.println(result);

    }
}
