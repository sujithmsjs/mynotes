package mypro.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Stack;

public class Token {

    private Stack<String> words = new Stack<String>();
    private Stack<Word> dict = new Stack<Word>();
    private int count = 1;


    private final int TOKENS_STARTS_FROM = 3;
   
    public void startWork(){
        Stack<String> set = ordering2();
        System.out.println(set);
        powerSwapper(set);
        show();
        tokenise(words.size()-1);
       
    }
    public void addNewWord(String word){
        words.add(word);
    }
    
    public Token(Stack<String> stack){
        this.words = stack;
    //    startWork();
    }

    public Stack<Word> getDict(){
        return dict;
    }
    
    
    public void powerSwapper(Stack<String> set) {
        while (!set.empty()) {
            String str = set.pop();
            if (words.remove(str)) {
                System.out.println("Removed " + str);
            }
            words.add(0, str);
        }
    }
    
    public void showDict(){
        for (int i = 0; i < dict.size(); i++) {
             System.out.println(dict.get(i));
        }
    }
    
    public void tokenise(int n){
        int m = 1;
        addFirstWord();
        System.out.println("N values is "+n);
        for (int i = 0; i < words.size()&&n>0; i++) {
            for (int j = 1; j <= getTokensCount(words.get(i))&&n>0; j++) {
                
                //System.out.println(m+" token "+(n)+": "+getToten(words.get(i), j)+": \t\t "+words.get(m));
                Word w = new Word(m,getToten(words.get(i), j),words.get(i),words.get(m) );
                
                dict.add(w);
                n--;
                m++;
            }
            count++;
        }
    }
    
    public void addFirstWord(){
        String tok = getToten(words.get(1), 1);
        String word = words.get(1);
        String sys = words.get(0);
        Word w = new Word(0,tok,word,sys);
        dict.add(w);
    }

    public Stack<Integer> ordering() { // n is numbers
        Scanner s = new Scanner(System.in);
        Stack<Integer> set = new Stack();
        boolean isExpn = false;
        do {
            isExpn = false;
            try {
                System.out.println("Enter order of lines: ");

                String line = s.nextLine();
                StringTokenizer st = new StringTokenizer(line, " ");
                while (st.hasMoreTokens()) {
                    int m = Integer.parseInt(st.nextToken());
                    //System.out.println(m);
                    if (m <= words.size() && m > 0 && !set.contains(m)) {
                        set.push(m);
                    }
                }
                if (line.equals("")) {
                    isExpn = true;
                }
            } catch (Exception ex) {
                isExpn = true;
                System.out.println("Expn found! Try again.");
// ex.printStackTrace();
            }
        } while (isExpn);
        return set;
    }

    public Stack<String> ordering2() { // n is numbers
        Scanner s = new Scanner(System.in);
        Stack<String> set = new Stack();
        boolean isExpn = false;
        do {
            isExpn = false;
            try {
                set.clear();
                show();
                System.out.println("Enter order of lines: ");
                
                String line = s.nextLine();
                StringTokenizer st = new StringTokenizer(line, " ");
                int tokens = 0;
                while (st.hasMoreTokens()) {
                    int m = Integer.parseInt(st.nextToken());
                    //System.out.println(m);
                    if (m <= words.size() && m > 0 && !set.contains(m)) {
                        String word = words.get(m-1);
                        if(set.contains(word))
                            isExpn = true;
                            set.push(word);
                            tokens+=getTokensCount(word);
                            
                                    
                        
                    }
                }
                if(tokens<words.size()-1){
                    isExpn = true;
                    
                }
                System.out.println("Token possible: "+tokens+""
                            + "; Words: "+(words.size()-1))
                            ;
                if (line.equals("")) {
                    isExpn = true;
                }
            } catch (Exception ex) {
                isExpn = true;
                System.out.println("Expn found! Try again.");
// ex.printStackTrace();
            }
        } while (isExpn);
        return set;
    }

    public int getTokensCount(String str) {
        return str.length() - TOKENS_STARTS_FROM + 1;
    }

    public String getToten(String str, int token) {
        int totalTokens = getTokensCount(str);
        String tok = "null";
        if (token > 0 && token <= totalTokens) {
            tok = str.substring(0, TOKENS_STARTS_FROM - 1 + token);
        }
        return tok;
    }

    public void order() {

    }

    public void pushTop(int n) {
        n -= 1;
        String str = words.get(n);
        words.remove(n);
        System.out.println("Words added at front" + str);
        words.add(0, str);
    }

    public void swap(int n, int m) {

        String nStr = words.get(n);
        String mStr = words.get(m - 1);

        words.remove(n);
        words.add(n, mStr);

        words.remove(m);
        words.add(m, nStr);

    }

    public void show() {
        for (int i = 0; i < words.size(); i++) {
            System.out.println((i + 1) + ". " + words.get(i));
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

    }
}
