package com.suji.ui;

/*  www  .  jav a2s. com*/
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.Element;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class TextPaneBold {

    public static void main(String[] args) {
        JFrame frame = new TextStyleTestFrame();
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

class TextStyleTestFrame extends JFrame {

    JButton btnStyle = new JButton("Bold");
    JTextPane textPane = new JTextPane();

    public TextStyleTestFrame() {
        textPane.setText("this is a test.");
        add(textPane, BorderLayout.CENTER);
        add(btnStyle, BorderLayout.NORTH);
        btnStyle.addActionListener(e -> changeStyle());
    }

    private void changeStyle() {
        StyledDocument doc = (StyledDocument) textPane.getDocument();
        int selectionEnd = textPane.getSelectionEnd();
        int selectionStart = textPane.getSelectionStart();
        if (selectionStart == selectionEnd) {
            return;
        }
        Element element = doc.getCharacterElement(selectionStart);
        AttributeSet as = element.getAttributes();

        MutableAttributeSet asNew = new SimpleAttributeSet(as.copyAttributes());
        StyleConstants.setBold(asNew, !StyleConstants.isBold(as));
        doc.setCharacterAttributes(selectionStart, textPane.getSelectedText()
                .length(), asNew, true);
        String text = (StyleConstants.isBold(as) ? "Cancel Bold" : "Bold");
        btnStyle.setText(text);
    }
}
