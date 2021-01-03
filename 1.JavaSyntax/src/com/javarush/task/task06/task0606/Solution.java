package com.javarush.task.task06.task0606;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* 
Чётные и нечётные циферки
*/

public class Solution {

    public static int even;
    public static int odd;

    public static void main(String[] args) throws IOException {
        //напишите тут ваш код

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();
        char[] chars = s.toCharArray();
        int i = 0 ;
        for (char ch :
                chars) {
            i = ch - '0';
            if (i % 2 == 0)
                even++;
            else odd++;

        }
        System.out.println("Even: " + even + " Odd: " + odd);

        Map<String, String> map = createPeopleList();
        printPeopleList(map);
    }

    public static Map<String, String> createPeopleList()
    {
        //напишите тут ваш код
        Map<String, String > map = new HashMap<String, String>();
        map.put("Сурэн", "Мильен");
        map.put("Буквоедов", "Шараш");
        map.put("Квора", "Сьюзен");
        map.put("Буквоедов", "Бюртен");
        map.put("Пирогиня", "Сьюзен");
        map.put("Буквоедова", "Сьюзен");
        map.put("Буквоедов", "Шалы");
        map.put("Сорока", "Сьюзен");
        map.put("Белобока", "Сьюзен");
        map.put("Сарумянка", "Мильен");

        return map;
    }

    public static void printPeopleList(Map<String, String> map)
    {
        for (Map.Entry<String, String> s : map.entrySet())
        {
            System.out.println(s.getKey() + " " + s.getValue());
        }
    }
}
