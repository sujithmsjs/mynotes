
import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;

public class JTextPaneTest {

    public static void main(String args[]) throws BadLocationException {
        JFrame frame = new JFrame("JTextPane Test");
        Container cp = frame.getContentPane();
        
        JTextPane pane = new JTextPane();
        
        SimpleAttributeSet set = new SimpleAttributeSet();
        
        StyleConstants.setBold(set, true);
        
        pane.setCharacterAttributes(set, true);
        
        pane.setText("Welcome to");
        
        set = new SimpleAttributeSet();
        
        StyleConstants.setItalic(set, true);
        StyleConstants.setForeground(set, Color.blue);
        
        
        
        Document doc = pane.getStyledDocument();
        doc.insertString(doc.getLength(), " Tutorials ", set);
        
        
        
        
        set = new SimpleAttributeSet();
        StyleConstants.setFontSize(set, 20);
        
        doc.insertString(doc.getLength(), " Point", set);
        
        JScrollPane scrollPane = new JScrollPane(pane);
        cp.add(scrollPane, BorderLayout.CENTER);
        frame.setSize(375, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
