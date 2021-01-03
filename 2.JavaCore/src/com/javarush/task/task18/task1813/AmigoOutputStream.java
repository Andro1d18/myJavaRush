package com.javarush.task.task18.task1813;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/* 
AmigoOutputStream
*/

public class AmigoOutputStream extends FileOutputStream{
    public static String fileName = "C:/tmp/result.txt";

    private FileOutputStream originals;
//    public AmigoOutputStream(FileOutputStream fileOutputStream) throws FileNotFoundException {
//        originals= fileOutputStream;
//     //   originals()
//        super(` `)
//    }
//    public AmigoOutputStream (String fileName) throws FileNotFoundException {
//        super(fileName);
//    }
    public AmigoOutputStream (FileOutputStream fileOutputStream) throws FileNotFoundException {
        super(fileName);
        originals=fileOutputStream;
    }

    @Override
    public void write(int b) throws IOException {
        originals.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        originals.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        originals.write(b, off, len);
    }



    @Override
    public void flush() throws IOException {
        originals.flush();
    }

    @Override
    public void close() throws IOException {
        originals.flush();
        originals.write("JavaRush © All rights reserved.".getBytes());
        originals.close();
    }

    public static void main(String[] args) throws FileNotFoundException {
        new AmigoOutputStream(new FileOutputStream(fileName));
    }

}
