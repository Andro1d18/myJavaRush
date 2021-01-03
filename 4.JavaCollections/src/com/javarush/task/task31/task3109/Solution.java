package com.javarush.task.task31.task3109;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/* 
Читаем конфиги
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Properties properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.xml");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.txt");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/notExists");
        properties.list(System.out);
    }

    public Properties getProperties(String fileName) {
//        System.out.println(File.separator);
//        System.out.println(File.separatorChar);
//        System.out.println(File.pathSeparator);
//        System.out.println(File.pathSeparatorChar);
      Properties properties = new Properties();
        String[] partOfFileName = fileName.split(File.pathSeparator);
        if (partOfFileName[partOfFileName.length-1].endsWith(".xml")){
            try {
                properties.loadFromXML(new FileInputStream(fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                properties.load(new FileReader(fileName));
            } catch (IOException e) {

            }
        }
        return properties;
    }
}
