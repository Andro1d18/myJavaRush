package com.javarush.task.task14.task1420;

/* 
НОД
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            int val1 = Integer.parseInt(reader.readLine());
            int val2 = Integer.parseInt(reader.readLine());
            int maxVal;
            int minVal;
            if (val1 > val2) {
                maxVal = val1;
                minVal = val2;
            }
            else {
                maxVal = val2;
                minVal = val1;
            }

            int i = 1;
            int nod;

            while (true){

                if (maxVal%minVal==0){
                    nod = minVal;
                    break;
                }
                int sum;
                if (minVal%++i==0){
                    nod = minVal/i;
                    if (maxVal%nod==0) break;
                }
        }
            System.out.println(nod);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
