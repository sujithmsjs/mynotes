package com.zetcode;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class MyFrame extends JFrame {
    
    public MyFrame() {

        initUI();
    }

    MyFrame(JPanel panel, String title) {
        super(title);
        add(panel);
    }

    private void initUI() {
        setTitle("Simple Java 2D example");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                MyFrame ex = new MyFrame();
                ex.setVisible(true);
            }
        });
    }
}
