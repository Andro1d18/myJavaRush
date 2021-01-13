package com.javarush.task.task33.task3310;

import java.math.BigInteger;
import java.security.SecureRandom;

/*
4.1. Создай класс Helper.
4.1.1. Добавь в него статический публичный метод String generateRandomString(),
который будет генерировать случайную строку. Воспользуйся для этого классами SecureRandom и BigInteger.
Подсказка: гугли запрос "random string java". Строка может состоять из цифр и любой из 26 маленьких букв английского алфавита.
4.1.2. Добавь в класс статический метод printMessage(String message).
Он должен выводить переданный текст в консоль. Весь дальнейший вывод в программе должен быть реализован через этот метод!
4.2. Создай класс ExceptionHandler.
4.2.1. Добавь в него статический метод log(Exception e), который будет выводить краткое описание исключения.


 */
public class Helper {
    public static String generateRandomString(){
        SecureRandom secureRandom = new SecureRandom();
        BigInteger bi = new BigInteger(100, secureRandom);
        return bi.toString(36);
    }
    static void printMessage(String message){
        System.out.println(message);
    }
}
