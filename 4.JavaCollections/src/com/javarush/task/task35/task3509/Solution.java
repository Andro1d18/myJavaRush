package com.javarush.task.task35.task3509;

import java.util.*;

/* 
Collections & Generics
*/

public class Solution {

    public static void main(String[] args) {
//        List<String> list = newArrayList("ads", "ererg","asfdasd", "adfsa");
//        System.out.println(list);
//        List<Integer> listInt = newArrayList(123,222,444,2222);
//        System.out.println(listInt);
//        HashMap<String, Integer> hashMap = newHashMap(list,listInt);
//        System.out.println(hashMap);
//        HashSet<Double> hashSet = newHashSet(4545.54,454.55,111.77);
//        System.out.println(hashSet);
    }

    public static <T> ArrayList<T> newArrayList(T... elements) {
        //напишите тут ваш код
        ArrayList list = new ArrayList();
        Collections.addAll(list, elements);
        return list;
    }

    public static <T> HashSet<T> newHashSet(T... elements) {
        //напишите тут ваш код
        HashSet hashSet = new HashSet();
        Collections.addAll(hashSet, elements);
        return hashSet;
    }

    public static <K, V> HashMap<K, V> newHashMap(List<? extends K> keys, List<? extends V> values) {
        //напишите тут ваш код
        if (!(keys.size() == values.size()))
            throw new IllegalArgumentException();
        HashMap hashMap = new HashMap();
        for (int i = 0; i < keys.size(); i++) {
            hashMap.put(keys.get(i),values.get(i));
        }
        return hashMap;
    }
}
