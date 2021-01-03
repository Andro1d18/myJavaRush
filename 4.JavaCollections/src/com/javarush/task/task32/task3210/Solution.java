package com.javarush.task.task32.task3210;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.nio.file.Paths;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) {
        Path path = Paths.get(args[0]); //путь к файлу
        int number = Integer.parseInt(args[1]); //число, позиция в файле;
        String text = args[2]; //текст
        char[] charsText = text.toCharArray();

        //Считать текст с файла начиная с позиции number, длинной такой же как и длинна переданного текста в третьем параметре.
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(path.toString(), "rw");
            raf.seek(number);
            byte[] arrayForRead = new byte[charsText.length];
            int countReadRaf = raf.read(arrayForRead, 0, charsText.length);
            String s = new String(arrayForRead);
          // for test  System.out.println("Прочитали их файла: "+s);

            //Если считанный текст такой же как и text, то записать в конец файла строку 'true', иначе записать 'false'.
            if (s.equals(text)){
                raf.seek(raf.length());
                raf.write("true".getBytes());
            } else {
                raf.seek(raf.length());
                raf.write("false".getBytes());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
