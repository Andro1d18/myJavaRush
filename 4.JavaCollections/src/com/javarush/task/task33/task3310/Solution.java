package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/*
6.1. Создай класс Solution, если ты не сделал это раньше.
6.2. Добавь в класс Solution реализации вспомогательных статических методов:
6.2.1. Set<Long> getIds(Shortener shortener, Set<String> strings).
Этот метод должен для переданного множества строк возвращать множество идентификаторов.
Идентификатор для каждой отдельной строки нужно получить, используя shortener.
6.2.2. Set<String> getStrings(Shortener shortener, Set<Long> keys). Метод будет возвращать множество строк,
которое соответствует переданному множеству идентификаторов.
При реальном использовании Shortener, задача получить из множества строк множество идентификаторов и наоборот скорее всего не встретится,
это нужно исключительно для тестирования.
6.2.3. testStrategy(StorageStrategy strategy, long elementsNumber).
Метод будет тестировать работу переданной стратегии на определенном количестве элементов elementsNumber.
Реализация метода должна:
6.2.3.1. Выводить имя класса стратегии. Имя не должно включать имя пакета.
6.2.3.2. Генерировать тестовое множество строк, используя Helper и заданное количество элементов elementsNumber.
6.2.3.3. Создавать объект типа Shortener, используя переданную стратегию.
6.2.3.4. Замерять и выводить время необходимое для отработки метода getIds для заданной стратегии и заданного множества элементов.
Время вывести в миллисекундах.
При замере времени работы метода можно пренебречь переключением процессора на другие потоки,
временем, которое тратится на сам вызов, возврат значений и вызов методов получения времени (даты).
Замер времени произведи с использованием объектов типа Date.
6.2.3.5. Замерять и выводить время необходимое для отработки метода getStrings для заданной стратегии и
полученного в предыдущем пункте множества идентификаторов.
6.2.3.6. Сравнивать одинаковое ли содержимое множества строк, которое было сгенерировано и множества,
которое было возвращено методом getStrings. Если множества одинаковы, то выведи "Тест пройден.", иначе "Тест не пройден.".
6.2.4. Добавь метод main(). Внутри метода протестируй стратегию HashMapStorageStrategy с помощью 10000 элементов.
6.3. Проверь, что программа работает и тест пройден.
 */
public class Solution {
    public static void main(String[] args) {
        //Helper.printMessage(Helper.generateRandomString());
        testStrategy(new HashMapStorageStrategy(), 10000);
        testStrategy(new OurHashMapStorageStrategy(), 10000);
        testStrategy(new FileStorageStrategy(), 200);
        testStrategy(new OurHashBiMapStorageStrategy(), 10000);
        testStrategy(new HashBiMapStorageStrategy(), 10000);
        testStrategy(new DualHashBidiMapStorageStrategy(), 10000);
    }

    static Set<Long> getIds(Shortener shortener,Set<String> strings){
        Set<Long> result = new HashSet<>();
        for (String s :
                strings) {
            result.add(shortener.getId(s));
        }
        return result;
    }

    static  Set<String> getStrings(Shortener shortener, Set<Long> keys){
        Set<String> result = new HashSet<>();
        for (Long key :
                keys) {
            result.add(shortener.getString(key));
        }
        return result;
    }

    static void testStrategy(StorageStrategy strategy, long elementsNumber){
        Helper.printMessage(strategy.getClass().getSimpleName());

        Set<String> testStringSet = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            testStringSet.add(Helper.generateRandomString());
        }
        Shortener shortener = new Shortener(strategy);

        Date dateStart = new Date();
        Set<Long> testSetIds = Solution.getIds(shortener,testStringSet);
        long timeCost = new Date().getTime() - dateStart.getTime();
        Helper.printMessage(String.valueOf(timeCost));

        dateStart = new Date();
        Set<String> testStringFromSolution = Solution.getStrings(shortener, testSetIds);
        timeCost = new Date().getTime() - dateStart.getTime();
        Helper.printMessage(String.valueOf(timeCost));
        boolean equalsSets = testStringSet.equals(testStringFromSolution);
        String message = equalsSets ? "Тест пройден.": "Тест не пройден.";
        Helper.printMessage(message);

    }
}
