package com.javarush.task.task08.task0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Cамая длинная последовательность
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        //напишите тут ваш код

        List<Integer> list = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 10; i++) {
            list.add(Integer.parseInt(reader.readLine()));
        }

        int count = 0;
        int countMax = 0;
        for (int i = 0; i < 10; i++) {

            if (i != 0) {

                if (list.get(i).equals(list.get(i-1))){
                    count++;
                    countMax = (countMax > count) ? countMax : count;
                } else count = 1;

            } else {
                count++;
                countMax = count;
            }
        }
        System.out.println(countMax);


    }
}