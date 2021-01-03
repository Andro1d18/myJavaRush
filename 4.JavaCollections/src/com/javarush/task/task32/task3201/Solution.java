package com.javarush.task.task32.task3201;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) {

        Path path = Paths.get(args[0]); //путь к файлу
        int number = Integer.parseInt(args[1]); //число, позиция в файле;
        String text = args[2]; //текст

        try {
            RandomAccessFile raf = new RandomAccessFile(path.toString(),"rw");
            raf.seek(number);
            if (raf.length()<number){
                raf.seek(raf.length());
            }

            raf.write(text.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
