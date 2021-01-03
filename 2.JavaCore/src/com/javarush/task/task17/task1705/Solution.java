package com.javarush.task.task17.task1705;

import java.util.ArrayList;
import java.util.List;

/* 
Сад-огород
*/

public class Solution {


    public static void main(String[] args) {

    }

    public static class Garden {

        public final List<String> fruits = new ArrayList<String>();
        public final List<String> vegetables = new ArrayList<String>();

        public synchronized void  addfruit(int index, String fruit){
            fruits.add(index,fruit);
        }
        public synchronized void removefruit(int index){
            fruits.remove(index);
        }
        public synchronized void addvegetable(int index, String vegetable){
            fruits.add(index,vegetable);
        }
        public synchronized void removevegetable(int index){
            fruits.remove(index);
        }

    }
}
