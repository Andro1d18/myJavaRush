package com.javarush.task.task35.task3505;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertableUtil {

    public static <T> Map convert(List<? extends Convertable> list) {
        Map result = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            Object key = list.get(i).getKey();
            Object val = list.get(i);
            result.put(key,val);
        }
        for (Convertable convertable
                : list) {

        }
        return result;
    }
}
