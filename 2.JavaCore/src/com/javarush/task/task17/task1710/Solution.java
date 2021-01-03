package com.javarush.task.task17.task1710;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/*
CRUD
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        try {
            allPeople.add(Person.createMale("Миронов", sdf.parse("15/04/1990")));  //сегодня родился    id=2
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //start here - начни тут
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
//      -c name sex bd
//      -u id name sex bd
//      -d id
//      -i id
//        name - имя, String
//        sex - пол, "м" или "ж", одна буква
//        bd - дата рождения в следующем формате 15/04/1990
//        -u - обновляет данные человека с данным id
//
//
//        id соответствует индексу в списке.
        switch (args[0]){
            case "-c":{
             //   -c - добавляет человека с заданными параметрами в конец allPeople, выводит id (index) на экран
                String name = args[1];
                Sex sex = null;
                if (args[2].equals("м")) sex = Sex.MALE;
                else if (args[2].equals("ж")) sex = Sex.FEMALE;
                Date date = null;
                try {
                    date = simpleDateFormat.parse(args[3]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (sex == Sex.FEMALE){
                    allPeople.add(Person.createFemale(name, date));
                } else if (sex == Sex.MALE) allPeople.add(Person.createMale(name, date));
                System.out.println(allPeople.size()-1);
            }
            case "-u":{
                int id = Integer.parseInt(args[1]);
                String name = args[2];
                Sex sex = null;
                if (args[3].equals("м")) sex = Sex.MALE;
                else if (args[3].equals("ж")) sex = Sex.FEMALE;
                Date date = null;
                try {
                    date = simpleDateFormat.parse(args[4]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                allPeople.get(id).setName(name);
                allPeople.get(id).setSex(sex);
                allPeople.get(id).setBirthDate(date);
            }
            case "-d":{
           //     -d - производит логическое удаление человека с id, заменяет все его данные на null
                int id = Integer.parseInt(args[1]);
                allPeople.get(id).setName(null);
                allPeople.get(id).setSex(null);
                allPeople.get(id).setBirthDate(null);

            }
            case "-i":{
             //   -i - выводит на экран информацию о человеке с id: name sex (м/ж) bd (формат 15-Apr-1990)
                //Миронов м 15-Apr-1990
                int id = Integer.parseInt(args[1]);
                StringBuilder sb = new StringBuilder();
                sb.append(allPeople.get(id).getName());
                sb.append(" ");
                if (allPeople.get(id).getSex().equals(Sex.FEMALE))
                    sb.append("ж");
                if (allPeople.get(id).getSex().equals(Sex.MALE))
                    sb.append("м");
                sb.append(" ");
                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
                sb.append(simpleDateFormat2.format(allPeople.get(id).getBirthDate()));
                System.out.println(sb);
            }
        }

    }
}
