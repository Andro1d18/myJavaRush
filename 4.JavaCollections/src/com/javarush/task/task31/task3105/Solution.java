package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
pathFile  D:\javarush\asas.csv
pathZipArchive  D:\javarush\archive.zip
*/
public class Solution {
    public static void main(String[] args) throws IOException {

        Path pathFile = Paths.get(args[0]);
        Path pathZipArchive = Paths.get(args[1]);

        FileInputStream fileInputStream = new FileInputStream(pathZipArchive.toString());
        ZipInputStream zipInputStream = new ZipInputStream(fileInputStream); //читаем из архива

        Map<String, ByteArrayOutputStream> mapZipEntryBaos = new HashMap<>(); //будем хранить все ZipEntry и их содержимое в map

        boolean flagCloneFile = false; //флаг был ли в архиве целевой файл (который задается в параметрах запуска)
        String strZipExisting = "";//подготавливаем String для хранения существующего в архиве файла, если он существует

        ZipEntry zipEntry;

        //читаем содержимое архива и записываем в map и ставим флаг, если целевой файл уже существут в архиве
        while ((zipEntry = zipInputStream.getNextEntry()) != null){

            //выясняем конечное имя файла в архиве (т.е. без папок в пути файла)
            String[] fullEntryName = zipEntry.getName().split("/");
            String fileName = fullEntryName[fullEntryName.length-1]; //последнее в массиве - как раз название самого файла

            //System.out.println(fileName +" equals to " + pathFile.getFileName().toString() + "?");
            if (fileName.equals(pathFile.getFileName().toString())){
                flagCloneFile = true;

                strZipExisting = zipEntry.getName();
                //System.out.println("Путь где лежит найденный файл: " + existingFileInArchive.getName());
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = zipInputStream.read(buffer))>0)
                baos.write(buffer, 0,count);
            mapZipEntryBaos.put(zipEntry.getName(),baos);
        }

        FileOutputStream fileOutputStream = new FileOutputStream(pathZipArchive.toString());
        ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);

        //делаем вторую попытку: мапа с EntrySet.getName и baos
        for (Map.Entry<String, ByteArrayOutputStream> mapEB :
                    mapZipEntryBaos.entrySet()){
            String zipName = mapEB.getKey();
            ByteArrayOutputStream baos = mapEB.getValue();
            //Если это существующий файл, который мы пометили ранее, то заменяем его на файл пришедший в параметрах
            if (strZipExisting.equals(zipName)){
                zipOutputStream.putNextEntry(new ZipEntry(strZipExisting));
                Files.copy(pathFile,zipOutputStream);
                zipOutputStream.closeEntry();
            } else { //иначе пишем в файл, то что сохраниле в мапу
                zipOutputStream.putNextEntry(new ZipEntry(zipName));
                zipOutputStream.write(baos.toByteArray());
                baos.flush();
                baos.close();
                zipOutputStream.closeEntry();
            }

        }
        //добвляем файл, пришедший в параметрах программы, в папку нью, если этого файла не было внутри архива
        if (!flagCloneFile){
            zipOutputStream.putNextEntry(new ZipEntry("new/"+ pathFile.getFileName().toString()));
            Files.copy(pathFile, zipOutputStream);
            zipOutputStream.closeEntry();
        }
        zipInputStream.close();
        zipOutputStream.close();

    }
}
