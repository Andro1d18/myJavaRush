package com.javarush.task.task22.task2211;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/* 
Смена кодировки
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(args[0]);
        FileOutputStream fileOutputStream = new FileOutputStream(args[1]);


        Charset charset1251 = Charset.forName("Windows-1251");
        Charset charsetUTF8 = Charset.forName("UTF-8");
        byte[] buffer = new byte[fileInputStream.available()];
        while (fileInputStream.available()>0){
            fileInputStream.read(buffer);
            String s = new String(buffer,charset1251);
            buffer = s.getBytes(charsetUTF8);
            fileOutputStream.write(buffer);
        }

     //   fileOutputStream.write(new String(buffer, StandardCharsets.UTF_8).getBytes("Windows-1251"));

        fileInputStream.close();
        fileOutputStream.close();


    }
}
