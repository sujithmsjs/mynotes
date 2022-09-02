package learn.multi;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.JFrame;

public class RunningTest {

    public static void main(String[] args) {
        //Creating a Home frame.
        JFrame f = new JFrame();
        f.setTitle("Running Test");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(new Dimension(400, 300));

        //Getting user input
        Scanner scan = new Scanner(System.in);
        System.out.println("Inter the number of players:");
        int noPl = scan.nextInt();

        //Getting a Container.
        Container c = f.getContentPane();
        c.setLayout(new GridLayout(noPl, 1));

        Vector<Runner> vec = new Vector<Runner>();

        for (int i = 0; i < noPl; i++) {
            System.out.println("Enter playername " + (i + 1) + " :");
            String name = scan.next();
            Runner r = new Runner(name, 100);
            vec.add(r);
            c.add(r);
        }
        System.out.println("Running starting...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }

        f.setVisible(true);
        
        try {
            
            
            //Starting all the threads.
            for (int i = 0; i < vec.size(); i++) {
                Runner child = vec.get(i);
                child.start();
            }
            
            //Joining all threads with main.
            for (int i = 0; i < vec.size(); i++) {
                Runner child = vec.get(i);
                System.out.println(child.getName()+" Joined with main."+System.currentTimeMillis());
                child.getThread().join();
                
            }
            
            
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
                
        f.setVisible(false);
        f.dispose();

        Collections.sort(vec);
        
        System.out.println("Results: ");
        for (int i = 0; i < vec.size(); i++) {
            System.out.println((i+1)+". "+vec.get(i).getName());
        }
    }

}
