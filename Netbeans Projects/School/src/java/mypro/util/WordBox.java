package mypro.util;

import java.util.ArrayList;
import java.util.Scanner;

public class WordBox {

    private ArrayList<String> words = new ArrayList<String>();
    private ArrayList<String> words2 = new ArrayList<String>();
    private ArrayList<String> dict = new ArrayList<String>();
    private ArrayList<String> dict2 = new ArrayList<String>();

    WordBox() {
        words = new ArrayList<String>();
        words2 = new ArrayList<String>();
        dict = new ArrayList<String>();
    }

    WordBox(ArrayList<String> words) {
        this.words.addAll(words);
        this.words2.addAll(words);
        dict = new ArrayList<String>();

    }

    public static void main(String[] args) {
        WordBox wb = new WordBox();
        wb.install();
        wb.work();
    }

    public void work() {
        // install();
        Scanner scan = new Scanner(System.in);
        boolean isEx = false;
        do {

            do {
                try {
                    show(words);
                    System.out.println("Choose one from above");
                    isEx = false;
                    
                    if (scan.hasNextInt()) {
                       int n = scan.nextInt();
                        tokenize2(n);
                    }
                    
                    
                } catch (Exception ex) {
                    System.err.println(ex);
                    isEx = true;
                }
            } while (isEx);

            System.out.println("Remained:" + words2);
            isClear();
        } while (!isClear());

        System.out.println("Printing all data");
        for (int i = 0; i < dict.size(); i++) {
            System.out.println(dict.get(i));
        }

    }

    public void work2() {
        // install();
        Scanner scan = new Scanner(System.in);
        boolean isEx = false;
        do {

            do {
                try {
                    show(words);
                    System.out.println("Choose one from above");
                    isEx = false;
                    int n = scan.nextInt();
                    tokenize2(n);
                } catch (Exception ex) {
                    System.err.println(ex);
                    isEx = true;
                }
            } while (isEx);

            System.out.println("Remained:" + words2);
            isClear();
        } while (!isClear());

        System.out.println("Printing all data");
        for (int i = 0; i < dict.size(); i++) {
            System.out.println(dict.get(i));
        }

    }
    
    public void install() {
        words.add("elephant");
        words.add("ant");
        words.add("hippopotamus");
        words.add("tiger");
        words.add("eagle");
        words.add("lion");
        //   words.add("Gazzle");
        words.add("deer");
        words2.addAll(words);
        dict = new ArrayList<String>();
    }

    public ArrayList<String> getDict() {
        return dict;
    }

    public void show(ArrayList<String> al) {
        for (int i = 0; i < al.size(); i++) {
            System.out.println(i + ") " + al.get(i));
        }
    }

    public int choose() {
        Scanner s = new Scanner(System.in);
        System.out.println("Select: " + words);
        int sel = s.nextInt();
        return sel;
    }

    public void desc(int n) {
        System.out.println("\nDESC: ");
        String selWord = words.get(n);
        int selWordLen = selWord.length();
        System.out.println("Selected word: " + selWord + " | Index: " + n + " | Len:" + selWordLen);
        System.out.println("Words : " + words + " | Len: " + words.size());
        System.out.println(" : " + words + " | Len: " + words.size());
    }

    public void tokenize(int n) {
        int start = 3;
        String word = words.get(n);
        for (int i = start; i <= word.length(); i++) {
            System.out.println((i - start + 1) + ". " + word.substring(0, i));
        }
    }

    public boolean isClear() {
        //System.out.println(words2+": "+words2.size());
        return (words2.size() == 0);
    }

    public void tokenize2(int n) {
        int start = 3;

        String word = words.get(n);
        System.out.println("Coosed: " + word);

        words.remove(n);
        // words2.remove(word);
        for (int i = start, p = 0; i <= word.length() && p < words2.size(); i++, p++) {
            System.out.println((i - start + 1) + ". " + word.substring(0, i) + " : " + words2.get(p));
            try {
                dict.add(word.substring(0, i) + "-" + word.substring(0, i + 1) + "-" + word);
                dict2.add(words2.get(p));
            } catch (Exception e) {
                dict.add(word.substring(0, i) + "-" + word.substring(0, i) + "-" + word + "-" + words2.get(p));
                dict2.add(words2.get(p));
            }
            words2.remove(p);
            p--;
        }
    }

}
