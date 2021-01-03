package com.javarush.task.task15.task1527;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

/* 
Парсер реквестов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        //add your code here

        ArrayList<String> objList = new ArrayList<>();

        BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
        String stringURL = reader.readLine();

        //собираем всем параметры в одну строку
        String paramert = stringURL.substring(stringURL.indexOf("?")+1);

        //заполняем список параметрами и значениями
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < paramert.length(); i++) {
            if (paramert.charAt(i)!="&".charAt(0)){
                sb.append(paramert.charAt(i));
            } else{
                objList.add(sb.toString());
                sb = new StringBuilder();
            }
            if (i==paramert.length()-1){
                objList.add(sb.toString());
            }
        }

        //составляем итоговую пару ключ=значение
        HashMap<String, String> map = new HashMap<>();

        for (int i = 0; i < objList.size(); i++) {
            String[] str = objList.get(i).split("=");
            objList.get(i).split("[?& ]");
            if (str.length>1){
                map.put(str[0], str[1]);
            }
            else map.put(str[0],"");
        }

        System.out.println(map);


    }

    public static void alert(double value) {
        System.out.println("double: " + value);
    }

    public static void alert(String value) {
        System.out.println("String: " + value);
    }
}
