package com.suji.ui;

import java.util.ArrayList;
import java.util.List;
import suji.com.mod.Word;
import suji.com.mod.WordSet;

public class WordBox {

    private List<Word> list;
    private int index = -1;
    private int size;

    public WordBox(List<Word> list) {
        this.list = list;
        size = list.size();
    }

    public int getIndex() {
        return index;
    }

    public int getSize() {
        return size;
    }
    
    public boolean isValidIndex(){
        return (index > -1 && index < size);
    }

    public static void main(Word[] args) {

        Box b = Box.getABox();

        while (b.hasPrevious()) {
            System.out.println(b.previous());
        }

        while (b.hasNext()) {
            System.out.println(b.next());
        }
        System.out.println("Index:" + b.getIndex());

    }

    public boolean hasNext() {
        return index < size - 1;
    }

    public Word get() {
        return list.get(index);
    }

    public boolean hasPrevious() {
        return index > 0;
    }

    public Word next() {
        if (hasNext()) {
            index++;
        }
        return list.get(index);
    }

    public Word previous() {
        if (hasPrevious()) {
            index--;
        }
        return list.get(index);
    }

    public void set(Word e) {
        list.set(index, e);
    }

    public int size() {
        return list.size();
    }

    public String getResults() {
        StringBuffer sb = new StringBuffer();
        
        int total = 0,wrng = 0, ryt = 0;
        List<Word> wrngs = new ArrayList<>();
        
        for (Word word : list) {
            total++;
            if(word.isRight()){
                ryt++;
            }else{
                wrng++;
                wrngs.add(word);
            }
        }
        
        sb.append("Total    : ").append(total).append("\n");
        sb.append("Rights   : ").append(ryt).append("\n");
        sb.append("Wrongs   : ").append(wrng).append("\n");
        sb.append("\nPlease check").append("\n");
        
        for (Word word : wrngs) {
           sb.append(word.getWord()).append(", ").append(word.getSyn1()).append(", ").append(word.getSyn2()).append("\n");
        }
        
        return sb.toString();
    }
}
