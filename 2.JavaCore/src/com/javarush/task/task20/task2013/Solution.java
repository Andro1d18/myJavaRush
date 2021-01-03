package com.javarush.task.task20.task2013;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Externalizable Person
*/
public class Solution {
    public static class Person implements Externalizable {
        private String firstName;
        private String lastName;
        private int age;
        private Person mother;
        private Person father;
        private List<Person> children;

        public Person(String firstName, String lastName, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        public Person(){}

        public void setMother(Person mother) {
            this.mother = mother;
        }

        public void setFather(Person father) {
            this.father = father;
        }

        public void setChildren(List<Person> children) {
            this.children = children;
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {

            out.writeUTF(firstName);
            out.writeUTF(lastName);
            out.writeObject(mother);
            out.writeObject(father);
            out.writeInt(age);
            out.writeObject(children);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {


            firstName = in.readUTF();
            lastName = in.readUTF();
            mother = (Person)in.readObject();
            father = (Person)in.readObject();
            age = in.readInt();
            children = (List <Person>)in.readObject();
        }

        public void printAll (){
            System.out.println(firstName);
            System.out.println(lastName);
            System.out.println(age);
            System.out.println(children);
            System.out.println(father);
            System.out.println(mother);
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileOutputStream fileOutput = new FileOutputStream("D:\\javarush\\javaTwo.txt");
        ObjectOutputStream objOutStr = new ObjectOutputStream(fileOutput);

        Person person = new Person("Buba", "Kuba", 34);

        person.setFather(new Person("a","aa", 54));
        person.setMother(new Person("b","bb", 53));
        ArrayList<Person> personsList= new ArrayList<>();
        personsList.add (new Person("x", "xx", 13));
        personsList.add (new Person("y", "yy", 14));
        personsList.add (new Person("z", "yy", 15));
        person.setChildren(personsList);
        person.writeExternal(objOutStr);

        person.printAll();
        System.out.println();

        FileInputStream fileInput = new FileInputStream("D:\\javarush\\javaTwo.txt");
        ObjectInputStream objInStr = new ObjectInputStream(fileInput);

        Person personLoad = new Person();
        personLoad.readExternal(objInStr);

        personLoad.printAll();

//        System.out.println(personLoad.firstName);
//        System.out.println(personLoad.lastName);
//        System.out.println(personLoad.age);
//        System.out.println(personLoad.children);
//        System.out.println(personLoad.father);
//        System.out.println(personLoad.mother);

    }
}
