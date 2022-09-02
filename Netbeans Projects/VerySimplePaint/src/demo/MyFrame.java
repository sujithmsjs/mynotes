package demo;

import com.jtattoo.plaf.noire.NoireLookAndFeel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
 
public class MyFrame extends JFrame{
    private JPanel canvasPanel;
    private JMenu editMenu;
    private JMenu fileMenu;
    private JMenuBar menuBar;
    private JScrollPane scrollPane;

    JPanel panel;

    public MyFrame(JPanel panel, String title) {
        this.panel = panel;
        add(panel);
        initUI();
        setTitle(title);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        System.out.println(getLayout());
    }
    
    public void initUI() {
        
        scrollPane = new javax.swing.JScrollPane();
        canvasPanel = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        editMenu = new javax.swing.JMenu();
        
        scrollPane.setPreferredSize(new java.awt.Dimension(600, 400));
        scrollPane.setViewportView(canvasPanel);
        scrollPane.setBackground(Color.BLACK);
        getContentPane().add(scrollPane, java.awt.BorderLayout.CENTER);
        
        canvasPanel.setBackground(new java.awt.Color(0, 153, 153));
        canvasPanel.setPreferredSize(new java.awt.Dimension(2000, 2000));
        
        
        menuBar.setBackground(new java.awt.Color(51, 51, 51));
        fileMenu.setText("File");
        menuBar.add(fileMenu);
        editMenu.setText("Edit");
        menuBar.add(editMenu);
       // setJMenuBar(menuBar);
    }
    
    public static void main(String[] args) {
        
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
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
              //  new MyFrame().setVisible(true);
            }
        });
        
        
        
    }
}
