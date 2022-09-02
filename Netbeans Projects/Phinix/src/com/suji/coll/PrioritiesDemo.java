package com.suji.coll;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrioritiesDemo {

    public static void main(String[] args) {
        Properties p = new Properties();

        String fileName = "suji.properties";
        File file = new File(fileName);
        try {
            p.load(new FileReader(file));
            System.out.println(p.getProperty("db"));
            System.out.println(p.getProperty("port"));
        } catch (IOException ex) {
            System.out.println("File Not Found");
        }
    }

    public static void writeProperties() {
        Properties p = new Properties();
        p.setProperty("db", "MySql");
        p.setProperty("port", "3360");
        p.setProperty("name", "sujiDB");
        String fileName = "suji.properties";
        File file = new File(fileName);
        try {
            p.store(new FileWriter(fileName), "Just Checking");
        } catch (IOException ex) {
            System.out.println("File Not Found");
        }
        System.out.println(file.getPath());
    }

}
