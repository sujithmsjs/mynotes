/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypro.util;

/**
 *
 * @author sujit
 */
public class Word {
    
    private int sno;
    private int wordset;
    private String token;
    private String word;
    private String sys;

    public Word(int sno, String token, String word, String sys) {
        this.sno = sno;
        this.token = token;
        this.word = word;
        this.sys = sys;
        this.wordset = wordset;
    }

    public int getWordset() {
        return wordset;
    }

    public void setWordset(int wordset) {
        this.wordset = wordset;
    }



    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getSys() {
        return sys;
    }

    public void setSys(String sys) {
        this.sys = sys;
    }
    
    

    public static void main(String[] args) {
        // TODO code application logic here
    }

    @Override
    public String toString() {
        return "Word{" + "sno=" + sno  + wordset + ", word=" + word + ", word=" + token + ", sys=" + sys + '}';
    }
    public String toString2(){
        return (sno+1)+","+word+","+token+","+sys;
    }
    
    
}
