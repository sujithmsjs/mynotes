

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import java.util.*;

public class SpaceShip extends Sprite {

    private int velocity = 2;
    private int gravity = 1;
    private long freeFallStartedAt;
    private boolean isControlling;

    public SpaceShip() {
        initShip();
    }

    public void initShip() {

        // String filePath = "C:\\Users\\sujit\\OneDrive\\Desktop\\Java created\\myPaint.png";
        String filePath = "C:\\Users\\sujit\\OneDrive\\Desktop\\images\\ship.png";
        Image image = null;
        if (new File(filePath).exists()) {
            image = new ImageIcon(filePath).getImage();
        } else {
            image = null;
        }

        setColor(Color.red);
        setImage(image);
        setLocation(200, 0);
        freeFallStartedAt = System.nanoTime();
    }

    public void blasted() {
        Image image = new ImageIcon("C:\\Users\\sujit\\OneDrive\\Desktop\\images\\fire.png").getImage();
        setImage(image);
    }

    public void move() {
        if (isControlling) {
            setVelocity();
        } else {
            setGravity();
        }
    }

    public void setGravity() {
        long now = System.nanoTime();
        long freeFall = now - freeFallStartedAt;
        long bn = 1_000_000_00;
        int secs = (int) (freeFall / bn);
        System.out.println("Sec " + secs);
        y += gravity * secs;
    }

    public void setVelocity() {
        y -= velocity;
    }

    void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            isControlling = false;
            freeFallStartedAt = System.nanoTime();
        }
    }

    void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            isControlling = true;
        }
    }

}
