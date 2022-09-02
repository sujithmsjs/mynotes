package com.suji.exp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class NewMain {

    public static void main(String[] args) throws IOException {
        FileInputStream fileIn = null;
        try {
            String filePath = null;
            fileIn = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            throw new IOException(e);
        } finally {
            fileIn.close();
        }
    }

}
