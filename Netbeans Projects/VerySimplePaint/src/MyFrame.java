import com.jtattoo.plaf.noire.NoireLookAndFeel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class MyFrame extends JFrame {

    private JPanel canvasPanel;
//    private JMenu editMenu;
//    private JMenu fileMenu;
//    private JMenuBar menuBar;
    private JScrollPane scrollPane;

    public MyFrame(Board panel, String title) {
        canvasPanel = panel;
        initUI();
        setTitle(title);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        System.out.println(getLayout());
    }

    public void initUI() {

        scrollPane = new JScrollPane();
     //   canvasPanel = new Board();
//        menuBar = new JMenuBar();
//        fileMenu = new JMenu();
//        editMenu = new JMenu();

        scrollPane.setPreferredSize(new java.awt.Dimension(Canvas.SCREEN_SIZE));
        scrollPane.setViewportView(canvasPanel);

        
        
       // scrollPane.setBackground(Color.BLACK);
        getContentPane().add(scrollPane, java.awt.BorderLayout.CENTER);

//        menuBar.setBackground(new java.awt.Color(51, 51, 51));
//        fileMenu.setText("File");
//        menuBar.add(fileMenu);
//        editMenu.setText("Edit");
//        menuBar.add(editMenu);
        // setJMenuBar(menuBar);
    }

    
}
