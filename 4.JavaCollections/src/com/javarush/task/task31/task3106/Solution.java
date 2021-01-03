package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/*
Разархивируем файл

*/
public class Solution {
    public static void main(String[] args) throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream(args[0]);
        //формируем массив частей архива
        String[] pathsZip = new String[args.length-1];
        System.arraycopy(args, 1,pathsZip,0,args.length-1);
        Arrays.sort(pathsZip); //сортируем их

        //создаём поток для каждой части архива
        List listInputStream = new ArrayList<>();
        for (String s :
                pathsZip) {
            listInputStream.add(new FileInputStream(s));
        }
        //создаём ZIS на основе SequenceInputStream, котоый получает перечисление в виде списка потоков частей архива
        ZipInputStream zipInputStream = new ZipInputStream(new SequenceInputStream(Collections.enumeration(listInputStream)));

        //читаем поток
        ZipEntry zipEntry;
        while ((zipEntry = zipInputStream.getNextEntry()) != null) {
            byte[] buffer = new byte[8128];

            //записываем в резултирующий файл
            int count;
            while ((count = zipInputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, count);
            }
            zipInputStream.closeEntry();
            fileOutputStream.flush();
        }
        zipInputStream.close();
        fileOutputStream.close();
    }
}
