package com.suji.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.io.File;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;


public class TestEditorPane01 {

    public static void main(String[] args) {
        new TestEditorPane01();
    }

    public TestEditorPane01() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                JEditorPane editor = new JEditorPane();
                try {
                    editor.setPage(new File("Test.html").toURI().toURL());
                } catch (Exception exp) {
                    exp.printStackTrace();
                }

                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new JScrollPane(editor));
                frame.setSize(400, 400);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                Document document = editor.getDocument();
                try {
                    String find = "Method";
                    for (int index = 0; index + find.length() < document.getLength(); index++) {
                        String match = document.getText(index, find.length());
                        if (find.equals(match)) {
                            javax.swing.text.DefaultHighlighter.DefaultHighlightPainter highlightPainter
                                    = new javax.swing.text.DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
                            editor.getHighlighter().addHighlight(index, index + find.length(),
                                    highlightPainter);
                        }
                    }
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }

            }
        });
    }
}
