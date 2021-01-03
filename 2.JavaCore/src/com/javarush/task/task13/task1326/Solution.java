package com.javarush.task.task13.task1326;

/* 
Сортировка четных чисел из файла
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Solution {
    public static void main(String[] args) throws IOException {
        // напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream fileInputStream = new FileInputStream(reader.readLine());

        ArrayList<Integer> numbers = new ArrayList<Integer>();
        byte[] buff = new byte[fileInputStream.available()];
        fileInputStream.read(buff);
        StringBuilder stringBuilder = new StringBuilder();
        boolean flag = false;

        //
        for (int i = 0; i < buff.length; i++) {
            byte b = buff[i];
            if (b!=10){
                stringBuilder.append((char)b);
                flag = true;
                if (i ==buff.length - 1) numbers.add(Integer.parseInt(stringBuilder.toString()));
            }
            else {
                if (flag){
                    numbers.add(Integer.parseInt(stringBuilder.toString()));
                    flag = false;
                }
                stringBuilder.setLength(0);
            }
        }

       Collections.sort(numbers);

        printEvenNumbersArray(numbers);

        fileInputStream.close();
        reader.close();
    }
    public static void printArray(ArrayList<Integer> arrayList){
        for (Integer obj :
                arrayList) {
            System.out.println(obj);
        }
    }public static void printEvenNumbersArray(ArrayList<Integer> arrayList){
        for (Integer obj :
                arrayList) {
            if (obj%2==0) System.out.println(obj);
        }
    }
}
