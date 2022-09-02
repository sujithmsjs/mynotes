
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * @see https://stackoverflow.com/questions/5812002
 */
public class RevalidateTest {

    private static JPanel panel = new JPanel(); // default FlowLayout
    private static JTextField text = new JTextField("Text field");
    private static JButton clear = new JButton(new AbstractAction("Clear") {

        @Override
        public void actionPerformed(ActionEvent e) {
            panel.removeAll();
            panel.add(reset);
            panel.revalidate();
            panel.repaint();
        }
    });
    private static JButton reset = new JButton(new AbstractAction("Reset") {

        @Override
        public void actionPerformed(ActionEvent e) {
            panel.removeAll();
            panel.add(text);
            panel.add(clear);
            panel.revalidate();
            panel.repaint();
        }
    });

    static void createAndShowGUI() {
        JFrame frame = new JFrame("Test");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panel.add(text);
        panel.add(clear);
        frame.add(panel); // default BorderLayout center
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
