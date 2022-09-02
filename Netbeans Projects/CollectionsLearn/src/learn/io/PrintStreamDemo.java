package learn.io;

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;


public class PrintStreamDemo {

    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "C:\\Users\\sujit\\OneDrive\\Desktop\\demo.txt";
        Scanner scan = new Scanner(System.in);
        File f = new File(fileName);
        Console console = System.console();
       

        //Creating PrintStream object.
        PrintStream ps = new PrintStream(f);
        ps.println();
        ps.println("Hello, this is sujith manchala.");
        ps.close();
        
        
    }

}
