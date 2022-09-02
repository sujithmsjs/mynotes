package com.suji.ui.xhtml;


import com.suji.ui.xhtml.RegExConst;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextTools {

    

    public static void main(String[] args) {

        String str = TextFileUtil.getFileText("C:\\Users\\sujit\\OneDrive\\Desktop\\Articles\\demo.txt");
        String htmlCode = HTMLConverter.fromTextToHtml(str);
      //  String xHtml = HTMLConverter.addXhtmlheaders(htmlCode);
        
        
    }

    public static void main(String[][] args) {

        String str = TextFileUtil.getFileText("C:\\Users\\sujit\\OneDrive\\Desktop\\Articles\\demo.txt");
        StringBuffer sb = new StringBuffer();

        String lines[] = str.split("\n");

        for (int i = 0; i < lines.length; i++) {

            String line = lines[i].trim();

            if (lines[i].trim().length() > 0) {

                int ch = line.charAt(0);

                if (Character.isAlphabetic(ch)) {
                    sb.append("<p>").append(line).append("<p/>\n\n");
                } else if (Character.isDigit(ch)) {
                    sb.append("<h3>").append(line).append("<h3/>\n\n");
                } else if (ch == '$') {
                    sb.append("<h1>").append(line.substring(1).trim()).append("<h1/>\n\n");
                } else if (ch == '#') {
                    sb.append("<h3>").append(line.substring(1)).append("<h3/>\n\n");
                } else if (ch == '*') {
                    sb.append("<h3>").append(line.substring(1)).append("<h3/>\n\n");
                } else {
                    System.out.println("Nothing: " + line);
                }

            }
        }

        System.out.println(sb);

    }

    public static void main(String args) {

        String str = TextFileUtil.getFileText();
        String code = textToHtml(str);
        String pages[] = code.split(RegExConst.PAGE_BREAK);

        String dir = "C:\\Users\\sujit\\OneDrive\\Desktop\\Articles\\My First";

        StringBuilder context = new StringBuilder();
        context.append("<h1>Index</h1>\n");
        context.append("<ol>\n\n");

        for (int i = 0; i < pages.length; i++) {

            String text = pages[i];

            System.out.println("page " + i);

            String fileName = "";

            if (text.contains("<h1>")) {
                int s = text.indexOf("<h1>") + 4;
                int e = text.indexOf("</h1>");

                String title = text.substring(s, e).replaceAll("[^a-zA-Z ]+", "").trim();
                fileName = title + ".xhtml";

                String index = "<li><a href=\"" + title + "\">" + fileName + "</a></li>\n\n";

                context.append(index);

            } else if (text.contains("<h3>")) {

                int s = text.indexOf("<h3>") + 4;
                int e = text.indexOf("</h3>");

                String title = text.substring(s, e).replaceAll("[^a-zA-Z ]+", "").trim();
                fileName = title + ".xhtml";

                String index = "<li><a href=\"" + title + "\">" + fileName + "</a></li>\n\n";
                context.append(index);

            } else if (text.contains("<h4>")) {

                int s = text.indexOf("<h4>") + 4;
                int e = text.indexOf("</h4>");

                String title = text.substring(s, e).replaceAll("[^a-zA-Z ]+", "").trim();
                fileName = title + ".xhtml";

                String index = "<li><a href=\"" + title + "\">" + fileName + "</a></li>\n\n";
                context.append(index);
            }

            TextFileUtil.saveXhtmlFile(dir, fileName, text);
        }

        context.append("</ol>");
        TextFileUtil.saveXhtmlFile(dir, "Context.xhtml", context);
        System.out.println(context);
    }

    public static String textToHtml(String input) {

        String sb1 = replace(input, RegExConst.PARA, "<p>", "", "</p>");
        String sb2 = replace(sb1, RegExConst.HEAD_3, "<h3>", "[#]", "</h3>");
        String sb3 = replace(sb2, RegExConst.HEAD_4, "<h4>", "[@]", "</h4>");
        String sb4 = replace(sb3, RegExConst.HEAD_1, "<h1>", "[$]", "</h1>");
        String sb5 = orderedList(sb4, RegExConst.NUM_LIST);
        String sb6 = unorderedList(sb5, RegExConst.LIST);

        return sb6;
    }

    public static String replace(String input, String regEx, String sTag, String deleteRegEx, String eTag) {

        StringBuilder sb = new StringBuilder(input);
        StringBuilder editable = new StringBuilder(input);

        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(sb);
        int len = 0;

        while (m.find()) {

            int s = m.start();
            int e = m.end();
            String group = m.group();
            int gl = group.length();
            String edit = "\n" + sTag + group.trim().replaceAll(deleteRegEx, "") + eTag + "\n";
            editable.replace(s + len, e + len, edit);
            len = (len + edit.length() - gl);

        }
        System.out.println(editable);
        return editable.toString();
    }

    public static void replaceOrg(String input, String regEx, String sTag, String eTag) {

        StringBuilder sb = new StringBuilder(input);
        StringBuilder editable = new StringBuilder(input);
        //Every Digit replace with ** double start

        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(sb);
        int len = 0;

        while (m.find()) {

            int s = m.start();
            int e = m.end();
            String group = m.group();
            int gl = group.length();
            String edit = sTag + group.trim() + eTag;
            editable.replace(s + len, e + len, edit);
            len = (len + edit.length() - gl);

        }
        System.out.println(editable);

    }

    private static String orderedList(String input, String regEx) {

        StringBuilder sb = new StringBuilder(input);
        StringBuilder editable = new StringBuilder(input);

        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(sb);

        int len = 0;
        int count = 0;

        while (m.find()) {
            count++;
            int s = m.start();
            int e = m.end();
            String group = m.group();
            int gl = group.length();

            String items[] = group.split("\n");

            //Modifining String.
            StringBuilder cache = new StringBuilder();
            cache.append("\n<ol>\n");
            for (int i = 0; i < items.length; i++) {
                cache.append("<li>").append(items[i].replaceAll("[-]", "")).append("</li>\n");
            }
            cache.append("</ol>\n");

            //System.out.println(count+": "+group);
            // String edit = "\n" + sTag + group.trim().replaceAll(deleteRegEx, "") + eTag + "\n";
            editable.replace(s + len, e + len, cache.toString());

            len = (len + cache.length() - gl);

        }
        System.out.println(editable);
        return editable.toString();

    }

    private static String unorderedList(String input, String regEx) {

        StringBuilder sb = new StringBuilder(input);
        StringBuilder editable = new StringBuilder(input);

        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(sb);

        int len = 0;
        int count = 0;

        while (m.find()) {
            count++;
            int s = m.start();
            int e = m.end();
            String group = m.group();
            int gl = group.length();

            String items[] = group.split("\n");

            //Modifining String.
            StringBuilder cache = new StringBuilder();
            cache.append("\n<ul>\n");
            for (int i = 0; i < items.length; i++) {
                cache.append("<li>").append(items[i].replaceAll("[*]", "")).append("</li>\n");
            }
            cache.append("</ul>\n");

            //System.out.println(count+": "+group);
            // String edit = "\n" + sTag + group.trim().replaceAll(deleteRegEx, "") + eTag + "\n";
            editable.replace(s + len, e + len, cache.toString());

            len = (len + cache.length() - gl);

        }
        System.out.println(editable);
        return editable.toString();

    }
}
