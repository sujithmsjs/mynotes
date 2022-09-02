package com.suji.pro1;

import java.awt.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.io.*;

public class NotePad extends JFrame {

    public NotePad() {

        initComponents();
    }

    private void initComponents() {

        buttonGrp = new ButtonGroup();
        scroll = new JScrollPane();
        textArea = new JTextArea();
        Axn axn = new Axn();
        menuBar = new JMenuBar();
        file = new JMenu();
        openFile = new JMenuItem();
        saveFile = new JMenuItem();
        closeFile = new JMenuItem();
        edit = new JMenu();
        textColor = new JMenuItem();
        caretColor = new JMenuItem();
        BGcolor = new JMenuItem();
        selectText = new JMenuItem();
        selectTextBg = new JMenuItem();
        fontStyleBold = new JMenu();
        sizePlus = new JMenuItem();
        sizeMinus = new JMenuItem();
        normal = new JRadioButtonMenuItem();
        bold = new JRadioButtonMenuItem();
        italic = new JRadioButtonMenuItem();
        boldItalic = new JRadioButtonMenuItem();

        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        textArea.setColumns(20);
        textArea.setLineWrap(true);
        textArea.setRows(5);
        textArea.setTabSize(5);
        textArea.setWrapStyleWord(true);
        scroll.setViewportView(textArea);

        getContentPane().add(scroll, BorderLayout.CENTER);

        file.setText("File");
        file.setMnemonic('F');

        openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));

        openFile.setMnemonic('O');
        openFile.setText("Open");
        openFile.setToolTipText("To Open a txt file");
        openFile.addActionListener(axn);

        file.add(openFile);

        saveFile.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, InputEvent.CTRL_MASK));
        saveFile.setText("Save");
        saveFile.addActionListener(axn);

        file.add(saveFile);

        closeFile.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
        closeFile.setText("Close");
        closeFile.addActionListener(axn);
        file.add(closeFile);

        menuBar.add(file);

        edit.setMnemonic('E');
        edit.setText("Edit");

        textColor.setAccelerator(
                KeyStroke.getKeyStroke(
                        KeyEvent.VK_T, InputEvent.CTRL_MASK));
        textColor.setMnemonic('T');
        textColor.setText("set Text Color");
        textColor.addActionListener(axn);
        edit.add(textColor);

        caretColor.setMnemonic('C');
        caretColor.setText("set Carret Color");
        caretColor.addActionListener(axn);
        edit.add(caretColor);

        BGcolor.setAccelerator(
                KeyStroke.getKeyStroke(
                        KeyEvent.VK_B, InputEvent.CTRL_MASK));
        BGcolor.setMnemonic('B');
        BGcolor.setText("set BackGround");
        BGcolor.setToolTipText("");
        BGcolor.addActionListener(axn);
        edit.add(BGcolor);

        selectText.setMnemonic('S');
        selectText.setText("set Select Text Color");
        selectText.addActionListener(axn);
        edit.add(selectText);

        selectTextBg.setMnemonic('e');
        selectTextBg.setText("set Select Text Background Color");
        selectTextBg.addActionListener(axn);
        edit.add(selectTextBg);

        menuBar.add(edit);

        fontStyleBold.setText("Font");

        sizePlus.setAccelerator(
                KeyStroke.getKeyStroke(
                        KeyEvent.VK_PERIOD, InputEvent.CTRL_MASK));
        sizePlus.setText("Size Increase");

        sizePlus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                textArea.setFont(new Font("", textArea.getFont().getStyle(),
                        textArea.getFont().getSize() + 2));
            }
        });
        fontStyleBold.add(sizePlus);

        sizeMinus.setAccelerator(
                KeyStroke.getKeyStroke(
                        KeyEvent.VK_COMMA, InputEvent.CTRL_MASK));

        sizeMinus.setText("Size Decrease");

        sizeMinus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                textArea.setFont(new Font("", textArea.getFont().getStyle(),
                        textArea.getFont().getSize() - 2));
            }
        });

        fontStyleBold.add(sizeMinus);

        buttonGrp.add(normal);

        normal.setSelected(true);
        normal.setText("Normal");
        normal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                textArea.setFont(new Font("", -1, textArea.getFont().getSize()));
            }
        });

        fontStyleBold.add(normal);

        buttonGrp.add(bold);
        bold.setText("Bold");
        bold.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                textArea.setFont(new Font("", -1, textArea.getFont().getSize()));
            }
        });

        fontStyleBold.add(bold);

        buttonGrp.add(italic);
        italic.setText("Italic");
        italic.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                textArea.setFont(new Font("", -1, textArea.getFont().getSize()));
            }
        });

        fontStyleBold.add(italic);

        buttonGrp.add(boldItalic);

        boldItalic.setText("Bold and Italic");
        boldItalic.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                textArea.setFont(new Font("", -1, textArea.getFont().getSize()));
            }
        });
        fontStyleBold.add(boldItalic);

        menuBar.add(fontStyleBold);

        setJMenuBar(menuBar);
    }

    public static void main(String args[]) {
        new NotePad().setVisible(true);

    }

    class Axn implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            switch (e.getActionCommand()) {
                case "Open":
                    System.out.println(e.getActionCommand());
                    openFile();
                    break;
                case "Save":
                    System.out.println(e.getActionCommand());
                    saveFile();
                    break;
                case "Close":
                    System.out.println(e.getActionCommand());
                    closeFile();
                    break;
                case "set BackGround":
                    textArea.setBackground(showColorDialog(1));
                    break;
                case "set Text Color":
                    textArea.setForeground(showColorDialog(2));
                    break;
                case "set Carret Color":
                    textArea.setCaretColor(showColorDialog(3));
                    break;
                case "set Select Text Color":
                    textArea.setSelectionColor(showColorDialog(4));
                    break;
                case "set Select Text Background Color":
                    textArea.setSelectedTextColor(showColorDialog(5));
                    break;

            }
        }
    }

    private void closeFile() {
        int i = JOptionPane.showConfirmDialog(
                this, "Are You Want to Close", "Conform Closing", 0);
        if (i == 0) {
            System.exit(0);
        }
    }

    private Color showColorDialog(int type) {
        Color c = null;
        switch (type) {
            case 1:
                c = JColorChooser.showDialog(this, "Set BackGround", textArea.getBackground());
                break;
            case 2:
                c = JColorChooser.showDialog(this, "Set ForeGround", textArea.getForeground());
                break;
            case 3:
                c = JColorChooser.showDialog(this, "Set Caret Color", textArea.getCaretColor());
                break;
            case 4:
                c = JColorChooser.showDialog(this, "Set Selection Color", textArea.getSelectionColor());
                break;
            case 5:
                c = JColorChooser.showDialog(this, "Set Selected Text Color", textArea.getSelectedTextColor());
                break;
        }
        return c;
    }

    private void openFile() {

        JFileChooser fileChooser = new JFileChooser();
        int i = fileChooser.showOpenDialog(this);
        if (i == JFileChooser.APPROVE_OPTION) {
            textArea.setText("");
            try {
                Scanner scan = new Scanner(
                        new FileReader(fileChooser.getSelectedFile().getPath()));
                while (scan.hasNext()) {
                    textArea.append(scan.nextLine() + "\n");
                }

            } catch (FileNotFoundException ex) {
                System.out.println(ex.getStackTrace());
            }
        }

    }

    private void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        int i = fileChooser.showSaveDialog(this);
        if (i == JFileChooser.APPROVE_OPTION) {
            try {
                PrintWriter out = new PrintWriter(
                        new FileWriter(fileChooser.getSelectedFile().getPath(), true));
                //   out.write(textArea.getText());
                //   out.println(textArea.getText());
                out.write(textArea.getText());
                out.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    // Variables declaration - do not modify                     
    private JMenuItem BGcolor;
    private JRadioButtonMenuItem bold;
    private JRadioButtonMenuItem boldItalic;
    private ButtonGroup buttonGrp;
    private JMenuItem caretColor;
    private JMenuItem closeFile;
    private JMenu edit;
    private JMenu file;
    private JMenu fontStyleBold;
    private JRadioButtonMenuItem italic;
    private JScrollPane scroll;
    private JMenuBar menuBar;
    private JRadioButtonMenuItem normal;
    private JMenuItem openFile;
    private JMenuItem saveFile;
    private JMenuItem selectText;
    private JMenuItem selectTextBg;
    private JMenuItem sizeMinus;
    private JMenuItem sizePlus;
    private JTextArea textArea;
    private JMenuItem textColor;
    // End of variables declaration                   
}
