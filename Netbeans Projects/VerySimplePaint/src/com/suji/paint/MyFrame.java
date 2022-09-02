package com.suji.paint;

import javafx.scene.canvas.Canvas;
import javax.swing.*;

public class MyFrame extends JFrame {

    private JScrollPane scrollPane;
    private Board board;
    
    public MyFrame() {

        initUI();
        setTitle("My Paint Application.");
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        System.out.println(getLayout());
    }

    public void initUI() {
        board = new Board();
        scrollPane = new JScrollPane();
        
        scrollPane.setPreferredSize(new java.awt.Dimension(Canvas.SCREEN_SIZE));
        scrollPane.setViewportView(board);

       // getContentPane().add(scrollPane, java.awt.BorderLayout.CENTER);
       add(board);

    }
    

    public static void main(String[] args) {

        Board b = new Board();
        new MyFrame().setVisible(true);
                
        //<editor-fold defaultstate="collapsed" desc="GUI">
/*
try {
NoireLookAndFeel.setTheme("Large-Font");
UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
} catch (ClassNotFoundException ex) {
Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
} catch (InstantiationException ex) {
Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
} catch (IllegalAccessException ex) {
Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
} catch (UnsupportedLookAndFeelException ex) {
Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
}

Create and display the form
java.awt.EventQueue.invokeLater(new Runnable() {
public void run() {
Board b = new Board();
new MyFrame(b,"Demo").setVisible(true);
}
});
*/

//</editor-fold>
    }
    
}
