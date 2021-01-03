package com.javarush.task.task34.task3403;

/* 
Разложение на множители с помощью рекурсии
*/
public class Solution {

    public static void main(String[] args){

        new Solution().recurse(132);

    }

    public void recurse(int n) {
        if (n > 1) {
            int mod; //остаток от деления
            int div; //целая часть от деления
            boolean flag = true; //флаг не было ли вхождение в цикл.

            for (int i = 2; i < n / 2; i++) { //перебираем возможные делители от 2 до n/2
                mod = n % i;

                div = n / i;

                if (mod == 0) {     //если нашли делитель
                    System.out.print(i + " "); //выводим на экран
                    recurse(div);   //запускаем рекурсию далее с разделенным значением
                    flag = false;   //ставим флаг, что не вхождения в рекурсию не было (вхождение в рекурсию было)
                    break;      //останавливаем цикл
                }
            }
            if (flag) //вхождения в цикл не было, значит делителя не нашли, значит выводим текущее n
                System.out.println(n);
        }
    }
}
