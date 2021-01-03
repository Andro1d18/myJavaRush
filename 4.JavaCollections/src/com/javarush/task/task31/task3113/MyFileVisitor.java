package com.javarush.task.task31.task3113;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class MyFileVisitor extends SimpleFileVisitor<Path> {
    private int countFiles;
    private int countDirectories;
    private long totalSpace;

    public long getTotalSize() {
        return totalSpace;
    }

    public int getCountDirectories() {
        return countDirectories;
    }

    public int getCountFiles() {
        return countFiles;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        if (Files.isRegularFile(file))
            countFiles++;
        totalSpace =+ totalSpace + Files.size(file);
        return super.visitFile(file, attrs);
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        if (Files.isDirectory(dir))
            countDirectories++;
        return super.preVisitDirectory(dir, attrs);
    }
}
