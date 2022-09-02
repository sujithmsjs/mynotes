package learn.multi;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;


public class RunnableDemo3 {

    public static void main(String[] args) {
        //Creating a Home frame.
        JFrame f = new JFrame();
        f.setTitle("Running Test");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(new Dimension(400,300));
        
        //Getting a Container.
        Container pane = f.getContentPane();
        pane.setLayout(new java.awt.GridLayout());
        
        
        
        Runner r = new Runner("Sujith",100);
    }

}
