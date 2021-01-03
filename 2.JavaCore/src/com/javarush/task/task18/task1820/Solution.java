package com.javarush.task.task18.task1820;

/* 
Округление чисел
D:\javarush\тест2.txt
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream fileInputStream1 = new FileInputStream(reader.readLine());

        ArrayList<String> allLine = new ArrayList<>();
        ArrayList<String> allDouble = new ArrayList<>();


        BufferedReader readerFile = new BufferedReader(new InputStreamReader(fileInputStream1));
        String str;
        while ((str = readerFile.readLine()) != null){
            allLine.add(str);
        }

        //ищем дробные в файле
        Pattern pattern = Pattern.compile("-?\\d+\\.\\d+"); // поиск всех дробных чисел
        for (int i = 0; i < allLine.size(); i++) {
            Matcher matcher = pattern.matcher(allLine.get(i));

            while(matcher.find()){
                String str2 = allLine.get(i).substring(matcher.start(),matcher.end());
                allDouble.add(str2);
            }
        }

        //Пишем в файл
        FileOutputStream fileOutputStream = new FileOutputStream(reader.readLine());
        for (String doubleString :
                allDouble) {
            long lo = Math.round(Double.parseDouble(doubleString));
            fileOutputStream.write((String.valueOf(lo) + " ").getBytes());
        }


    }
}
