package com.javarush.task.task16.task1631;

import com.javarush.task.task16.task1631.common.BmpReader;
import com.javarush.task.task16.task1631.common.ImageReader;
import com.javarush.task.task16.task1631.common.ImageTypes;
import com.javarush.task.task16.task1631.common.JpgReader;

public  class ImageReaderFactory {
    public static ImageReader getImageReader(ImageTypes types){
        if (types == ImageTypes.BMP){
            return new BmpReader();
        }
        else throw new IllegalArgumentException("Неизвестный тип картинки");
    }
}
