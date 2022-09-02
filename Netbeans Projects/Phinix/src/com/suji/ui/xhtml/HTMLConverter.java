package com.suji.ui.xhtml;

import java.util.ArrayList;

public class HTMLConverter {

    private static String addXhtmlheaders(String printText) {
        StringBuilder sb = new StringBuilder(printText);
        sb.insert(0, "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"
                + "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\"\n"
                + "  \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">\n"
                + "\n"
                + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                + "<head>\n"
                + "  <title></title>\n"
                + "</head>\n"
                + "\n"
                + "<body>");
        sb.append("</body>\n"
                + "</html>");
        return sb.toString();
    }

    public static String fromTextToHtml(String input) {
        StringBuffer sb = new StringBuffer();

        String lines[] = input.split("\n");

        // All lines of code stored in the file.
        ArrayList<String> arr = new ArrayList<>();

        for (String line : lines) {

            if (line.trim().length() > 2) {
                arr.add(line.trim());
            }
        }

        for (int i = 0; i < arr.size(); i++) {

            // System.out.println(arr.get(i));
            String line = arr.get(i);

            int ch = line.charAt(0);

            // Starting with a [a-zA-Z] are paragraphs.
            if (Character.isAlphabetic(ch)) {
                sb.append("<p>").append(line).append("<p/>\n\n");
            } else if (Character.isDigit(ch)) {
                sb.append("<h3>").append(line).append("<h3/>\n\n");
            } else {
                switch (ch) {
                    case '!':
                        sb.append("<h1 style='text-align: center'>").append(line.substring(1).trim()).append("<h1/>\n\n");
                        break;
                    case '@':
                        sb.append("<h2>").append(line.substring(1).trim()).append("<h2/>\n\n");
                        break;
                    case '#':
                    case '_':
                        sb.append("<h3>").append(line.substring(1).trim()).append("<h3/>\n\n");
                        break;
                    case '$':
                        sb.append("<h4>").append(line.substring(1).trim()).append("<h4/>\n\n");
                        break;
                    case '%':
                        sb.append("<h5>").append(line.substring(1).trim()).append("<h5/>\n\n");
                        break;
                    case '^':
                        sb.append("<h6>").append(line.substring(1).trim()).append("<h6/>\n\n");
                        break;
                    case '*':
                        sb.append("<ul>\n");
                        int j = i;
                        for (; j < arr.size() && arr.get(j).charAt(0) == '*'; j++) {

                            String cache = arr.get(j).substring(1).trim();

                            sb.append("<li>").append(cache).append("<li/>\n");
                        }

                        sb.append("</ul>\n\n");
                        i = --j;
                        break;

                    case '>':
                        sb.append("<ol>\n");
                        int k = i;
                        for (; k < arr.size() && arr.get(k).charAt(0) == '>'; k++) {

                            String cache = arr.get(k).substring(1).trim();

                            sb.append("<li>").append(cache).append("<li/>\n");
                        }

                        sb.append("</ol>\n\n");
                        i = --k;

                        break;
                    default:
                        sb.append("<p>").append(line.substring(1).trim()).append("<p/>\n\n");
                }
            }
        }

        return addXhtmlheaders(sb.toString());
    }
}
