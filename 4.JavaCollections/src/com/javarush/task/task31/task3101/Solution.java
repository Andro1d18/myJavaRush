package com.javarush.task.task31.task3101;



import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
Проход по дереву файлов
D:\javarush\ D:\javarush\maxbyte.txt

*/
public class Solution {

     ArrayList<String> listPathSting = new ArrayList<String>();

    public static void main(String[] args) throws IOException {

        Path path = Paths.get(args[0]);
        File fileRes = new File(args[1]);


        File fileResult = new File (fileRes.getParent() + "/allFilesContent.txt"); // данной командой сам файл на диске НЕ создаётся

        //В основе FileUtils.renameFile лежит File.renameTo(dest), который дейстивтельно (в случае с windows)
        // переименовывает файл
        FileUtils.renameFile(fileRes, fileResult);

        // fileRes = fileResult;
        //список для хранения относительных имен файлов
        ArrayList<String> listPathSting = new ArrayList<String>();

        //заполняем список listFile файлами, а список listPathString названиями файлов (рекурсионно)
        ArrayList<File> listFile = listf(path.toString(), listPathSting);

            //Проверка как заполнились списки
//            System.out.println();
//            System.out.println();
//            System.out.println();
//
//            for (String str :
//                    listPathSting) {
//                System.out.println(str);
//            }

            sortNameFiles(listFile);

//            //проверка сортировки
//            System.out.println();
//            System.out.println();
//            System.out.println();
//
//            for (File file :
//                    listFile) {
//
//                System.out.println(file.getAbsolutePath() + " " + file.length());
//            }
//            System.out.println();
//            // проверка сколько весит рузельтирующий файл перед началом записи.
//           System.out.println(fileResult.getAbsolutePath() + " " + fileResult.length());

            FileOutputStream fileOutputStream = null;
            FileInputStream fileInputStream = null;

            try {
                // !!Важно открыть поток именно на файл (не на fileResult.getAbsolutePath())!!
                fileOutputStream = new FileOutputStream(fileResult);

            for (File file :
                    listFile) {
                if (file.length() <= 50 & file.isFile()) {
                    fileInputStream = new FileInputStream(file.getAbsolutePath());

                    while (fileInputStream.available()>0) {
                        int data = fileInputStream.read();

                        fileOutputStream.write(data);
                    }
                    fileOutputStream.write("\n".getBytes());

                }
            }

            fileOutputStream.close();
                fileInputStream.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        fileOutputStream.close();
        fileInputStream.close();



    }
    //сортирует только по названию файла (путь отсекается)
    public static void sortNameFiles(ArrayList<File> listf){

        for (int i = 0; i < listf.size(); i++) {
            for (int j = i+1; j < listf.size(); j++) {
                if (listf.get(i).getName().compareTo(listf.get(j).getName()) > 0) {
                    File f = listf.get(i);
                    listf.set(i, listf.get(j));;
                    listf.set(j, f );
                }
            }
        }
    }

    //Возвращает список всех файлов внутри папки, в т.ч. вложенных папок
    public static ArrayList<File> listf(String directoryName, ArrayList<String> listFilesName) {
        File directory = new File(directoryName);

        ArrayList<File> resultList = new ArrayList<File>();

        // get all the files from a directory
        File[] fList = directory.listFiles();
        resultList.addAll(Arrays.asList(fList));
        for (File file : fList) {
            listFilesName.add(file.getName());
            if (file.isFile()) {
      //          System.out.println(file.getAbsolutePath());
            } else if (file.isDirectory()) {
                resultList.addAll(listf(file.getAbsolutePath(), listFilesName));
            }
        }
        //System.out.println(fList);
        return resultList;
    }
}
