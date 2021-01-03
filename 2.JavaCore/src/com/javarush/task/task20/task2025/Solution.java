package com.javarush.task.task20.task2025;

import java.util.ArrayList;
import java.util.List;

/*
Алгоритмы-числа
*/
public class Solution {
    public static int[] getNumbers(int N) {
        int[] result = null;


        if (N>=1){
            List<Integer> list = new ArrayList<>();
            //int m ;
            //int ;


            for (int i = 1; i <= N; i++) {
                int summa = 0;
                int pow = 1;
                int fDubl =i; //2147483647;
                int curNumber;




                //byte[] numberArray = getNumberArray(i);
                int m = getM(i);




                do{
                    curNumber = fDubl %10;
                    for (int j = 0; j < m; j++) {
                        pow *= curNumber;
                    }
                    summa +=pow;
                    pow = 1;
                } while ( (fDubl /= 10) >= 1);








//                for (int j = 0; j < numberArray.length; j++) {
//                    //curNumber = getNumber(i,j);
//
//                    //своя степень:
//                    for (int k = 0; k < numberArray.length; k++) {
//                        pow *= numberArray[j];
//                    }
//                    //summa += Math.pow(numberArray[j],numberArray.length);
//                    summa +=pow;
//                    pow = 1;
//                }
                if (summa == i){
                    list.add(i);
                    System.out.println(i);
                }
            }
            result = new int[list.size()];
            for (int i = 0; i <list.size() ; i++) {
                result[i] = list.get(i);
            }
        }


        // System.arraycopy(list.toArray(),0,result,0,list.size());
        return result;
    }


    private static int getM (int n){
        int count = 0;
        while (n>=1){
            n /= 10;
            count++;
        }
        return count;
    }


    private static byte[] getNumberArray (int n){
        byte[] arrayNumber = null;


        int nTemp = n;
        int count = 0;
        while (nTemp>=1){
            nTemp  /= 10;
            count++;
        }
        arrayNumber = new byte[count];


        for (int i = 0; i < count; i++) {
            arrayNumber[count-1-i] = (byte)(n % 10);
            n /=10;
        }


        return arrayNumber;
    }




//    public static int getNumber (int fullNumber, int currentPosition) {
//
//        String s = String.valueOf(fullNumber);
//        return Integer.valueOf(s.substring(currentPosition-1,currentPosition));
//
//    }


    public static void main(String[] args) {


        Long t0 = System.currentTimeMillis();
        int[] resArr = getNumbers(1465112112);
        Long t1 = System.currentTimeMillis();


        System.out.println("Memory: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())/(1024*1024d) + " Mb.");
        System.out.println("Time: " + (t1 - t0)/1000d + " sec.");


//        for (int i = 0; i <resArr.length ; i++) {
//            System.out.println(resArr[i]);
//        }
    }
}
