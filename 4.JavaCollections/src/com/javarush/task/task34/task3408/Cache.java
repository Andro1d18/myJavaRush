package com.javarush.task.task34.task3408;



import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap();   //TODO add your code here

    public V getByKey(K key, Class<V> clazz) throws Exception {
        //TODO add your code here
        if (!cache.containsKey(key)){
            Constructor constructor = clazz.getConstructor(key.getClass());
            V o = (V) constructor.newInstance(key);
            cache.put(key, o);
        }
        return cache.get(key);
    }

    public boolean put(V obj) {
        //TODO add your code here

        try {
            Method method = obj.getClass().getDeclaredMethod("getKey");
            method.setAccessible(true);
            K k = (K) method.invoke(obj);
            cache.put(k, obj);
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        } catch (IllegalAccessException e) {
            return false;
        } catch (InvocationTargetException e) {
            return false;
        }

    }

    public int size() {
        return cache.size();
    }
}
