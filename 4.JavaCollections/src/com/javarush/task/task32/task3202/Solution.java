package com.javarush.task.task32.task3202;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/* 
Читаем из потока
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        StringWriter writer = getAllDataFromInputStream(new FileInputStream("testFile.log")); //D:\javarush\asas.csv  testFile.log
        System.out.println(writer.toString());
    }

    public static StringWriter getAllDataFromInputStream(InputStream is) throws IOException {

        StringWriter sw = new StringWriter();
        int r;
        if (is != null) {
            while ((r = is.read()) > 0) {
                sw.write(r);
            }
        }
        return sw;
    }
}