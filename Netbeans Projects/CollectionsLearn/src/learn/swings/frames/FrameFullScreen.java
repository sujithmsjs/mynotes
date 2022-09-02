package learn.swings.frames;

import javax.swing.*;


public class FrameFullScreen {

    public static void main(String[] args) {
        boolean fullScreen = false;
        
        JFrame f = new JFrame("Hellow");
        
        if(fullScreen){
            f.setUndecorated(true);
            f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }else{
            f.setSize(600,400);
            f.setResizable(false);
            f.setLocationRelativeTo(null);
        }      
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

}
