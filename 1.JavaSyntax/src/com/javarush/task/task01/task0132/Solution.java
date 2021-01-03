package com.javarush.task.task01.task0132;

/* 
Сумма цифр трехзначного числа
*/

import java.sql.SQLOutput;

public class Solution {
    public static void main(String[] args) {
        System.out.println(sumDigitsInNumber(546));
    }

    public static int sumDigitsInNumber(int number) {
        //напишите тут ваш код
        String s = number + "sdfgsdfgsdfgsergserbsbgsgdbfgbdfgb dfgb dfg bd gfbd fgbw34t4 t4g 4545bwb wrtb sb5 d1g6b5 1e4565 1wg45651w455 g1w455 1";
        String s2 = new String (number + "sdfgdfgsdfgsergserbsbgsgdbfgbdfgb dfgb dfg bd gfbd fgbw34t4 t4g 4545bwb wrtb sb5 d1g6b5 1e4565 1wg45651w455 g1w455 1");
        System.out.println(s.hashCode());
        System.out.println(s2.hashCode());
        char[] chars = s.toCharArray();
        int i = 0 ;
        for (char ch :
                chars) {
            i += ch - '0';
        }
        return i;
    }
}