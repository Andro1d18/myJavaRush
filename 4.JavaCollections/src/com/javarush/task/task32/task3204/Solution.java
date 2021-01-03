package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Генератор паролей
*/
public class Solution {
    private static HashSet<String> set = new HashSet();
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());

        Pattern pattern = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}");
        Matcher matcher = pattern.matcher("aAdehdjk");
        if (matcher.find()) System.out.println("find");
        else System.out.println("not find");
    }

    public static ByteArrayOutputStream getPassword() {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = upper.toLowerCase();
        String digits = "0123456789";

        String[] allsymbols = new String[]{upper,lower,digits};
        StringBuilder password = new StringBuilder(8);
        while (true) {

            for (int i = 0; i < 8; i++) {
                int randomArrayIndex = (int) (Math.random() * 3);
                String s = allsymbols[randomArrayIndex];
                int randomCharAt = (int) (Math.random() * (s.length()));
                password.append(s.charAt(randomCharAt));
            }
            //13.11.2019 остановился здесь. Нужно точно сделать паттерн. Так же нужно навесить проверку на уже созданные пароли
            //Pattern pattern = Pattern.compile("([A..Z]+)&([a..z]+)|d+"); //не правильно делаю шаблон для поиска Изучи заново..
            //         Правильно:
            //        (?=.*[0-9]) - строка содержит хотя бы одно число;
            //        (?=.*[a-z]) - строка содержит хотя бы одну латинскую букву в нижнем регистре;
            //        (?=.*[A-Z]) - строка содержит хотя бы одну латинскую букву в верхнем регистре;
            //        [0-9a-zA-Z]{8,} - строка состоит не менее, чем из 8 вышеупомянутых символов.
            //но сомнение относительно запятой - "8,". Она скорее всего не нужна

            Pattern pattern = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}");
            Matcher matcher = pattern.matcher(password);
            if (matcher.find()&&!set.contains(password.toString())) {
                set.add(password.toString());
                break;
            }
            password = new StringBuilder(8);
        }



        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] bytes = password.toString().getBytes();
        try {
            baos.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos;
    }
}