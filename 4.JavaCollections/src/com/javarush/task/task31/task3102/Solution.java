package com.javarush.task.task31.task3102;



import java.io.File;
import java.io.IOException;
import java.util.*;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {

        List<String> resultListString = new ArrayList<>();

        ArrayDeque<File> filesQueue = new ArrayDeque<>();


        File rootDir = new File(root);

        filesQueue.addAll(Arrays.asList(rootDir.listFiles()));

        while (!filesQueue.isEmpty()){
            File file = filesQueue.removeFirst(); //доставём первый элемент из очереди и одновременно удаляем его из очереди
            if (!file.isDirectory()) {
                resultListString.add(file.getAbsolutePath()); //если элемент - файл, добавляем название файла в результирующий список
            } else {
                filesQueue.addAll(Arrays.asList(file.listFiles())); //иначе, добавляем в очередь все файлы из директории
            }
        }
        return resultListString;

    }

    public static void main(String[] args) throws IOException {

//        List<String> list = getFileTree("D:/javarush/");
//
//        System.out.println();
//        System.out.println();
//        System.out.println();
//
//        for (String s :
//                list) {
//            System.out.println(s);
//        }
    }
}
