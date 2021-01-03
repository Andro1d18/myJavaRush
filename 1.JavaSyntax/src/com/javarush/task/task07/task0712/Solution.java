package com.javarush.task.task07.task0712;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Самые-самые
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        ArrayList<String> list = new ArrayList<>();
        int count = 0;
        int minLenght = 0;
        int maxLenght = 0;
        int positionMinLenght = 0;
        int positionMaxLenght = 0;

        BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();
        minLenght = s.length();
        maxLenght = s.length();
        list.add(s);

        for (int i = 1; i < 10; i++) {
            s = bufferedReader.readLine();
            list.add(s);

            if (minLenght > s.length()) {
                minLenght = s.length();
                positionMinLenght = i;
            }
            if (maxLenght < s.length()){
                maxLenght = s.length();
                positionMaxLenght = i;
            }
        }

        if (positionMinLenght < positionMaxLenght){
            System.out.println(list.get(positionMinLenght));
        }else System.out.println(list.get(positionMaxLenght));
    }
}
