package com.javarush.task.task33.task3313;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/* 
Сериализация даты в JSON
*/
public class Solution {
    public static void main(String[] args) throws JsonProcessingException {
        Event event = new Event("event#1");

       // DateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String result = new ObjectMapper().writeValueAsString(event);

        System.out.println(result);
    }
}
