package com.javarush.task.task19.task1907;

/* 
Считаем слово
*/

import java.io.*;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();


        // читаем все символы из файла, записываем в итоговую строку.
        FileInputStream fileInputStream = new FileInputStream(fileName);
        FileReader fileReader = new FileReader(fileName);
        char[] buff = new char[fileInputStream.available()];
        int countChars = fileReader.read(buff);
        String fullFileString = String.valueOf(buff);




        //создаём паттерн на все знаки препинания +\n (новая строка) и \r(перевод каретки)
        Pattern pattern = Pattern.compile("[ !\\\"#$%&'()*+,-./:;<=>?@\\\\[\\\\]^_`{|}~;\n\r]");
        //делим полную считанную строку по созданному патерну, записываем в массив строк
        String[] test100500manyWords = pattern.split(fullFileString);


        //не рабочая фигня!!!
//        Pattern pattern = Pattern.compile("((/n)|(/r)|([!\"#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~;])|(/s))*(world)((/n)|(/r)|([!\"#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~;])|(/s))*");
//        Matcher matcher = pattern.matcher(fullFileString);
//        while (matcher.find()){
//            System.out.print(fullFileString.substring(matcher.start(), matcher.end()) + "*");
//            countWorld++;
//        }
//        System.out.println();


        //считаем совпадения со словом world
        int countWorld = 0;
        for (String s :
                test100500manyWords) {
            if ("world".equals(s)) countWorld++;
        }


        System.out.println(countWorld);
        fileInputStream.close();
        reader.close();
        fileReader.close();

    }
}
