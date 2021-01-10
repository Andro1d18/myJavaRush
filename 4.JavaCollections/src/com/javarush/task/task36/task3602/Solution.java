package com.javarush.task.task36.task3602;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.rmi.NotBoundException;
import java.util.Collections;
import java.util.List;

/* 
Найти класс по описанию Ӏ Java Collections: 6 уровень, 6 лекция
Для тех, кто, как и я не понял, что нужно делать.
Есть некий класс (спойлеры ниже) в котором есть вложенные классы – коллекции.
Один из этих вложенных классов-коллекций соответствует требованиям в задачи:
1.	Этот класс или его класс родитель имплементирует класс List
2.	Этот класс приватный и статический
3.	У этого класса есть приватный метод get(int i) который, если к нему обратиться выкидывает эксепшен «InvocationTargetException»,
в тексте которого есть текст «IndexOutOfBoundsException»
Дальше спойлеры, рекомендую не читать, а попробовать решить самим.
Порядок действий (решается через reflection):
1.	Получаем (getDeclaredClasses) и обходим все классы у класса Collections
2.	Для каждого класса проверяем:
a.	Имплементирует ли этот класс или его родитель (getSuperclass) интерфейс List (getInterfaces)
b.	Является ли этот класс приватным (Modifier.isPrivate(clazz.getModifiers()))
c.	Является ли этот класс статическим (Modifier.isStatic(clazz.getModifiers()))
d.	Получаем метод get (getDeclaredMethod("get", int.class)) и устанавливаем ему доступность setAccessible(true)
e.	Получаем конструктор (getDeclaredConstructor) и устанавливаем ему доступность  setAccessible(true)
f.	Выполняем метод (invoke()) с созданным новым инстансом через конструктор (newInstance()) и любым параметром int
g.	Если отловили InvocationTargetException и в getCause() содержится "IndexOutOfBoundsException" – это наш класс, его и возвращаем.
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
//        Method[] methods = Collections.class.getDeclaredMethods();
//        for (Method method :
//                methods) {
//            System.out.println(method.getName());
//        }

        Class[] classes = Collections.class.getDeclaredClasses();
        for (Class clazz :
                classes) {
            Class superClass = clazz.getSuperclass();
            Class[] interfaceSuperClass = superClass.getInterfaces();
            Class[] interfaceClass = clazz.getInterfaces();

            int count = 0;
            for (Class ifaceClass :
                    interfaceClass) {
                if (ifaceClass.getSimpleName().equals("List")) count++;
            }
            for (Class ifaceSupClass :
                    interfaceSuperClass) {
                if (ifaceSupClass.getSimpleName().equals("List")) count++;
            }
            if (Modifier.isPrivate(clazz.getModifiers())) count++;
            if (Modifier.isStatic(clazz.getModifiers())) count++;


            if (count == 3) {
//                System.out.println("Интерфейсы базового класса " + clazz.getName() + " :");
//                System.out.println(interfaceClass[0].getName());
//                System.out.println(interfaceClass[0].getSimpleName());
//                System.out.println(" родительский класс - " + superClass.getName());
//                System.out.println("интерфейсы класса-родителя(" + superClass.getName() + "):");
//                System.out.println(interfaceSuperClass[0].getName());
//                System.out.println(interfaceSuperClass[0].getSimpleName());

                try {
                    Constructor constructor = clazz.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    List list = (List) constructor.newInstance();
                    list.get(0); //здесь не правильно. Нужно было через отлавливание InvocationTargetException
//                    Method method = clazz.getDeclaredMethod("get",int.class);
//                    method.setAccessible(true);
//                    method.invoke(clazz.newInstance(), 1);

                } catch (NoSuchMethodException e) {
                    //      e.printStackTrace();

                } catch (IndexOutOfBoundsException e) {
                    //      e.printStackTrace();
                    return clazz;
                } catch (IllegalAccessException e) {
                    //       e.printStackTrace();
                } catch (InstantiationException e) {
                    //       e.printStackTrace();
                } catch (InvocationTargetException e) {
//                    e.printStackTrace();
//                    System.out.println(e.getCause().getMessage());
                }
            }
        }
        return null;
    }
}
