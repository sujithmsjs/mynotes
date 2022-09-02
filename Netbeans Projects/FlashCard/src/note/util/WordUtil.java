package note.util;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class WordUtil {

    public static void main(String[] args) {
       // Word w = new Word("1Su3ji333", "Sujith", "'Sujith' 'Manchala", "Name");
      ////  boolean isSaved = saveWord(w);
      //  System.out.println("isSaved = " + isSaved);
      //  System.out.println(getWord("1Suj2"));
        System.out.println(WordUtil.getAllTitlesOnDate(Date.valueOf("2021-04-19")));
    }
    
    public static boolean saveWord(Word word) {
        PreparedStatement ps = null;
        boolean isExecuted = false;
        try {
            ps = DBUtil.getPreStmt(Query.INSERT_WORD);
            ps.setString(1, word.getWord());
            ps.setString(2, word.getMean());
            ps.setString(3, word.getNote());
            ps.setString(4, word.getTopic());

            isExecuted = ps.executeUpdate() > 0;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return isExecuted;
    }
    
    
        public static ArrayList<String> getAllTitlesOnDate(java.sql.Date date) {
        PreparedStatement ps = null;
        ArrayList<String> titles = new ArrayList<>();
        try {
            ps = DBUtil.getPreStmt(Query.SEL_ALL_TITLES_ON_DATE);
             ps.setDate(1, date);
            if (ps.execute()) {
                ResultSet rs = ps.getResultSet();
                while (rs.next()) {
                    String title = rs.getString(1);
                    titles.add(title);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return titles;
    }
    
    public static ArrayList<String> getAllTitles() {
        PreparedStatement ps = null;
        ArrayList<String> titles = new ArrayList<>();
        try {
            ps = DBUtil.getPreStmt(Query.SEL_ALL_TITLES);
            if (ps.execute()) {
                ResultSet rs = ps.getResultSet();
                while (rs.next()) {
                    String title = rs.getString(1);
                    titles.add(title);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return titles;
    }
    
    
    
    public static Word getWord(String title) {
        Word w = null;
        PreparedStatement ps = null;
        try {
            ps = DBUtil.getPreStmt(Query.SELECT_WORD);
            ps.setString(1, title);
            if (ps.execute()) {
                ResultSet rs = ps.getResultSet();
                if (rs.next()) {
                    String word = rs.getString(1);
                    String mean = rs.getString(2);
                    String note = rs.getString(3);
                    String topic = rs.getString(4);
                    w = new Word(word, mean, note, topic);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return w;
    }
}
