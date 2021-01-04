package com.javarush.task;

class Cat
{
    public static void print(int n)
    {
        System.out.println(n);
    }
    public static void print(short n)
    {
        System.out.println(n);
    }

    public static void print(String s)
    {
        System.out.println(s);
    }
    public static void main(String[] args)
    {
        Cat.print(1);
        Cat.print((byte)1);
        Cat.print("1");
        Cat.print(null);
    }
}
