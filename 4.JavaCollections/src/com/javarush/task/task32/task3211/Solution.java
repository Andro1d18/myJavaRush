package com.javarush.task.task32.task3211;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

/* 
Целостность информации
*/

public class Solution {
    public static void main(String... args) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(new String("test string"));
        oos.flush();
        System.out.println(compareMD5(bos, "5a47d12a2e3f9fecf2d9ba1fd98152eb")); //true

    }

    public static boolean compareMD5(ByteArrayOutputStream byteArrayOutputStream, String md5) throws Exception {

        //Устанавливаем тип MD5
        MessageDigest md = MessageDigest.getInstance("md5");
        md.reset();
        //Вычисляем хеш в виде массива байт
        md.update(byteArrayOutputStream.toByteArray());
        byte[] bytes = md.digest();
        //конвертируем массив байт в 16-тиричную систему считсления и так как хеш может начинаться с 0, то добавляем
        //нули вначало, чтобы получилась общая длинна в 32 символа
        String s2 = new BigInteger(1, bytes).toString(16);
        while(s2.length() < 32 ){
            s2 = "0"+s2;
        }
        return s2.equals(md5);
    }
}
