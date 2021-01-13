package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.IPQuery;

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

public class LogParser implements IPQuery{
    private List<String> linesList = new ArrayList<>();

    public LogParser(Path path) {
        linesList = processFilesFromFolder(linesList,path.toFile());
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

    private void addStringEntity(Date after, Date before, Set<String> enteties, String[] parts, int part) {
        long lineDateTime = getDate(parts[2]).getTime();

        if (isCompatibleDate(lineDateTime, after, before)) {
            enteties.add(parts[part]);
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
}