package com.javarush.task.task14.task1414;

/* 
MovieFactory
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        //ввести с консоли несколько ключей (строк), пункт 7
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str;
        Movie movie;

        while (true) {
            str = reader.readLine();
        /*
8 Создать переменную movie класса Movie и для каждой введенной строки(ключа):
8.1 получить объект используя MovieFactory.getMovie и присвоить его переменной movie
8.2 вывести на экран movie.getClass().getSimpleName()
        */
            if (str.equals("soapOpera")) {
                movie = MovieFactory.getMovie(str);
                System.out.println(movie.getClass().getSimpleName());
            } else if (str.equals(Thriller.class.getSimpleName().toLowerCase())) {
                movie = MovieFactory.getMovie(str);
                System.out.println(movie.getClass().getSimpleName());
            } else if (str.equals(Cartoon.class.getSimpleName().toLowerCase())) {
                movie = MovieFactory.getMovie(str);
                System.out.println(movie.getClass().getSimpleName());
            } else break;
        }
    }

    static class MovieFactory {

        static Movie getMovie(String key) {
            Movie movie = null;

            //создание объекта SoapOpera (мыльная опера) для ключа "soapOpera"
            if ("soapOpera".equals(key)) {
                movie = new SoapOpera();
            }
            //напишите тут ваш код, пункты 5,6
            if ("thriller".equals(key)) {
                movie = new Thriller();
            }
            if ("cartoon".equals(key)) {
                movie = new Cartoon();
            }
            return movie;
        }
    }

    static abstract class Movie {
    }

    static class SoapOpera extends Movie {
    }

    //Напишите тут ваши классы, пункт 3
    static  class Cartoon extends Movie{

    }
    static  class Thriller extends Movie{

    }
}