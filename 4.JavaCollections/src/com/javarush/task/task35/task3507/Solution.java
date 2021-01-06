package com.javarush.task.task35.task3507;

import java.io.*;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/

public class Solution {
    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {

        Set<Animal> set = new HashSet<>();
        File[] files = new File(pathToAnimals).listFiles();
        for (File file :
                files) {
           // System.out.println(file.getName());
            if (file.isFile() && file.getName().endsWith(".class")) {
                String packageName = Solution.class.getPackage().getName() + ".data";
                Class clazz = new ClassFromPath().load(file.toPath(), packageName);
               // System.out.println(clazz);
                int score = 0;
                //find interface Animal
                Class[] interfaces = clazz.getInterfaces();
                for (Class interf :
                        interfaces) {
                    if (interf.getSimpleName().toString().equals("Animal")) {
                        score++;
                        break;
                    }
                }
              //  System.out.println("1 score = " + score);
                //Find default constructors
                Constructor[] constructors = clazz.getConstructors();
                for (Constructor constr :
                        constructors) {
                    if(constr.getParameterCount()==0){
                        score++;
                    }
                }
               // System.out.println("2 score = " + score);
                if (score ==2){
                    try{
                        Animal animal = (Animal)clazz.newInstance();
                        set.add(animal);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


//        System.out.println(pathToAnimals);
//        if (pathToAnimals.startsWith("/"))
//            pathToAnimals = pathToAnimals.substring(1);
//        pathToAnimals = pathToAnimals.replace("/","\\");
//        System.out.println(pathToAnimals);
//        File file = new File(pathToAnimals);
//        String[] strings = file.list();
//        System.out.println(strings);
//
//        File file1 = new File("D:\\Dropbox\\Андрей\\Программирование(+книги)\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task35\\task3507\\data");
//        String[] strings1 = file1.list();
//        for (String s :
//                strings1) {
//            System.out.println(s);
//        }
//        System.out.println(strings1);
        return set;
    }

    public static class ClassFromPath extends ClassLoader {
        public Class<?> load(Path path, String packageName) {
            try {
                String className = packageName + "." + path.getFileName().toString().replace(".class", "");
                byte[] bytes = Files.readAllBytes(path);
                return defineClass(className, bytes, 0, bytes.length);
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

    }


}
