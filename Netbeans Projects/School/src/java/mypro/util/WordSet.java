package mypro.util;

import java.util.Vector;

public class WordSet {

    Vector<Word> words;

    public WordSet(Vector<Word> words) {
        this.words = words;
    }

    public WordSet() {
        words = new Vector<Word>();
    }

    @Override
    public String toString() {
        return "WordSet{" + "words=" + words + '}';
    }
    
}
