package com.javarush.task.task08.task0818;

import java.util.*;

/* 
Только для богачей
*/

public class Solution {
    public static Map<String, Integer> createMap() {
        //напишите тут ваш код
        Map<String,Integer> map = new HashMap<String,Integer>();
        for (int i = 0; i < 10; i++) {
            map.put("Buba" + i*10, 100 + i*100);
        }

        return map;
    }

    public static void removeItemFromMap(Map<String, Integer> map) {
        //напишите тут ваш код
        HashMap<String,Integer> mapCopy = new HashMap<String,Integer>(map);
        for (Map.Entry<String, Integer> pair :
                mapCopy.entrySet()) {
            if (pair.getValue() < 500){
                map.remove(pair.getKey());
            }
        }
//        Boolean b = new Boolean("/false");
//        System.out.println(b);
    }

    public static void main(String[] args) {
        HashMap<String, Integer> map = (HashMap) createMap();
        for (Map.Entry<String, Integer> mapEntry:
                map.entrySet()){
          //  System.out.printf("Ключ %s, Значение %s\n",mapEntry.getKey(),mapEntry.getValue());
        }
        removeItemFromMap(map);
       // System.out.println("Проверка!");
        for (Map.Entry<String, Integer> mapEntry:
                map.entrySet()){
        //    System.out.printf("Ключ %s, Значение %s\n",mapEntry.getKey(),mapEntry.getValue());
        }
        List<String> values = new ArrayList<String>() ;

        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(0);
        Integer[] array = null;
        list.toArray(array);
        System.out.println(list.get(1));
    }
}