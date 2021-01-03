package com.javarush.task.task13.task1319;

import java.io.*;
import java.util.ArrayList;

/* 
Писатель в файл с консоли
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        // напишите тут ваш код
        ArrayList<String> strings = new ArrayList<String>();
        FileOutputStream outputStream = new FileOutputStream("D:\\javarush\\jr2018.txt");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while (!(s = reader.readLine()).
                equals("exit")){
            strings.add(s);
            outputStream.write(s.getBytes());
            outputStream.write('\n');
        }


//        for (String str: strings
//             ) {
//            for (int i = 0; i < str.length(); i++) {
//                outputStream.write(str.charAt(i));
//            }
//            char n = '\n';
////            char r = '\r';
//            outputStream.write(n);
////            outputStream.write(r);
//        }
        outputStream.close();
        reader.close();
    }
}
