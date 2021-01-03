package com.javarush.task.task18.task1809;

/* 
Реверс файла
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Solution {
    public static void main(String[] args) {
        Integer[] ints = new Integer[1];
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(ints));
        Collections.reverse(arr);

    }
}
