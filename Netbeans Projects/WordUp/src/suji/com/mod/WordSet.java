package suji.com.mod;

import com.suji.csv.CSVFile;
import com.suji.ui.WordBox;
import com.suji.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WordSet {

   
    
    public static WordBox getWordBox(){
        
        List<Word> list = getPageWords(20,0);
        List<Word> ques = getQue(list);
        Collections.shuffle(ques);
        
        return new WordBox(ques);
    }
    
    private static List<Word> getQue(List<Word> list) {
        Random ran = new Random();
       
        for (Word word : list) {
            String hint = "";

            if (!word.getSyn1().equalsIgnoreCase(CSVFile.emptyStr) && !word.getSyn2().equalsIgnoreCase(CSVFile.emptyStr)) {

                int n = ran.nextInt(2);

                switch (n) {

                    case 0:
                        hint = word.getSyn1();
                        word.setHint(word.getSyn1());
                        word.setAns(hint);
                        break;

                    case 1:
                        hint = word.getSyn2();
                        word.setHint(word.getSyn2());
                        word.setAns(hint);
                        break;
                }

            } else if (!word.getSyn1().equalsIgnoreCase(CSVFile.emptyStr)) {
                // Syn1
                hint = word.getSyn1();
                word.setHint(word.getSyn1());
                word.setAns(hint);

            } else {
                // Syn2
                hint = word.getSyn2();
                word.setHint(word.getSyn2());
                word.setAns(hint);
            }

            //System.out.println(word);
        }
        return list;

    }

    public static String addAllWords(String list) {
        String lines[] = list.split("\n");
        StringBuilder sb = new StringBuilder();
        int count = 0;
        sb.append("Errors: \n");
        for (String line : lines) {
            try {
                if (WordDao.insertWord(line)) {
                    count++;
                }else{
                  sb.append(line).append("\n");
                }
            } catch (SQLException ex) {
                sb.append(line).append("\n");
            }
        }
        sb.insert(0, "Added : "+count+"\n");
        return sb.toString();
    }

    public static List<Word> geBrokentWords() {

        String sqlQuery = "select * from words where word='empt' or syn1='empt' or syn2='empt'";
        List<Word> list = new ArrayList();

        try {

            Connection con = DBUtil.getConn();
            PreparedStatement ps = con.prepareStatement(sqlQuery);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Word word = new Word(rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
                list.add(word);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;

    }

    public static List<Word> getPageWords(int limit, int offset) {

        String sqlQuery = "select * from words where word not like 'empt' and (syn1 not like 'empt' or syn2 not like 'empt') order by exp limit ? offset ?";
        List<Word> list = new ArrayList<>();

        try {

            Connection con = DBUtil.getConn();
            PreparedStatement ps = con.prepareStatement(sqlQuery);
            ps.setInt(1, limit);
            ps.setInt(2, offset);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                
                Word word = new Word(rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
                list.add(word);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;

    }

    public static int getTotalCount() {
        
        String sqlQuery = "SELECT count(*) FROM words";
        
        List<Word> list = new ArrayList<>();
        
        int count = 0;
        try {

            Connection con = DBUtil.getConn();
            PreparedStatement ps = con.prepareStatement(sqlQuery);

            ResultSet rs = ps.executeQuery();

            rs.next();
            
            count = rs.getInt(1);
            

        } catch (SQLException ex) {
            count = 50;
        }
        return count;
    }
}
