package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;

/* 
Что внутри папки?
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Path filePath = Paths.get(reader.readLine());
        reader.close();

        if(!Files.isDirectory(filePath)) {
            System.out.println(filePath.toAbsolutePath().toString() + " - не папка");
            return;
        };
        MyFileVisitor myFileVisitor = new MyFileVisitor();
        Files.walkFileTree(filePath, myFileVisitor);

        System.out.println("Всего папок - " + (myFileVisitor.getCountDirectories()-1)); //-1 т.к. посчитана изначальная директория
        System.out.println("Всего файлов - " + myFileVisitor.getCountFiles());
        System.out.println("Общий размер - " + myFileVisitor.getTotalSize());

    }
}
