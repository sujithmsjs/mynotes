package com.suji.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.MutableAttributeSet.*;

public class NumberHighlight extends DefaultStyledDocument {

    private static final MutableAttributeSet BOLD = new SimpleAttributeSet();

    private static int findLastNonWordChar(String text, int index) {
        while (--index >= 0) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
        }
        return index;
    }

    private static int findFirstNonWordChar(String text, int index) {
        while (index < text.length()) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
            index++;
        }
        return index;
    }
    final StyleContext cont = StyleContext.getDefaultStyleContext();

    final AttributeSet attp = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, new Color(255, 0, 255));
    final AttributeSet attrBlack = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.BLACK);

    public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
        super.insertString(offset, str, a);
        String text = getText(0, getLength());
        int before = findLastNonWordChar(text, offset);
        if (before < 0) {
            before = 0;
        }
        int after = findFirstNonWordChar(text, offset + str.length());
        int wordL = before;
        int wordR = before;
        while (wordR <= after) {
            if (wordR == after || String.valueOf(text.charAt(wordR)).matches("\\W")) {
                if (text.substring(wordL, wordR).matches("(\\W)*(\\d+$)")) {
                    setCharacterAttributes(wordL, wordR - wordL, BOLD, false);
                    setCharacterAttributes(wordL, wordR - wordL, attp, false);
                } else {
                    StyleConstants.setBold(BOLD, false);
                    setCharacterAttributes(wordL, wordR - wordL, BOLD, true);
                    setCharacterAttributes(wordL, wordR - wordL, attrBlack, false);
                }
                wordL = wordR;
            }
            wordR++;
        }
    }

    public void remove(int offs, int len) throws BadLocationException {
        super.remove(offs, len);
        String text = getText(0, getLength());
        int before = findLastNonWordChar(text, offs);
        if (before < 0) {
            before = 0;
        }
        int after = findFirstNonWordChar(text, offs);
        if (text.substring(before, after).matches("(\\W)*(\\d+$)")) {
            setCharacterAttributes(before, after - before, BOLD, false);
            setCharacterAttributes(before, after - before, attp, false);
        } else {
            StyleConstants.setBold(BOLD, false);
            setCharacterAttributes(before, after - before, BOLD, true);
            setCharacterAttributes(before, after - before, attrBlack, false);
        }
    }

}
