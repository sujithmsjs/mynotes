package util.swing;

import learn.swings.frames.*;
import javax.swing.*;

public class ReusableFrame extends JFrame {

    private JPanel board;

    public ReusableFrame(JPanel board, String title, boolean fullScreen) {
        this.board = board;
        add(board);

        if (fullScreen) {
            this.setUndecorated(true);
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        } else {
            //this.setSize(600, 400);
            this.setResizable(true);
            pack();
            this.setLocationRelativeTo(null);
        }
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
