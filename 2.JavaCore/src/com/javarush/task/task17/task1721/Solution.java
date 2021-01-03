package com.javarush.task.task17.task1721;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Транзакционность
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader bfr = new BufferedReader(new FileReader(bufferedReader.readLine()));
        String line;
        while ((line = bfr.readLine()) != null){
            allLines.add(line);
        }
        bfr.close();
        BufferedReader bfr2 = new BufferedReader(new FileReader(bufferedReader.readLine()));
        while ((line = bfr2.readLine()) != null){
            forRemoveLines.add(line);
        }
        try {
            Solution sol = new Solution();
            sol.joinData();
        }catch (CorruptedDataException ce){

        } catch (Exception e){};
    }

    public void joinData() throws CorruptedDataException {
        if (allLines.contains(forRemoveLines)){
            allLines.removeAll(forRemoveLines);
        } else throw  new CorruptedDataException();

    }
}
