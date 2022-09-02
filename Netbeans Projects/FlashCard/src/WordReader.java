
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import note.util.DBUtil;

public class WordReader implements BookReader{    
    private String query;
    private ResultSet rs;
    private int max;

    public ResultSet getRs() {
        return rs;
    }
    
    

    public WordReader(ResultSet rs,int displayRowNum){
        this.rs = rs;
        loadDisplayWord(displayRowNum);
    }
    
    public WordReader(String sqlQuery,int displayRowNum) {
        this.query = sqlQuery;
        loadResultSet();
        loadDisplayWord(displayRowNum);
    }
    
    private Word loadDisplayWord(int n){
        return  getWordAt(n);
    }
    
    private void loadResultSet() {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = ps.executeQuery();
            rs.last();
            max = rs.getRow();
            rs.beforeFirst();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void gotoRow(int n){
        if(n>max){
            
        }
    }
   
    
    
    
    private Word getWord(){
        Word word = null;
        try {
            System.out.println(rs.getRow());
            String title = rs.getString(1);
            String brief = rs.getString(2);
            String notes = rs.getString(3);
            String topic = rs.getString(4);;
            Blob blob = rs.getBlob(5);
            InputStream binaryStream = null;
            if (blob != null) {
                binaryStream = blob.getBinaryStream();
            }
            word = new Word(title, brief, notes, topic, binaryStream);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return word;
    }


    @Override
    public Word getWordAt(int cur) {
        Word w = null;
        if (getCurrentWordIndex() > 0) {
            try {
                if (rs.absolute(cur)) {
                    w = getWord();
                } else {
                    if (cur > max) {
                        rs.last();
                        w = getWord();
                    }
                    if (cur < 1) {
                        rs.first();
                        w = getWord();
                    }

                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        return w;
    }
    
    @Override
    public Word nextWord() {
        Word w = null;
        try {
            boolean next = rs.next();
            if(next){
                w = getWord();
            }else{
               // rs.first();
                rs.last();
                w = w = getWord();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return w;
    }

    @Override
    public Word prevWord() {
        Word w = null;
        try {
            boolean prev = rs.previous();
            if (prev) {
                w = getWord();
            } else {
                // rs.first();
                rs.first();
                w = w = getWord();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return w;
    }


    @Override
    public int getWordsCount() {
        try {
            int current = rs.getRow();
            rs.last();
            int n = rs.getRow();
            max = n;
            rs.absolute(current);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return max;
    }

    @Override
    public int getCurrentWordIndex() {
        int n = 0;
        try {
            n = rs.getRow();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return n;
    }

}
//