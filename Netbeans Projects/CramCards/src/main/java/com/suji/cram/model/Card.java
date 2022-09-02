package com.suji.cram.model;

import com.suji.cram.util.DialogUtil;

public class Card {

    private String front;
    private String back;
    private String hint;
    private String group;
    private int score;
    private int sno;
    
    
    public static final String DEFAULT = "Default";

    public Card(String front, String back, String hint) {
        this(-1, front, back, hint, Card.DEFAULT, -4);

    }

    public Card(String front, String back, String hint, int score) {
        this(-1, front, back, hint, Card.DEFAULT, score);
    }

    public Card(String front, String back, String hint, String group) {
        this(-1, front, back, hint, group, -4);
    }

    public Card(int sno, String front, String back, String hint, String group, int score) {
        this.sno = sno;
        this.front = front;
        this.back = back;
        this.hint = hint;
        this.group = group;
        this.score = score;
    }

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public int getScore() {
        return score;
    }

    public int getSno() {
        return sno;
    }

    public boolean isValid(){
        return front.length() > 3 && back.length() > 3 && hint.length() > 3;
    }

    
    
    
    public void setScore(int score) {
        this.score = score;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "Card{sno="+sno+", front=" + front + ", back=" + back + ", hint=" + hint + ", group=" + group + ", score=" + score +'}';
    }

    

}
