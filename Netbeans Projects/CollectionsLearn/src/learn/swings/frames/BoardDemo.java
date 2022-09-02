package learn.swings.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import javax.swing.JButton;
import util.swing.MyCanvas;
import util.swing.ReusableFrame;
import util.swing.SizeUtil;

public class BoardDemo extends MyCanvas {

    private static Dimension SCREEN = SizeUtil.KINDLE;
    private JButton b;

    public BoardDemo() {
        // setBackground(Color.BLUE);
        setPreferredSize(SCREEN);
        setLayout(null);
        b = new JButton("Click me");
        b.setBounds(SCREEN.width / 2 - 50, SCREEN.height / 2 - 50, 100, 100);

    }

    @Override
    protected void printComponent(Graphics g) {
        super.printComponent(g);
        b.setBounds(getWidth() / 2 - 50, getHeight() / 2 - 50, 100, 100);
        add(b);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                BoardDemo b = new BoardDemo();
                ReusableFrame f = new ReusableFrame(b, "Just demo", false);
                f.setVisible(true);
            }
        });

    }

}
