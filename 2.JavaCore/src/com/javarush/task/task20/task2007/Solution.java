package com.javarush.task.task20.task2007;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
Как сериализовать JavaRush?
*/
public class Solution {
    public static class JavaRush implements Serializable {
        public List<User> users = new ArrayList<>();
    }


        public static void main(String[] args) throws IOException, ClassNotFoundException {


            JavaRush javaRush = new JavaRush();

            User user = new User();
            user.setBirthDate(new Date());
            user.setCountry(User.Country.OTHER);
            user.setFirstName("Eou");
            user.setLastName("Lastou");
            user.setMale(true);
            javaRush.users.add(user);


            System.out.println(javaRush);
            if (javaRush.users !=null) {
                for (int i = 0; i < javaRush.users.size(); i++) {
                    System.out.println(javaRush.users.get(i).getBirthDate());
                    System.out.println(javaRush.users.get(i).getCountry());
                    System.out.println(javaRush.users.get(i).getFirstName());
                    System.out.println(javaRush.users.get(i).getLastName());
                    System.out.println(javaRush.users.get(i).isMale());
                }

            }

            FileOutputStream fileOutputStream = new FileOutputStream("D:\\javarush\\javaOne.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(javaRush);
            fileOutputStream.close();
            objectOutputStream.close();

            FileInputStream fileInputStream = new FileInputStream("D:\\javarush\\javaOne.txt");
            ObjectInputStream oInputStream = new ObjectInputStream(fileInputStream);
            Object obj = oInputStream.readObject();
            fileInputStream.close();
            oInputStream.close();

            JavaRush javaRush2 = (JavaRush) obj;
            System.out.println(javaRush2);
            if (javaRush2.users !=null) {
                for (int i = 0; i < javaRush2.users.size(); i++) {
                    System.out.println(javaRush2.users.get(i).getBirthDate());
                    System.out.println(javaRush2.users.get(i).getCountry());
                    System.out.println(javaRush2.users.get(i).getFirstName());
                    System.out.println(javaRush2.users.get(i).getLastName());
                    System.out.println(javaRush2.users.get(i).isMale());
                }

            }

        }
    }

