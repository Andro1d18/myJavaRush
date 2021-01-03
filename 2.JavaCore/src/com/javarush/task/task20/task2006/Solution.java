package com.javarush.task.task20.task2006;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Как сериализовать?
*/
public class Solution {
    public static class Human implements Serializable {
        public String name;
        public List<Asset> assets = new ArrayList<>();

        public Human() {
        }

        public Human(String name, Asset... assets) {
            this.name = name;
            if (assets != null) {
                this.assets.addAll(Arrays.asList(assets));
            }
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
                    Human hum = new Human("Ivan", new Asset("city"), new Asset("garden"));

            System.out.println(hum.name);
            if (hum.assets !=null) {
                for (int i = 0; i < hum.assets.size(); i++) {
                    System.out.println(hum.assets.get(i).getName() + " = " + hum.assets.get(i).getPrice());
                }

            }

            FileOutputStream fileOutputStream = new FileOutputStream("D:\\javarush\\javaOne.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(hum);
            fileOutputStream.close();
            objectOutputStream.close();

            FileInputStream fileInputStream = new FileInputStream("D:\\javarush\\javaOne.txt");
            ObjectInputStream oInputStream = new ObjectInputStream(fileInputStream);
            Object obj = oInputStream.readObject();
            fileInputStream.close();
            oInputStream.close();

            Human newHum = (Human)obj;
            System.out.println(newHum.name);
            if (newHum.assets !=null) {
                for (int i = 0; i < newHum.assets.size(); i++) {
                    System.out.println(newHum.assets.get(i).getName() + " = " + newHum.assets.get(i).getPrice());
                }

            }

        }

}
