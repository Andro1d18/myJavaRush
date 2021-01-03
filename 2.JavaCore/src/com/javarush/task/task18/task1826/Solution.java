package com.javarush.task.task18.task1826;

/* 
Шифровка
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
//        String str = "suz";
//        byte[] srtByte = str.getBytes();
//        for (Byte b :
//                srtByte) {
//            System.out.println(b);
//        }
//        System.out.println("suz".getBytes().toString());
//
//        byte b1 = 11;
//        byte b2 = 22;
//        System.out.println(b1^b2);
//

    String id = args[0];
    String fileName = args[1];
    String fileOutputName = args[2];


    String pKey = "suz";


        if ("-e".equals(id)){
        encrypt(fileName, fileOutputName, pKey);
    }else if ("-d".equals(id)){
        dencrupt(fileName, fileOutputName, pKey);
    }
}




    private static void dencrupt(String fileName, String fileOutputName, String pKey) throws IOException {


        FileInputStream fis = new FileInputStream(fileName);
        byte[] txt = new byte[fis.available()];
        fis.read(txt);
        fis.close();
        byte[] key = pKey.getBytes();
        byte[] res = new byte[txt.length];


        for (int i = 0; i < txt.length; i++) {
            res[i] = (byte) (txt[i] ^ key[i % key.length]);
        }


        FileOutputStream fos = new FileOutputStream(fileOutputName);
        fos.write(res);
        fos.close();
    }


    //Метод шифрования: читаем байты из исходного файла, добавляем к ним ключ, записываем получившиеся значения в новый файл
    private static void encrypt(String fileName, String fileOutputName, String pKey) throws IOException {


        //Читаем байты из исходного файла
        FileInputStream fis = new FileInputStream(fileName);
        byte[] txt = new byte[fis.available()];
        fis.read(txt);
        fis.close();


        //подготавливаем массив с ключем и результирующий массив
        byte[] key = pKey.getBytes();
        byte[] res = new byte[txt.length];


        //Заполняем результирующий массив
        for (int i = 0; i < txt.length; i++) {
            res[i] = (byte) (txt[i] ^ key[i % key.length]); //основной принцип XOR-шифрование
        }


        //Записываем получившийся массив в целевой файл
        FileOutputStream fos = new FileOutputStream(fileOutputName);
        fos.write(res);
        fos.close();
    }
}
