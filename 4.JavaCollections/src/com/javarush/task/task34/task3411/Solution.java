package com.javarush.task.task34.task3411;

/* 
Ханойские башни
*/

import javax.sound.midi.Soundbank;
import java.util.ArrayList;

public class Solution {




    public static void main(String[] args) {
        int numRings = 3;
        moveRing('A', 'B', 'C', numRings);
    }

    public static void moveRing(char a, char b, char c, int numRings) { //правильное решение в 4 строчки. Но для памяти оставляю криворукое решение в 500 тысяч строк
        if (numRings == 0) return;
        moveRing(a, c, b, numRings - 1);
        System.out.println("from " + a + " to " +b);
        moveRing(c, b, a, numRings -1);



        //напишите тут ваш код
//        initTower(numRings);
//        if (numRings ==0) return;
//        moveRing(a, b, c, numRings - 1);
//        moveTargerRing(a, b, c);
//        moveRing(a, b, c, numRings - 1);

        //https://habr.com/ru/post/200758/
        //27.04 Остановился на том, что не смог адаптировать решение с Хабра т.к. в варианте задачи джавараш
        //метод принимающий просто три башни и количество колец, а не как в хабре (принимающий откуда куда перенести кольцо)
//        поэтому я хотел вести count какой сейчас ход чтобы чередовать правильные ходы (в зависимости от четного/нечеткного количетсва колец)
        // но как понять откуда куда нужно перенести. ПОхоже где то нужно сохранять состояние или нет. ХЗ!



    }

    //    public static ArrayList<Integer> listA;
//    public static ArrayList<Integer> listB = new ArrayList<>();
//    public static ArrayList<Integer> listC = new ArrayList<>();
//    public static int count = 0;
//    public static int numRingsInStart;


//    public static void moveTargerRing(char a, char b, char c) {
//        if (numRingsInStart % 2 == 0){  //1-3, 1-2, 2-3  для четного количества колец
//            switch (count){
//                case (0):   // 1-3
//                    count++;
//                    if (listA.size() == 0){   //для одного уловия слишком сложное выражение - (listA.size() == 0 || (listC.size() > 0  &&  (listA.get(0) > listC.get(0)))
//                        listA.add(0, listC.get(0));
//                        listC.remove(0);
//                        System.out.println("from "+ c +" to " + a);
//                        break;
//                    }
//                    if (listC.size() == 0){
//                        listC.add(0, listA.get(0));
//                        listA.remove(0);
//                        System.out.println("from " + a + " to " + c);
//                        break;
//                    }
//                    if (listA.get(0) > listC.get(0)){
//                        listA.add(0, listC.get(0));
//                        listC.remove(0);
//                        System.out.println("from "+ c +" to " + a);
//                        break;
//                    } else {
//                        listC.add(0, listA.get(0));
//                        listA.remove(0);
//                        System.out.println("from " + a + " to " + c);
//                        break;
//                    }
//
//                case (1):   // 1-2
//                    count++;
//                    if (listA.size() == 0){   //для одного уловия слишком сложное выражение - (listA.size() == 0 || (listC.size() > 0  &&  (listA.get(0) > listC.get(0)))
//                        listA.add(0, listB.get(0));
//                        listB.remove(0);
//                        System.out.println("from " + b + " to " + a);
//                        break;
//                    }
//                    if (listB.size() == 0){
//                        listB.add(0, listA.get(0));
//                        listA.remove(0);
//                        System.out.println("from " + a + " to " + b);
//                        break;
//                    }
//                    if (listA.get(0) > listB.get(0)){
//                        listA.add(0, listB.get(0));
//                        listB.remove(0);
//                        System.out.println("from " + b + " to " + a);
//                        break;
//                    } else {
//                        listB.add(0, listA.get(0));
//                        listA.remove(0);
//                        System.out.println("from " + a + " to " + b);
//                        break;
//                    }
//                case (2):   // 2-3
//                    count = 0;
//                    if (listC.size() == 0){   //для одного уловия слишком сложное выражение - (listA.size() == 0 || (listC.size() > 0  &&  (listA.get(0) > listC.get(0)))
//                        listC.add(0, listB.get(0));
//                        listB.remove(0);
//                        System.out.println("from " + b + " to " + c);
//                        break;
//                    }
//                    if (listB.size() == 0){
//                        listB.add(0, listC.get(0));
//                        listC.remove(0);
//                        System.out.println("from " + c + " to " + b);
//                        break;
//                    }
//                    if (listC.get(0) > listB.get(0)){
//                        listC.add(0, listB.get(0));
//                        listB.remove(0);
//                        System.out.println("from " + b + " to " + c);
//                        break;
//                    } else {
//                        listB.add(0, listC.get(0));
//                        listC.remove(0);
//                        System.out.println("from " + c + " to " + b);
//                        break;
//                    }
//            }
//
//
//        } else{     // 1-2, 1-3, 2-3 для нечетного
//            switch (count){
//                case (0):   // 1-2
//                    count++;
//                    if (listA.size() == 0){   //для одного уловия слишком сложное выражение - (listA.size() == 0 || (listC.size() > 0  &&  (listA.get(0) > listC.get(0)))
//                        listA.add(0, listB.get(0));
//                        listB.remove(0);
//                        System.out.println("from " + b + " to " + a);
//                        break;
//                    }
//                    if (listB.size() == 0){
//                        listB.add(0, listA.get(0));
//                        listA.remove(0);
//                        System.out.println("from " + a + " to " + b);
//                        break;
//                    }
//                    if (listA.get(0) > listB.get(0)){
//                        listA.add(0, listB.get(0));
//                        listB.remove(0);
//                        System.out.println("from " + b + " to " + a);
//                        break;
//                    } else {
//                        listB.add(0, listA.get(0));
//                        listA.remove(0);
//                        System.out.println("from " + a + " to " + b);
//                        break;
//                    }
//                case (1):   // 1-3
//                    count++;
//                    if (listA.size() == 0){   //для одного уловия слишком сложное выражение - (listA.size() == 0 || (listC.size() > 0  &&  (listA.get(0) > listC.get(0)))
//                        listA.add(0, listC.get(0));
//                        listC.remove(0);
//                        System.out.println("from "+ c +" to " + a);
//                        break;
//                    }
//                    if (listC.size() == 0){
//                        listC.add(0, listA.get(0));
//                        listA.remove(0);
//                        System.out.println("from " + a + " to " + c);
//                        break;
//                    }
//                    if (listA.get(0) > listC.get(0)){
//                        listA.add(0, listC.get(0));
//                        listC.remove(0);
//                        System.out.println("from "+ c +" to " + a);
//                        break;
//                    } else {
//                        listC.add(0, listA.get(0));
//                        listA.remove(0);
//                        System.out.println("from " + a + " to " + c);
//                        break;
//                    }
//
//                case (2):   // 2-3
//                    count = 0;
//                    if (listC.size() == 0){   //для одного уловия слишком сложное выражение - (listA.size() == 0 || (listC.size() > 0  &&  (listA.get(0) > listC.get(0)))
//                        listC.add(0, listB.get(0));
//                        listB.remove(0);
//                        System.out.println("from " + b + " to " + c);
//                        break;
//                    }
//                    if (listB.size() == 0){
//                        listB.add(0, listC.get(0));
//                        listC.remove(0);
//                        System.out.println("from " + c + " to " + b);
//                        break;
//                    }
//                    if (listC.get(0) > listB.get(0)){
//                        listC.add(0, listB.get(0));
//                        listB.remove(0);
//                        System.out.println("from " + b + " to " + c);
//                        break;
//                    } else {
//                        listB.add(0, listC.get(0));
//                        listC.remove(0);
//                        System.out.println("from " + c + " to " + b);
//                        break;
//                    }
//            }
//        }
//    }
//
//    public static void initTower(int numRings) {
//        if (listA == null){
//            listA = new ArrayList<>();
//            for (int i = 0; i < numRings; i++) {
//                listA.add(i+1);
//            }
//            numRingsInStart = numRings;
//        }
//    }


}