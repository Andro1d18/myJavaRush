package com.javarush.task.task19.task1921;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat spl = new SimpleDateFormat("dd mm yyyy");
        int mm = 11;
        int yy = 1111;
        int dd = 22;
        Date date = spl.parse(dd + " " +mm+ " "+yy );

    }
}
