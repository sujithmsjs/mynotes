package com.suji.exp;

import java.io.FileInputStream;
import java.io.IOException;


public class NewMain1 {

    public static void main(String[] args) throws IOException {
        demoAddSuppressedException("");
    }

    public static void demoAddSuppressedException(String filePath) throws IOException {
        Throwable firstException = null;
        FileInputStream fileIn = null;
        try {
            fileIn = new FileInputStream(filePath);
        } catch (IOException e) {
            firstException = e;
        } finally {
            try {
                fileIn.close();
            } catch (NullPointerException npe) {
                if (firstException != null) {
                    npe.addSuppressed(firstException);
                }
                throw npe;
            }
        }
    }
    
    
}
