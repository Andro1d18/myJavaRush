package com.javarush.task.task33.task3303;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

/* 
Десериализация JSON объекта
*/
public class Solution {
    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz) throws IOException {

        FileReader fr = new FileReader(fileName);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(fr, clazz); //Дженерики мощная штука! Возрвщается тип объекта T - тот же, который подавался в сигнатуре метода!
    }

    public static void main(String[] args) {

        //Для тестирования создаём объект и записываем его в файл
        writeJSONtoFile();
        try {
            Cat cat = convertFromJsonToNormal("D:\\javarush\\allFilesContent.txt", Cat.class);
            System.out.println("kotyara name " + cat.name + " age = " + cat.age);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Для теста
    private static void writeJSONtoFile(){
        Cat cat = new Cat();
        cat.age = 2;
        cat.name = "musya";


        try {
            OutputStream outputStream = new FileOutputStream("D:\\javarush\\allFilesContent.txt");
            StringWriter stringWriter = new StringWriter();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(outputStream, cat);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @JsonAutoDetect
    public static class Cat {
        public String name;
        public int age;

        public Cat (){}
    }
}
