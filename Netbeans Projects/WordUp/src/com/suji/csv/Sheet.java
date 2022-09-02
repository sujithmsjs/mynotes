package com.suji.csv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class Sheet {

    private String emptyValue = "empt";
    private List<List<String>> sheet;

    public Sheet() {
        sheet = new ArrayList<List<String>>();
    }

    public void addList(List<String> list) {
        sheet.add(list);
    }

    public String removeValue(int row, int col) {

        String value = emptyValue;
        try {
            value = sheet.get(row).remove(col);
        } catch (Exception ex) {
            value = emptyValue;
        }

        return value;
    }

    public String getValue(int row, int col) {
        String value = emptyValue;
        try {
            value = sheet.get(row).get(col);
        } catch (Exception ex) {
            value = emptyValue;
        }
        return value;
    }

    public void removeEmpties() {
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(i); j++) {
                if (sheet.get(i).get(j).equalsIgnoreCase(emptyValue)) {
                    // sheet.get(i).remove(j);
                    removeValue(i, j);
                    j--;
                }
            }
        }
    }

    public int getColumns(int row) {
        return sheet.get(row).size();
    }

    public int getRows() {
        return sheet.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (List<String> list : sheet) {
            sb.append(list).append("\n");
        }
        return sb.toString();
    }

    public boolean SaveFile(File file) {
        
        PrintWriter pw = null;
        FileWriter fw = null;
        
        
        
        boolean isSaved = false;
        try {
            fw = new FileWriter(file, true);
            pw = new PrintWriter(fw);
          
            for (int i = 0; i < getRows(); i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < getColumns(i); j++) {
                    sb.append(getValue(i, j)).append(",");
                }
                sb.delete(sb.length() - 1, sb.length());
                System.out.println(sb);
                pw.println(sb.toString());
            }
            isSaved = true;
        } catch (Exception ex) {
            isSaved = false;
        } finally {
            pw.close();
        }

        return isSaved;
    }

}
