package suji.com.mod;

import com.suji.util.HintGenerator;

/*
A WROD CONTAINS 2 SIMILARM MEANINS.
*/

public class Word {

    private String word;
    private String syn1;
    private String syn2;
    private String hint;
    private String ans;
    private String input;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
    private int exp;

    public boolean isRight(){
        boolean isRight = ans.equalsIgnoreCase(input);
        if(isRight){
            WordDao.increseExp(word);
        }else{
            WordDao.decreseExp(word);
        }
        
        return isRight;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = HintGenerator.makeHint(hint);
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }
    
    
    
    public Word(String word, String syn1, String syn2) {
        this.word = word;
        this.syn1 = syn1;
        this.syn2 = syn2;
    }

    public Word(String word, String syn1, String syn2, int exp) {
        this.word = word;
        this.syn1 = syn1;
        this.syn2 = syn2;
        this.exp = exp;
    }

    
    

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getSyn1() {
        return syn1;
    }

    public void setSyn1(String syn1) {
        this.syn1 = syn1;
    }

    public String getSyn2() {
        return syn2;
    }

    public void setSyn2(String syn2) {
        this.syn2 = syn2;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "Word{" + "word=" + word + ", syn1=" + syn1 + ", syn2=" + syn2 + ", exp=" + exp + '}';
    }
    
    
    
}
