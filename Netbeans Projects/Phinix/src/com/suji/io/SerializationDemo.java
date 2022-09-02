package com.suji.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SerializationDemo {

    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {

        String pathname = "C:\\Users\\sujit\\OneDrive\\Desktop\\JavaFiles\\Suji.object";
        File file = new File(pathname);

        Emp e = new Emp(300, "Sujith", 5_000_000, "Yellandu");
        writeObject(file, e);

        Object obj = readObject(file);
        Emp e2 = (Emp) obj;
        System.out.println(e2);
       
        
    }

    public static boolean writeObject(File file, Object obj) throws FileNotFoundException, IOException {

        FileOutputStream fos = new FileOutputStream(file);

        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(obj);

        System.out.println("Object has been written!");

        fos.close();
        oos.close();
        
        return file.exists();
    }
    
    public static Object readObject(File file) throws FileNotFoundException, IOException, ClassNotFoundException {

        FileInputStream fis = new FileInputStream(file);

        ObjectInputStream ois = new ObjectInputStream(fis);

        Object obj = ois.readObject();
        
        fis.close();
        ois.close();

        return obj;
    }


}
