package mypro.util;

import java.util.ArrayList;
import java.util.Scanner;
import mypro.dbutil.DBUtil;

public class Dict5 {

    public static void main(String[] args) {
        ArrayList<String> al = new ArrayList<String>();
        al.add("elephant");
        al.add("ant");
        al.add("hippopotamus");
        al.add("tiger");
        al.add("eagle");
        al.add("lion");
        demo2(al);
    }

    public static void main() {
        try {
            String getUnique = "select distinct scut from org";
            String rowCount = "select count(distinct scut) as c from org";

            ArrayList<String> uVals = DBUtil.getColumnValues(getUnique);

            ArrayList<String> rows = DBUtil.getColumnValues(rowCount);
            int n = Integer.parseInt(rows.get(0));

            ArrayList<String> al = new ArrayList();
            Scanner s = new Scanner(System.in);
            for (int i = 0; i < 5; i++) {
                al.add(uVals.get(i));
                String get2 = "select word from org where scut='" + uVals.get(i) + "'";
                ArrayList<String> words = DBUtil.getColumnValues(get2);
                al.addAll(words);
                System.out.println(al + ": " + al.size());
                al.clear();
            }

            //System.out.println(uVals);
            System.out.println(rows);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void demo(ArrayList<String> al) {
        ArrayList<String> all = new ArrayList<String>();
        all.addAll(al);
        Scanner s = new Scanner(System.in);
        int tok = 0, wor = 0;
        do {
            System.out.println("which one do you know well?");
            System.out.println(al);
            // System.out.println(all);
            int p = s.nextInt();
            wor = al.size() - 1;
            System.out.println("Total words: " + wor);
            int tokenStartWith = 4;
            if (al.get(p).length() < 5) {
                tokenStartWith = 3;
            }
            
            tok = al.get(p).length() - tokenStartWith + 1;
            System.out.println("Tokens available: " + tok);

            String str = al.get(p);
            int size = str.length();
            al.remove(p);
            all.remove(p);
            int j = 0;
            for (int i = tokenStartWith; i <= str.length(); i++) {
                System.out.println((i - tokenStartWith + 1) + " )" + str.substring(0, i) + ":\t" + al.get(j));
                j++;
                al.remove(j);
            }
        } while (al.size()>0);
    }
    public static void demo2(ArrayList<String> al){
        Scanner s = new Scanner(System.in);
        
        ArrayList<String> clear = new ArrayList<String>();
        ArrayList<String> al2 = new ArrayList<String>();
        al2.addAll(al);
        int totWords =0;
        do{
            System.out.println("Select: "+al2);
            int selIdx = s.nextInt();
            String selWord = al2.get(selIdx);            
            int selWordLen = selWord.length();
            totWords = al.size();
            
            int tknStartsFrom =0;
            if(selWordLen<5)
                tknStartsFrom=3;
            else
                tknStartsFrom=4;
            
            System.out.println("Selected word: "+selWord);
            System.out.println("Selected word len: "+selWordLen);
            System.out.println("Total words to be tokenizations: "+(totWords-1));
            
            clear.add(al.get(selIdx));
            al.remove(selIdx);
            al2.remove(selIdx);
            /*
            for (int i = tknStartsFrom,j=0; i <= selWordLen && j<al.size(); i++) {
                System.out.println((i - tknStartsFrom + 1) + ". " + selWord.substring(0, i) + ":\t" + al.get(j));
                al.remove(j);                
                clear.add(al.get(j));
                j=0;
// j--;
            }
*/
            int start = 3;
            for(int i=start;i<selWordLen;i++){
                String token = selWord.substring(0,start+i);
            }
            
            
            
            System.out.println("CLR : "+clear);
            System.out.println("AL "+al);
            System.out.println("ALL: "+al2);
            
            if(totWords==clear.size())
                System.out.println("Successfully complted.");
            else
                System.out.println("Not complted.");
            
        }while(totWords!=clear.size());
    }
}
























