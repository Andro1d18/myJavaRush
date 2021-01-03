package com.javarush.task.task18.task1801;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* 
Максимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
//        FileInputStream fis = new FileInputStream("D:\\javarush\\maxbyte.txt");
      //  D:\\javarush\\jr2018.txt
        //  D:\\javarush\\тест.txt
//        int maxbyte = 0;
//        while (fis.available()>0){
//            int bt = fis.read();
//            if (maxbyte<bt) maxbyte=bt;
//        }
//        fis.close();
//        System.out.println(maxbyte);
//        System.out.println((char)(maxbyte));

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream fileStream = new FileInputStream(reader.readLine());
        int temp = 0;


        while (fileStream.available() >0){
            int readTemp = fileStream.read();
            if (temp < readTemp ) temp = readTemp;
            System.out.println(readTemp + " " + (char)readTemp);
            //System.out.println((char)readTemp);
        }
//        System.out.println(temp);
//        System.out.println((char)(temp));
        reader.close();
        fileStream.close();
    }
    }

