package com.javarush.task.task18.task1805;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

/* 
Сортировка байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));        //  D:\\javarush\\тест.txt
        FileInputStream fileInputStream = new FileInputStream(reader.readLine());
        TreeSet<Integer> treeSet = new TreeSet<>();

        while (fileInputStream.available()>0){
            treeSet.add(fileInputStream.read());
        }
        System.out.println(treeSet);
        for (Integer i:
             treeSet) {
            System.out.print(i + " ");
        }

    }
}
