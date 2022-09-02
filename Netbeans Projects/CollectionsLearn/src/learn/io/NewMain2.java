package learn.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;


public class NewMain2 {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String fileName="C:\\Users\\sujit\\OneDrive\\Desktop\\demo.txt";
        Scanner scan = new Scanner(System.in);   
        File f = new File(fileName);

        // Creating FileOutPut stream.
        FileOutputStream fos = new FileOutputStream(f);
        
        //Getting text from console.
        System.out.println("Write text:");
        String text = scan.nextLine();
        
        //Writting text into file.
        for (int i = 0; i < text.length(); i++) {
            fos.write(text.charAt(i));
        }
        
        //Closing Output stream.
        fos.close();
        
        //File Read.
        FileInputStream fis = new FileInputStream(f);
        int ch;
        
        //Reading data
        while((ch=fis.read())>-1){
            System.out.print((char)ch);
        }
        
        //Closing Input Stream.
        fis.close();
        
        System.out.println();
        
        
        
        
        
        
        
    }

}
