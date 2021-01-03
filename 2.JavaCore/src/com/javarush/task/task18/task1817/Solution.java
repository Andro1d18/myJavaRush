package com.javarush.task.task18.task1817;

/* 
Пробелы
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class Solution {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            FileInputStream fileInputStream = new FileInputStream(reader.readLine());
            byte[] byreArray = new byte[fileInputStream.available()];
            fileInputStream.read(byreArray);





        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static String round2symAfterPoint(double d) {
        return new DecimalFormat("##0.00").format(d);
    }
}
