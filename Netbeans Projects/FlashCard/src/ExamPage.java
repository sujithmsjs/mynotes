
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

public class ExamPage {

    private int cursor = -1;
    private int maximal;
    private ArrayList<String> titles;
    private ArrayList<Boolean> ans;
    private ArrayList<String> ques;

    public ExamPage(ArrayList<String> titles) {
        this.titles = titles;
        ans = new ArrayList<Boolean>();
        maximal = titles.size();
    }

    @Override
    public String toString() {
        return "ExamPage{" + "cursor=" + cursor + ", maximal=" + maximal + ", titles=" + titles + ", ans=" + ans + '}';
    }

    
    
    public boolean hasNext() {
        boolean hasNext = false;
        if (cursor < maximal - 1) {
            hasNext = true;
            cursor++;
        }

        return hasNext;
    }

    public int getMaximal() {
        return maximal;
    }

    public String next() {
        return titles.get(cursor);
    }

    public void saidAns(String queation,boolean isRight) {
        ques.add(queation);
        ans.add(cursor, isRight);
    }
    
    public String getResults(){
        String results = "";
        int rights = Collections.frequency(ans, true);
        int wrongs = Collections.frequency(ans, false);
        int percent= (rights*100)/maximal;
        
        for (int i = 0; i < maximal; i++) {
            results = results+"Que: "+ques.get(i)+"\n"+"Ans "+titles.get(i)+"\t"+ans.get(i);
            results = results+"\n\n";
        }
        results = results+"Total questions: "+maximal+"\n"
                + "Wrongly answers: "+wrongs+"\n"
                + "Right answers:"+rights+"\n"
                + "Percent"+percent;
        return results;
    }
}
