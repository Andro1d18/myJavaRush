package com.javarush.task.task19.task1901;

/* 
TableAdapter
*/

import javafx.scene.control.Tab;

import java.sql.SQLOutput;

public class Solution {
    public static void main(String[] args) {
        //это пример вывода
        ATable aTable = new ATable() {
            @Override
            public String getCurrentUserName() {
                return "Amigo";
            }

            @Override
            public String getTableName() {
                return "DashboardTable";
            }
        };

        BTable table = new TableAdapter(aTable);
        System.out.println(table.getHeaderText());
    }

    public static class TableAdapter implements BTable{

        ATable aTable;

        public TableAdapter(ATable aTable){
            this.aTable = aTable;
        }

        @Override
        public String getHeaderText() {
            return aTable.getCurrentUserName() + aTable.getTableName();
        }
    }

    public interface ATable {
        String getCurrentUserName();
        String getTableName();
    }

    public interface BTable {
        String getHeaderText();
    }
}