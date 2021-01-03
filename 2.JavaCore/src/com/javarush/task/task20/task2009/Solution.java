package com.javarush.task.task20.task2009;

import java.io.*;

/*
Как сериализовать static?
*/
public class Solution {
    public static class ClassWithStatic implements Serializable {
        private static final long serialVersionUID = 117L;
        public static String staticString = "This is a static test string";
        public int i;
        public int j;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ClassWithStatic cws = new ClassWithStatic();
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\javarush\\javaOne.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(cws);
        fileOutputStream.close();
        objectOutputStream.close();


        System.out.println(cws.staticString);
        System.out.println(cws.i);
        System.out.println(cws.j);


        cws.staticString = "blablabla";
        FileInputStream fileInputStream = new FileInputStream("D:\\javarush\\javaOne.txt");
        ObjectInputStream oInputStream = new ObjectInputStream(fileInputStream);
        Object obj = oInputStream.readObject();
        fileInputStream.close();
        oInputStream.close();


        ClassWithStatic cws2 = (ClassWithStatic)obj;




        System.out.println(cws2.staticString);
        System.out.println(cws2.i);
        System.out.println(cws2.j);
    }
}
