package com.javarush.task.task05.task0507;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Среднее арифметическое
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
//напишите тут ваш код
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
        String s = "";
        double x = 0;
        int count = 0;
        while (true){
            s = buffReader.readLine();
            if (!s.equals("-1")){
                x += Integer.parseInt(s);
                count++;
            }
            else {
                System.out.println(x/count);
                break;
            }

        }

    }




}

