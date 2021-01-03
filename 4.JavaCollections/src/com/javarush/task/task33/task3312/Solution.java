package com.javarush.task.task33.task3312;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;

/* 
Сериализация зоопарка
*/
public class Solution {
    public static void main(String[] args) throws JsonProcessingException {
        Zoo.Dog dog = new Zoo.Dog("doggy");
        Zoo.Cat cat = new Zoo.Cat("catty");
        Zoo zoo = new Zoo();
        zoo.animals.add(dog);
        zoo.animals.add(cat);

        ObjectMapper om = new ObjectMapper();
        StringWriter sw = new StringWriter();
        try {
            om.writeValue(sw,zoo);
            Zoo zoo2 = om.readValue(sw.toString(),Zoo.class);
            for (int i = 0; i < zoo2.animals.size(); i++) {
                System.out.println(zoo.animals.get(i).getClass().getSimpleName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String result = new ObjectMapper().writeValueAsString(zoo);

        System.out.println(result);
    }
}
