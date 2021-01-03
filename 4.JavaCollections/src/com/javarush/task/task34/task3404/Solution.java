package com.javarush.task.task34.task3404;

import java.util.ArrayList;
import java.util.StringTokenizer;

/*
Рекурсия для мат. выражения
*/

//Решение
//ищем первую операцию, которую можем выполнить, выполняем,
// записываем значение в итоговую строку и прокидываем дальше рекурсией и запоминаем количество операций
//Именно такой алгорит, потому что объвление метода void - он ничего не может вернуть (мы должны всё прокидывать дальше)
        /*
        Псевдокод

        Вначале счинаем количество операций
        Если значения countOperation = 0 то считаем количество тригонометр, минусов плюсов и приравниваем
        присваиваем значение countOperation полученному значению.

        Ищем самые внутренние скобки - вначале самую последнюю "(", как найдём начинаем искать самую первую ")".
        Если нашли, то
            Отправляем содержание скобок в метод expressionMathematic(String exp), который возвращает результат в типе String
            Если снаружи скобок есть степень, то возвоздим содержимое скобок в степень и удаляем информацию про степень.
            Если снаружи скобок есть тригонометрическая функции, то
                отправляем тригонометрическую функцию и содержимое скобок (влючая скобки) в метод trigonometricSolution(String exp)
                который возвращает результат в типе String (так же, получается, он ещё и скобки раскрывает)
                перезаписываем  expression с результатом вычислений тригонометрической функции и выражения в скобках
                вызываем рекурсию с новым expression
            Иначе  раскрываем (удаляем) скобки, перезаписываем expression и вызываем рекурсию с новым expression
        Иначе, отправляем всё выражение в метод expressionMathematic(String exp) и получает результат программы, выводим на экран вместе с количество операций


        Метод expressionMathematic(String exp) (получает выражение без скобок!)
        пока в пришедшей строке есть знаки +-/*^ выполнять цикл
            засовываем в токенайзер всю пришедшую строку, устанавливаем delim в виде +-/*^
            Ищем ^. Если нашли предшествующий токен возводим в степень, значение стпени в токене после ^
                перезаписываем содержимое выражения (exp) со значением результата
                continue (запускаем цикл заного)
            Ищем * и /. Если нашли предшествующий токен умножаем или делим (в зависимости от того какой знак был) на токен следом
                перезаписываем содержимое выражения (exp) со значением результата
                continue (запускаем цикл заного)
            Ищем + и -. Если нашли предшествующий токен складываем или вычитаем (в зависимости от того какой знак был) с токеном следом
                перезаписываем содержимое выражения (exp) со значением результата
                continue (запускаем цикл заного)
        Когда цикл закончился, возвращаем значение exp


        Метод trigonometricSolution(String exp)
        Свич
            sin -  вычисляем sin - возвращаем результат, например System.out.println("равен " + Math.round(Math.sin(Math.toRadians(30))));
            cos - вычисляем cos - возвращааяем результат
            tan - вычисляем tan - возвращаем результат



        */


public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
       // solution.recurse("sin(2*(-5+1.5*4)+28)", 0); //expected output 0.5 6
        solution.recurse("2*5-1.5*4+28", 0); //expected output 0.5 6
        solution.recurse("5^2^2^2", 0); //expected output 0.5 6
        solution.recurse("57-10^2+5", 0); //expected output 0.5 6
    }


    public void recurse(final String expression, int countOperation) {
        //implement

        String exp = expression;
        String resultTrigonometric = trigonometricSolution(exp);

        exp = expression;
        String resultMathematics = expressionMathematics(exp);
    }


    private String expressionMathematics(String exp) {
       /* пока в пришедшей строке есть знаки +-/*^ выполнять цикл
            засовываем в токенайзер всю пришедшую строку, устанавливаем delim в виде +-/*^
            Ищем ^. Если нашли предшествующий токен возводим в степень, значение стпени в токене после ^
                перезаписываем содержимое выражения (exp) со значением результата
                continue (запускаем цикл заного)
            Ищем * и /. Если нашли предшествующий токен умножаем или делим (в зависимости от того какой знак был) на токен следом
                перезаписываем содержимое выражения (exp) со значением результата
                continue (запускаем цикл заного)
            Ищем + и -. Если нашли предшествующий токен складываем или вычитаем (в зависимости от того какой знак был) с токеном следом
                перезаписываем содержимое выражения (exp) со значением результата
                continue (запускаем цикл заного)
        Когда цикл закончился, возвращаем значение exp
        */


        //регулярка которая проверяет есть ли знаки +-/*^ в строке. Пока они есть выполняем математические операции
        while(exp.matches("(.*\\++.+)|(.*-+.+)|(.*/+.+)|(.*\\*+.+)|(.*\\^+.+)")){ //Пояснение к регулярке ниже

            //создаём StringTokenizer для разделения чисел и знаков
            StringTokenizer stringTokenizer = new StringTokenizer(exp, "-+*^/", true);
            //создаём темповый лист для работы с числами и знаками
            ArrayList<String> listTokens = new ArrayList<>();
            //перегоняем числа и знаки из токинайзера в темповый лист
            while (stringTokenizer.hasMoreElements())
                listTokens.add(stringTokenizer.nextToken());

            //проверка на минус вначале выражения
            if(listTokens.get(0).equals("-")){ //если первый элемент минус
                String s = listTokens.get(1); //запоминаем второй элемент
                listTokens.remove(0);   //удаляем минус из списка
                listTokens.remove(0);   //удаляем второй элемент списка
                listTokens.add(0, "-" + s); //добавляем на первое место в списке значение второго элемента с минусом
            }
//02.03.2020г. ОСТАНОВИЛСЯ на знаке Проблема в том что при выражении -43+5 программа даёт результат -48, а должна -38
            //если нашли ^ то  число перед ^ возводим в степень. Значение степени - следующее число после ^
            while(listTokens.contains("^")){
                int index = listTokens.indexOf("^");
                double term1 = Double.valueOf(listTokens.get(index - 1));
                double term2 = Double.valueOf(listTokens.get(index + 1));
                double result = Math.pow(term1, term2);
                listTokens.remove(index -1); //удаляем число перед ^
                listTokens.remove(index -1); //удаляем ^ (всё равно i-1 т.к. лист сдвинулся слева после первого remove)
                listTokens.remove(index -1); //удаляем число после ^ (всё авно i-1 по той же причине)
                listTokens.add(index -1, String.valueOf(result)); //добавляем на место первого числа получившееся значение

            }

            while(listTokens.contains("*")){
                int index = listTokens.indexOf("*");
                double term1 = Double.valueOf(listTokens.get(index - 1));
                double term2 = Double.valueOf(listTokens.get(index + 1));
                double result = term1 * term2;
                listTokens.remove(index -1); //удаляем число перед ^
                listTokens.remove(index -1); //удаляем ^ (всё равно i-1 т.к. лист сдвинулся слева после первого remove)
                listTokens.remove(index -1); //удаляем число после ^ (всё авно i-1 по той же причине)
                listTokens.add(index -1, String.valueOf(result)); //добавляем на место первого числа получившееся значение

            }

            while(listTokens.contains("/")){
                int index = listTokens.indexOf("/");
                double term1 = Double.valueOf(listTokens.get(index - 1));
                double term2 = Double.valueOf(listTokens.get(index + 1));
                double result = term1 / term2;
                listTokens.remove(index -1); //удаляем число перед ^
                listTokens.remove(index -1); //удаляем ^ (всё равно i-1 т.к. лист сдвинулся слева после первого remove)
                listTokens.remove(index -1); //удаляем число после ^ (всё авно i-1 по той же причине)
                listTokens.add(index -1, String.valueOf(result)); //добавляем на место первого числа получившееся значение

            }
            while(listTokens.contains("+")){
                int index = listTokens.indexOf("+");
                double term1 = Double.valueOf(listTokens.get(index - 1));
                double term2 = Double.valueOf(listTokens.get(index + 1));
                double result = term1 + term2;
                listTokens.remove(index -1); //удаляем число перед ^
                listTokens.remove(index -1); //удаляем ^ (всё равно i-1 т.к. лист сдвинулся слева после первого remove)
                listTokens.remove(index -1); //удаляем число после ^ (всё авно i-1 по той же причине)
                listTokens.add(index -1, String.valueOf(result)); //добавляем на место первого числа получившееся значение

            }

            while(listTokens.contains("-")){
                int index = listTokens.indexOf("-");
                double term1 = Double.valueOf(listTokens.get(index - 1));
                double term2 = Double.valueOf(listTokens.get(index + 1));
                double result = term1 - term2;
                listTokens.remove(index - 1); //удаляем число перед ^
                listTokens.remove(index - 1); //удаляем ^ (всё равно i-1 т.к. лист сдвинулся слева после первого remove)
                listTokens.remove(index - 1); //удаляем число после ^ (всё авно i-1 по той же причине)
                listTokens.add(index - 1, String.valueOf(result)); //добавляем на место первого числа получившееся значение

            }

            //Создаём stringBuilder для сбора новой строки
            StringBuilder stringBuilder = new StringBuilder();
            for (String s :
                    listTokens) {
                stringBuilder.append(s); //собираем строку из элементов темпового листа
            }
            //присваиваем exp новое значение строки
            exp = stringBuilder.toString();
            System.out.println(exp);

            if (listTokens.size() == 1){ //если в списке остался 1 элемент, то возвращаем exp. Это нужно потому что там может быть отрицательное значение
                break;                   //
            }
        }
        return exp;
    }
    //Пояснение к регулярке (.*\++.+)|(.*-+.+)|(.*/+.+)|(.*\*+.+)
    //Во первых в комментариях одна обратная касая, а в боевой строке две - это особенность джавы - одна обратная косая "съедается" джавой
    //(.*\++.+)
    // .* - любой символ в количестве ноль или больше
    // \++ - экранирование символа плюс и этот символ плюк в количестве один или больше
    //.+ - любой символ в количестве один или больше
    // символ | между скобками - это ИЛИ
    //в других скобках аналогично, но например символ минуса и деления экранировать не надо




       //Код для примера как работает StringTokenizer
//        StringTokenizer stringTokenizer = new StringTokenizer(exp, "-+*^/", true);
//        System.out.println(stringTokenizer.countTokens());
//        System.out.println();
//        while (stringTokenizer.hasMoreTokens())
//
//            System.out.println(stringTokenizer.nextToken());
//        System.out.println();
//        while (stringTokenizer.hasMoreElements())
//            System.out.println(stringTokenizer.nextElement());




    private String trigonometricSolution(String exp) {

        System.out.println("равен " + Math.round(Math.sin(Math.toRadians(30))));

        return exp;
    }

    public Solution() {
        //don't delete
    }
}
