package com.javarush.task.task37.task3702.male;


import com.javarush.task.task37.task3702.AbstractFactory;
import com.javarush.task.task37.task3702.Human;

/*
1. Создай публичный класс MaleFactory в пакете male с публичным методом getPerson, который принимает один параметр int age
(возраст) и возвращает нужный объект одного из классов, реализующих интерфейс Human.

2. Логика метода getPerson: используя константу MAX_AGE определи, какой класс соответствует переданному возрасту.
Например, age=4 соответствует мальчику (KidBoy), age=15 - подростку (TeenBoy), остальной возраст - взрослому мужчине.

3. В методе main класса Solution создай фабрику и вызови у нее метод getPerson три раза с такими параметрами: 99, 4, 15.
 */
public class MaleFactory implements AbstractFactory {
    public Human getPerson(int age){
        if (age <= KidBoy.MAX_AGE )
            return new KidBoy();
        if (age <= TeenBoy.MAX_AGE)
            return new TeenBoy();
        return new Man();
    }
}
