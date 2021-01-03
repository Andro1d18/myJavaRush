package com.javarush.task.task15.task1525;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Файл в статическом блоке
*/

public class Solution {
    public static List<String> lines = new ArrayList<String>();

    static {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(Statics.FILE_NAME);

        BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
        String line;
        while ((line = reader.readLine())!=null){
            lines.add(line);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {




        System.out.println(lines);
    }
}
