package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {
    private List<String> linesList = new ArrayList<>();
    private Path logDir;

    public LogParser(Path path) {
        linesList = processFilesFromFolder(linesList, path.toFile());
        logDir = path;
    }

    private List<String> processFilesFromFolder(List<String> logs, File file) {
        File[] folderEntries = file.listFiles();
        for (File entry : folderEntries) {
            if (entry.isDirectory()) {
                processFilesFromFolder(logs, entry);
            }
            if (entry.isFile() && entry.getName().endsWith(".log")) {
                logs.addAll(loadListOfLogsFromFiles(entry));
            }
        }
        return logs;
    }

    private List<String> loadListOfLogsFromFiles(File entry) {
        List<String> listLogs = new ArrayList<>();
        try {
            listLogs = Files.readAllLines(entry.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listLogs;
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        Set<String> uniqueIPs = new HashSet<>();

        for (String line : linesList) {
            String[] parts = line.split("\\t");

            addStringEntity(after, before, uniqueIPs, parts, 0);
        }
        return uniqueIPs;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        Set<String> IPsForUser = new HashSet<>();

        for (String line : linesList) {
            String[] parts = line.split("\\t");

            if (user.equals(parts[1])) {
                addStringEntity(after, before, IPsForUser, parts, 0);
            }
        }
        return IPsForUser;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        Set<String> IPsForEvent = new HashSet<>();

        for (String line : linesList) {
            String[] parts = line.split("\\t");

            if (event.toString().equals(parts[3].split(" ")[0])) {
                addStringEntity(after, before, IPsForEvent, parts, 0);
            }
        }
        return IPsForEvent;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        Set<String> IPsForEvent = new HashSet<>();

        for (String line : linesList) {
            String[] parts = line.split("\\t");

            if (status.toString().equals(parts[4])) {
                addStringEntity(after, before, IPsForEvent, parts, 0);
            }
        }
        return IPsForEvent;
    }

    private <T> void addStringEntity(Date after, Date before, Set<T> enteties, String[] parts, int part) {
        long lineDateTime = getDate(parts[2]).getTime();

        if (isCompatibleDate(lineDateTime, after, before)) {
            if (part == 2) {
                enteties.add((T) getDate(parts[part]));
            } else enteties.add((T) parts[part]);
        }
    }

    private Date getDate(String part) {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);
        Date date = null;
        try {
            date = dateFormat.parse(part);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private boolean isCompatibleDate(long lineDateTime, Date after, Date before) {
        if (after == null && before == null) {
            return true;
        } else if (after == null) {
            if (lineDateTime <= before.getTime()) {
                return true;
            }
        } else if (before == null) {
            if (lineDateTime >= after.getTime()) {
                return true;
            }
        } else {
            if (lineDateTime >= after.getTime() && lineDateTime <= before.getTime()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Set<String> getAllUsers() {
        Set<String> allUsersSets = new HashSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            addStringEntity(null, null, allUsersSets, parts, 1);
        }

        return allUsersSets;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        Set<String> numberOfUsers = new HashSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            addStringEntity(after, before, numberOfUsers, parts, 1);
        }
        return numberOfUsers.size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        Set<String> userEvents = new HashSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (user.equals(parts[1])) {
                addStringEntity(after, before, userEvents, parts, 3);
            }

        }

        return userEvents.size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        Set<String> userForIP = new HashSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (ip.equals(parts[0])) {
                addStringEntity(after, before, userForIP, parts, 1);
            }

        }

        return userForIP;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        Set<String> userLogged = new HashSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (Event.LOGIN.toString().equals(parts[3]))
                addStringEntity(after, before, userLogged, parts, 1);
        }

        return userLogged;
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        Set<String> downloadedPluginUsers = new HashSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (Event.DOWNLOAD_PLUGIN.toString().equals(parts[3]))
                addStringEntity(after, before, downloadedPluginUsers, parts, 1);
        }

        return downloadedPluginUsers;
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        Set<String> wroteMessageUsers = new HashSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (Event.WRITE_MESSAGE.toString().equals(parts[3]))
                addStringEntity(after, before, wroteMessageUsers, parts, 1);
        }

        return wroteMessageUsers;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        Set<String> solvedTaskUsers = new HashSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (Event.SOLVE_TASK.toString().equals(parts[3].split(" ")[0]))
                addStringEntity(after, before, solvedTaskUsers, parts, 1);
        }

        return solvedTaskUsers;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        Set<String> solvedTaskUsers = new HashSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (Event.SOLVE_TASK.toString().equals(parts[3].split(" ")[0])
                    && parts[3].split(" ")[1].equals(String.valueOf(task)))
                addStringEntity(after, before, solvedTaskUsers, parts, 1);
        }
        return solvedTaskUsers;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        Set<String> doneTaskUsers = new HashSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (Event.DONE_TASK.toString().equals(parts[3].split(" ")[0]))
                addStringEntity(after, before, doneTaskUsers, parts, 1);
        }
        return doneTaskUsers;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        Set<String> doneTaskUsers = new HashSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (Event.DONE_TASK.toString().equals(parts[3].split(" ")[0])
                    && parts[3].split(" ")[1].equals(String.valueOf(task)))
                addStringEntity(after, before, doneTaskUsers, parts, 1);
        }
        return doneTaskUsers;
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        Set<Date> dates = new HashSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (user.equals(parts[1]) &&
                    event.toString().equals(parts[3].split(" ")[0]))
                addStringEntity(after, before, dates, parts, 2);
        }

        return dates;
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        Set<Date> dates = new HashSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (Status.FAILED.toString().equals(parts[4]))
                addStringEntity(after, before, dates, parts, 2);
        }

        return dates;
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        Set<Date> dates = new HashSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (Status.ERROR.toString().equals(parts[4]))
                addStringEntity(after, before, dates, parts, 2);
        }

        return dates;
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        TreeSet<Date> dates = new TreeSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (user.equals(parts[1]) && Event.LOGIN.toString().equals(parts[3]))
                addStringEntity(after, before, dates, parts, 2);
        }
        if (dates.isEmpty())
            return null;
        return dates.first();
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        TreeSet<Date> dates = new TreeSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (user.equals(parts[1]) &&
                    Event.SOLVE_TASK.toString().equals(parts[3].split(" ")[0]) &&
                    parts[3].split(" ")[1].equals(String.valueOf(task)))
                addStringEntity(after, before, dates, parts, 2);
        }
        if (dates.isEmpty())
            return null;
        return dates.first();
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        TreeSet<Date> dates = new TreeSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (user.equals(parts[1]) &&
                    Event.DONE_TASK.toString().equals(parts[3].split(" ")[0]) &&
                    parts[3].split(" ")[1].equals(String.valueOf(task)))
                addStringEntity(after, before, dates, parts, 2);
        }
        if (dates.isEmpty())
            return null;
        return dates.first();
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        TreeSet<Date> dates = new TreeSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (user.equals(parts[1]) &&
                    Event.WRITE_MESSAGE.toString().equals(parts[3]))
                addStringEntity(after, before, dates, parts, 2);
        }

        return dates;
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        TreeSet<Date> dates = new TreeSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (user.equals(parts[1]) &&
                    Event.DOWNLOAD_PLUGIN.toString().equals(parts[3]))
                addStringEntity(after, before, dates, parts, 2);
        }

        return dates;
    }

    //need refactoring
    private void addStringEntityForEvent(Date after, Date before, Set<Event> enteties, String[] parts, int part) {
        long lineDateTime = getDate(parts[2]).getTime();

        if (isCompatibleDate(lineDateTime, after, before)) {
            parts[3] = parts[3].split(" ")[0];

            enteties.add(Event.valueOf(parts[part]));
        }
    }


    @Override
    public int getNumberOfAllEvents(Date after, Date before) {

        return getAllEvents(after, before).size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        Set<Event> allEvents = new HashSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            addStringEntityForEvent(after, before, allEvents, parts, 3);
        }
        return allEvents;
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        Set<Event> eventsForIP = new HashSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (ip.equals(parts[0]))
                addStringEntityForEvent(after, before, eventsForIP, parts, 3);
        }
        return eventsForIP;
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        Set<Event> eventsForUser = new HashSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (user.equals(parts[1]))
                addStringEntityForEvent(after, before, eventsForUser, parts, 3);
        }
        return eventsForUser;
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        Set<Event> failedEvents = new HashSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (Status.FAILED.toString().equals(parts[4]))
                addStringEntityForEvent(after, before, failedEvents, parts, 3);
        }
        return failedEvents;
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        Set<Event> errorEvents = new HashSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (Status.ERROR.toString().equals(parts[4]))
                addStringEntityForEvent(after, before, errorEvents, parts, 3);
        }
        return errorEvents;
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        int count = 0;
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (Event.SOLVE_TASK.toString().equals(parts[3].split(" ")[0]) && //bad IF block for fast solution
                    task == Integer.parseInt(parts[3].split(" ")[1]) &&
                    isCompatibleDate(getDate(parts[2]).getTime(), after, before)) {
                count++;
            }

        }
        return count;
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        int count = 0;
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (Event.DONE_TASK.toString().equals(parts[3].split(" ")[0]) && //bad IF block for fast solution
                    task == Integer.parseInt(parts[3].split(" ")[1]) &&
                    isCompatibleDate(getDate(parts[2]).getTime(), after, before)) {
                count++;
            }
        }
        return count;
    }

    //need refactoring
    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> result = new HashMap<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (Event.SOLVE_TASK.toString().equals(parts[3].split(" ")[0])) {
                // Event event = Event.valueOf(parts[3].split(" ")[0]);
                int task = Integer.parseInt(parts[3].split(" ")[1]);
                if (isCompatibleDate(getDate(parts[2]).getTime(), after, before)) {
                    if (result.containsKey(task)) {
                        result.put(task, result.get(task) + 1);
                    } else {
                        result.put(task, 1);
                    }
                }
            }
        }

        return result;
    }

    //need refactoring
    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> result = new HashMap<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (Event.DONE_TASK.toString().equals(parts[3].split(" ")[0])) {
                //Event event = Event.valueOf(parts[3].split(" ")[0]);
                int task = Integer.parseInt(parts[3].split(" ")[1]);
                if (isCompatibleDate(getDate(parts[2]).getTime(), after, before)) {
                    if (result.containsKey(task)) {
                        result.put(task, result.get(task) + 1);
                    } else {
                        result.put(task, 1);
                    }
                }
            }
        }

        return result;
    }


    //    @Override
//    public Set<Object> execute(String query) {
//
//        Set<Object> result = new HashSet<>();
//
//        switch (query) {
//            case ("get ip"):
//                result.addAll(getUniqueIPs(null, null));
//                break;
//            case ("get user"):
//                result.addAll(getAllUsers());
//                break;
//            case ("get date"):
//                for (String str : linesList) {
//                    String[] parts = str.split("\\t");
//                    result.add(getDate(parts[2]));
//                }
//                break;
//            case ("get event"):
//                Collections.addAll(result, Event.values());
//                break;
//            case ("get status"):
//                Collections.addAll(result, Status.values());
//                break;
//        }
//
//        return result;
//    }

    //take solution andrewkalkin for fast complete the tasks

    private List<LogRecord> getParsedRecords(Path logDir) {
        List<LogRecord> recordList = new ArrayList<>();
        try {
            for (File file : logDir.toFile().listFiles()) {
                if (file.isFile() && file.getName().toLowerCase().endsWith(".log"))
                    for (String record : Files.readAllLines(file.toPath())) {
                        recordList.add(new LogRecord(record));
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return recordList;
    }

    private boolean isDateInside(Date after, Date before, Date currentDate) {
        if (after != null) {
            if (currentDate.getTime() <= after.getTime())
                return false;
        }
        if (before != null) {
            if (currentDate.getTime() >= before.getTime())
                return false;
        }
        return true;
    }

    @Override
    public Set<Object> execute(String query) {
        Set<Object> res = new HashSet<>();
        if (query == null || query.isEmpty()) return res;
        Pattern p = Pattern.compile("get (ip|user|date|event|status)"
                + "( for (ip|user|date|event|status) = \"(.*?)\")?"
                + "( and date between \"(.*?)\" and \"(.*?)\")?");
        Matcher m = p.matcher(query);
        String field1 = null;
        String field2 = null;
        String value1 = null;
        Date dateFrom = null;
        Date dateTo = null;
        if (m.find()) {
            field1 = m.group(1);
            field2 = m.group(3);
            value1 = m.group(4);
            String d1 = m.group(6);
            String d2 = m.group(7);
            try {
                dateFrom = new SimpleDateFormat("d.M.yyyy H:m:s").parse(d1);
            } catch (Exception e) {
                dateFrom = null;
            }
            try {
                dateTo = new SimpleDateFormat("d.M.yyyy H:m:s").parse(d2);
            } catch (Exception e) {
                dateTo = null;
            }
            switch (field1) {
                case "ip": {
                    res.addAll(getAllIps(field2, value1, dateFrom, dateTo));
                    break;
                }
                case "user": {
                    res.addAll(getAllUsers(field2, value1, dateFrom, dateTo));
                    break;
                }
                case "date": {
                    res.addAll(getAllDates(field2, value1, dateFrom, dateTo));
                    break;
                }
                case "event": {
                    res.addAll(getAllEvents(field2, value1, dateFrom, dateTo));
                    break;
                }
                case "status": {
                    res.addAll(getAllStatuses(field2, value1, dateFrom, dateTo));
                    break;
                }
            }
        }
        return res;
    }

    private Set<String> getAllIps(String field, String value, Date after, Date before) {
        Set<String> users = new HashSet<>();
        for (LogRecord record : getParsedRecords(logDir)) {
            try {
                if (isDateInside(after, before, record.getDate()) && isFieldMatch(field, value, record)) {
                    users.add(record.getIp());
                }
            } catch (ParseException e) {
                //e.printStackTrace();
            }
        }
        return users;
    }

    private Set<Date> getAllDates(String field, String value, Date after, Date before) {
        Set<Date> dates = new HashSet<>();
        for (LogRecord record : getParsedRecords(logDir)) {
            try {
                if (isDateInside(after, before, record.getDate()) && isFieldMatch(field, value, record)) {
                    dates.add(record.date);
                }
            } catch (ParseException e) {
                //e.printStackTrace();
            }
        }
        return dates;
    }

    private Set<Status> getAllStatuses(String field, String value, Date after, Date before) {
        Set<Status> set = new HashSet<>();
        for (LogRecord record : getParsedRecords(logDir)) {
            try {
                if (isDateInside(after, before, record.getDate()) && isFieldMatch(field, value, record)) {
                    set.add(record.getStatus());
                }
            } catch (ParseException e) {
                //e.printStackTrace();
            }
        }
        return set;
    }

    private Set<Event> getAllEvents(String field, String value, Date after, Date before) {
        Set<Event> set = new HashSet<>();
        for (LogRecord record : getParsedRecords(logDir)) {
            try {
                if (isDateInside(after, before, record.getDate()) && isFieldMatch(field, value, record)) {
                    set.add(record.getEvent());
                }
            } catch (ParseException e) {
                //e.printStackTrace();
            }
        }
        return set;
    }

    private Set<String> getAllUsers(String field, String value, Date after, Date before) {
        Set<String> users = new HashSet<>();
        for (LogRecord record : getParsedRecords(logDir)) {
            try {
                if (isDateInside(after, before, record.getDate()) && isFieldMatch(field, value, record)) {
                    users.add(record.getUser());
                }
            } catch (ParseException e) {
                //e.printStackTrace();
            }
        }
        return users;
    }

    private boolean isFieldMatch(String field, String value, LogRecord record) throws ParseException {
        boolean criteria = false;
        if (field == null) return true;
        if (value == null) return false;
        switch (field) {
            case "ip": {
                criteria = record.getIp().equals(value);
                break;
            }
            case "user": {
                criteria = record.getUser().equals(value);
                break;
            }
            case "date": {
                criteria = record.getDate().equals(new SimpleDateFormat("d.M.yyyy H:m:s").parse(value));
                break;
            }
            case "event": {
                criteria = record.getEvent().equals(Event.valueOf(value));
                break;
            }
            case "status": {
                criteria = record.getStatus().equals(Status.valueOf(value));
                break;
            }
        }
        return criteria;
    }

    private class LogRecord {
        private String ip;
        private String user;
        private Date date;
        private Event event;
        private String taskNumber;
        private Status status;

        public LogRecord(String ip, String user, Date date, Event event, Status status) {
            this.ip = ip;
            this.user = user;
            this.date = date;
            this.event = event;
            this.status = status;
        }

        public LogRecord(String record) {
            String[] strings = record.split("\t");
            this.ip = strings[0].trim();
            this.user = strings[1];
            SimpleDateFormat dateFormat = new SimpleDateFormat("d.M.yyyy H:m:s");
            try {
                date = dateFormat.parse(strings[2]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String eventAndParameter[] = strings[3].split(" ");
            event = Event.valueOf(eventAndParameter[0]);
            if (eventAndParameter.length > 1) taskNumber = eventAndParameter[1];
            status = Status.valueOf(strings[4]);
        }

        public String getIp() {
            return ip;
        }

        public String getUser() {
            return user;
        }

        public Date getDate() {
            return date;
        }

        public Event getEvent() {
            return event;
        }

        public String getTaskNumber() {
            return taskNumber;
        }

        public Status getStatus() {
            return status;
        }
    }
}