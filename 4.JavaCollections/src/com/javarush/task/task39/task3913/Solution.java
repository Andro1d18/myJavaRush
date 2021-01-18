package com.javarush.task.task39.task3913;

import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("D:/javarush/"));
        //System.out.println(logParser.getNumberOfUniqueIPs(null, new Date()));

        Set<String> allUsers = logParser.getAllUsers();
        print("Все пользователи", allUsers);


        Calendar after = new GregorianCalendar(2012, 0, 1);
        Calendar before = new GregorianCalendar(2022, 0, 1);
        String testUser = "Eduard Petrovich Morozko";
        int number = logParser.getNumberOfUserEvents(testUser, after.getTime(), before.getTime());
        System.out.printf("Число уникальных событий для %s \nпосле %s \nи до %s \nравняется %s\n", testUser, after.getTime(), before.getTime(), number);


        Set<String> usersForIP = logParser.getUsersForIP("127.0.0.1", null, null);
        Set<String> usersForIP1 = logParser.getUsersForIP("146.34.15.5", null, null);
        print("Пользователи с IP 127.0.0.1 в заданный период дат", usersForIP);
        print("Пользователи с IP 146.34.15.5 в заданный период дат", usersForIP1);

        Set<String> loggedUsers = logParser.getLoggedUsers(after.getTime(), before.getTime());
        print("Пользователи совершавшие LOGIN в заданный период дат", loggedUsers);

        Set<String> downloadedPlugUsers = logParser.getDownloadedPluginUsers(after.getTime(), before.getTime());
        print("Пользователи совершавшие DOWNLOADED_PLUGIN в заданный период дат", downloadedPlugUsers);

        Set<Date> dateUserDoEvent = logParser.getDatesForUserAndEvent(testUser, Event.WRITE_MESSAGE, after.getTime(), before.getTime());
        print("Получение Дат для testUser и события в заданный период дат ", dateUserDoEvent);

        Set<Date> dateWrote = logParser.getDatesWhenUserWroteMessage(testUser, after.getTime(), before.getTime());
        print("Получение Дат для testUser и события WROTE MESSAGE в заданный период дат", dateWrote);

        int count = logParser.getNumberOfAllEvents(after.getTime(), before.getTime());
        System.out.println("Всего событий в заданный период дат: " + count);

        Map<Integer, Integer> map = logParser.getAllSolvedTasksAndTheirNumber(after.getTime(), before.getTime());
        for (Map.Entry<Integer, Integer> entry :
                map.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
        map = logParser.getAllDoneTasksAndTheirNumber(after.getTime(), before.getTime());
        for (Map.Entry<Integer, Integer> entry :
                map.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
        Set<Object> allUsersObj = logParser.execute("get user");
        print("execute get user ", allUsersObj);

        allUsersObj = logParser.execute("get event for date = \"30.01.2014 12:56:22\"");
        print("get event for date = \"30.01.2014 12:56:22″", allUsersObj);

        allUsersObj = logParser.execute(" get user for event = \"DONE_TASK\"");
        print(" get user for event = \"DONE_TASK\"", allUsersObj);


    }

    private static <T> void print(String text, Set<T> set) {
        System.out.println(text);
        for (T s :
                set) {
            System.out.println(s);
        }
        System.out.println();
    }
}