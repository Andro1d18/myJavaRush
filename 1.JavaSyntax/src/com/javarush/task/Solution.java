package com.javarush.task;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Hryvnia().getAmount());
    }

    public static abstract class Money {
        abstract Money getMoney();

        public Object getAmount() {
            return getMoney().getAmount();
        }
    }

    //add your code below - добавь код ниже
    public static class Hryvnia extends Money {
        private double amount = 123d;

        public Hryvnia getMoney() {
            return this;
        }
    }
    public void doSomething(List<? extends Solution> list)
    {
        for(Solution object : list)
        {
            System.out.println(); //тут все работает отлично.
        }
    }
    public static <T> void add(List<? super T> destinationList, List<? extends T> sourceList) { //<D, H extends D, S extends H>
        ListIterator<? super T> destListIterator = destinationList.listIterator();
        ListIterator<? extends T> srcListIterator = sourceList.listIterator();
        for (int i = 0; i < sourceList.size(); i++) {
            destListIterator.add(srcListIterator.next());
        }
    }
}