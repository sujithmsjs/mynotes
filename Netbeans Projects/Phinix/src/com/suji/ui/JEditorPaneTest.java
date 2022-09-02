
import javax.swing.*;

public class JEditorPaneTest extends JFrame {

    public JEditorPaneTest() {
        setTitle("JEditorPane Test");
        JEditorPane editorPane = new JEditorPane();
        editorPane.setContentType("text/html");
        editorPane.setText("<h1 color:red>Java</h1><p>is a general-purpose computer programming language that is       concurrent, class-based, object-oriented, and specifically designed to have as few          implementation dependencies as possible.</p>");
        setSize(350, 275);
        setContentPane(editorPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        System.out.println(editorPane.getText());
    }

    public static void main(String[] a) {
        new JEditorPaneTest();
    }
}
