package com.suji.pro1.note;

import com.suji.str.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SpacesRomover {
    
    
    

    public static void main(String[] args) {

        String str = "        Hey    this isss    sujith       Manchalaa. Hey hey hey     \n\n\nHey   ";

        String str2 = "hey. This is Sujith. how aRe You. This\n\n\n\n\n is Power of Soke.ddsfl.ererlsdfsf.";

        // StringBuilder sb = new StringBuilder("Sujith");
        // sb.insert(1, '*');
        // sb.replace(0, 2, "*");
        // String str2 = makePara(str);
        String para = makePara(str2);
        String cap = capitalize(para);
        System.out.println(cap);
    }

    // First letter of the line is to be Captail Letter.
    public static String capitalize(String str) {

        StringTokenizer st = new StringTokenizer(str, ".");

        StringBuilder sb = new StringBuilder();
        while (st.hasMoreTokens()) {
            String line = st.nextToken().trim().toLowerCase();
            StringBuilder sline = new StringBuilder(line);
            sline.replace(0, 1, Character.toUpperCase(line.charAt(0)) + "");
            sb.append(sline).append(". ");
        }
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }
    
    
    public static String camelCase(String str){
        StringTokenizer st = new StringTokenizer(str, " ");
        StringBuilder sb = new StringBuilder();
        while (st.hasMoreTokens()) {
            String line = st.nextToken().trim().toLowerCase();
            StringBuilder sline = new StringBuilder(line);
            sline.replace(0, 1, Character.toUpperCase(line.charAt(0)) + "");
            sb.append(sline).append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
        
    }
    
    public static String paraToPoints(String str){
        StringTokenizer st = new StringTokenizer(str, ".");
        StringBuilder sb = new StringBuilder();
        int pno = 1;
        while (st.hasMoreTokens()) {
            String line = st.nextToken().trim().toLowerCase();
            StringBuilder sline = new StringBuilder(line);
            sline.replace(0, 1, Character.toUpperCase(line.charAt(0)) + "");
            sb.append(pno++).append(". ").append(sline).append(".\n");
        }
        
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }
    
    public static String addUnderScoor(String str){
        StringBuilder sb = new StringBuilder(str);
        StringBuilder line = new StringBuilder();
        sb.append('\n');
        for (int i = 0; i < sb.length(); i++) {
            line.append("_");
        }
        sb.append(line);
        return sb.toString();
    }

    public static String makePara(String str) {

        //StringTokenizer st = new StringTokenizer(str,".");
        str = removeSequence(str.trim(), ' ');
        str = removeSequence(str, '\n');

        StringBuilder sb = new StringBuilder(str);

        for (int i = 0; i < sb.length(); i++) {

            System.out.println(sb.charAt(i) + " : " + (int) sb.charAt(i));
            if (sb.charAt(i) == '\n') {
                sb.replace(i, i + 1, " ");
            }
            try {
                if (sb.charAt(i - 1) == '.') {
                    if (sb.charAt(i) != ' ') {
                        sb.insert(i, ' ');
                    }
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        return sb.toString();
    }

    public static String makePara2(String str) {

        //StringTokenizer st = new StringTokenizer(str,".");
        str = removeSequence(str.trim(), ' ');
        StringBuilder sb = new StringBuilder(str);

        for (int i = 0; i < sb.length(); i++) {

            System.out.println(sb.charAt(i) + " : " + (int) sb.charAt(i));
            if (sb.charAt(i) == '\n') {
                sb.deleteCharAt(i);
                i--;
            }
        }

        return sb.toString();
    }

    public static String clear(String str) {

        String str2 = removeSequence(str, ' ');
        String str3 = removeSequence(str2, '\n');

        return str3;
    }

    private static String removeSequence(String str, char ch) {
        System.out.println(str.length());

        StringBuilder sb = new StringBuilder(str.trim());

        for (int i = 0; i < sb.length(); i++) {

            //Encounter first space
            if (sb.charAt(i) == ch) {
                // Step ++ and delete if it is space
                System.out.println("First space : " + i);

                for (i++; i < sb.length(); i++) {

                    if (sb.charAt(i) == ch) {

                        System.out.println("Distine to delete : " + i);
                        sb.deleteCharAt(i);
                        i--;
                    } else {

                        break;

                    }
                }
            }
            System.out.println("Text : " + i);
        }
        return sb.toString();
    }

    public static String remove(String str) {

        System.out.println(str.length());

        StringBuilder sb = new StringBuilder(str.trim());

        for (int i = 0; i < sb.length(); i++) {

            //Encounter first space
            if (sb.charAt(i) == 32) {
                // Step ++ and delete if it is space
                System.out.println("First space : " + i);

                for (i++; i < sb.length(); i++) {

                    if (sb.charAt(i) == 32) {

                        System.out.println("Distine to delete : " + i);
                        sb.deleteCharAt(i);
                        i--;
                    } else {

                        break;

                    }
                }
            }
            System.out.println("Text : " + i);
        }
        return sb.toString();
    }
}
