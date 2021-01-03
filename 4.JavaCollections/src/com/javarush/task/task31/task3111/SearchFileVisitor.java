package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {

    private String partOfName;
    private String partOfContent;
    private int minSize;
    private int maxSize;
    private boolean minSizeFlagEnable = false;
    private boolean maxSizeFlagEnable = false;

    private List<Path> foundFiles= new ArrayList<>();


    public List<Path> getFoundFiles() {
        return foundFiles;
    }

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;

    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
        minSizeFlagEnable = true;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
        maxSizeFlagEnable = true;
    }

    public String getPartOfName() {
        return partOfName;
    }

    public String getPartOfContent() {
        return partOfContent;
    }

    public int getMinSize() {
        return minSize;
    }

    public int getMaxSize() {
        return maxSize;
    }

    //15.09 остановился на дохренище флагов, которые работают некорректно: один раз включившись, они всё время включены
    //нужно переделать на пример с хабра, но подумать как бысть с макс-мин байтами

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        byte[] content = Files.readAllBytes(file); // размер файла: content.length

        boolean partOfNameFlag = true;
        boolean partOfContentFlag = true;
        boolean minSizeFlag = true;
        boolean maxSizeFlag = true;

        if (minSizeFlagEnable){
            if(! (getMinSize()<content.length))
                minSizeFlag = false;
        }
        if (maxSizeFlagEnable) {
            if (!(content.length < getMaxSize()))
                maxSizeFlag = false;
        }

        String s = new String(content);
        if (!(getPartOfContent()==null) && !s.contains(getPartOfContent()))
            partOfContentFlag = false;

        String s2 = file.toString();
        if (!(getPartOfName()==null) && !s2.contains(getPartOfName()))
            partOfNameFlag= false;

        if (minSizeFlag & maxSizeFlag  & partOfContentFlag & partOfNameFlag)
            foundFiles.add(file);
        return super.visitFile(file, attrs);
    }
}
