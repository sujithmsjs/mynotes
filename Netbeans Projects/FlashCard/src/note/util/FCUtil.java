package note.util;

import note.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class FCUtil {
    private static Connection conn;
    
    static{
        try {
            conn = DBUtil.getConnection();
            JOptionPane.showMessageDialog(null,conn.toString(),"Connection Established",JOptionPane.INFORMATION_MESSAGE);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null,conn.toString(),"Error",JOptionPane.ERROR_MESSAGE);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,conn.toString(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void main(String[] args) {
        String str = getDataByAns("Ans");
        System.out.println(str);
    }

    public static void addWord(Vector<String> line) {
        
        
    }

    public static String[] getWordsByDate(String date) {
        //String sqlQuery = "select distinct date_format(entry,'%b %d %Y') from fcards";

        String sqlQuery = "select distinct title from fcards where date_format(entry,'%b %d %Y')='"+date+"' order by title";
        Vector<String> v = new Vector<>();
        try {
        PreparedStatement preStmt = DBUtil.getPreStmt(sqlQuery);
        
        
            if (preStmt.execute()) {
                ResultSet rs = preStmt.getResultSet();
                while (rs.next()) {
                    String value = rs.getString(1);
                    v.add(value);
                    System.out.println(value);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "title", ex.getCause().toString(), JOptionPane.ERROR_MESSAGE);
        }
        return v.toArray(new String[]{});
    }
    
    public static String getDataByAns(String ans) {
        //String sqlQuery = "select distinct date_format(entry,'%b %d %Y') from fcards";

        String sqlQuery = "select title,brief,notes,topic from fcards where title='" + ans + "' order by entry";
        String str = "";
        try {
            PreparedStatement preStmt = DBUtil.getPreStmt(sqlQuery);

            if (preStmt.execute()) {
                ResultSet rs = preStmt.getResultSet();
                if (rs.next()) {
                    String que = rs.getString(1);
                    String ans2 = rs.getString(2);
                    String hint = rs.getString(3);
                    String cmt = rs.getString(4);
                    str= str+que+"\n"
                            +ans2+"\n"
                            + hint+"\n"
                            + cmt;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "title", ex.getCause().toString(), JOptionPane.ERROR_MESSAGE);
        }
        return str;
    }
    
    public static String[] getDatesFromDB() {
        //String sqlQuery = "select distinct date_format(entry,'%b %d %Y') from fcards";
        String sqlQuery = "select distinct date_format(entry,'%b %d %Y') from fcards order by entry desc";
        PreparedStatement preStmt = DBUtil.getPreStmt(sqlQuery);
        Vector<String> v = new Vector<>();
        try {
            if (preStmt.execute()) {
                ResultSet rs = preStmt.getResultSet();
                while (rs.next()) {
                    String value = rs.getString(1);
                    v.add(value);
                      System.out.println(value);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "title", ex.getCause().toString(), JOptionPane.ERROR_MESSAGE);
        }
        return v.toArray(new String[]{});
    }

    public static boolean updateCmt(String word,String notes ) {
        String query = "update fcards set notes='"+notes+"' where title='"+word+"';";
        int value = DBUtil.updateQueryValue(query);
        
        return (value>0)?true:false;
    }
    
    public static boolean insertWordAndNotes(String word, String question,String notes){
        String sqlQuery = "insert into fcards(title,brief,notes) values(?,?,?)";
        boolean isAdded = false;
        try {
            PreparedStatement ps = DBUtil.getPreStmt(sqlQuery);
            ps.setString(1, word);
            ps.setString(2, question);
            ps.setString(3, notes);
            int n = ps.executeUpdate();
            if(n>0)
                isAdded=true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error",ex.getCause().toString(), JOptionPane.ERROR_MESSAGE);
        }
        return isAdded;
    }

    public static String getCmtByWord(String key) {
        String query = "select notes from fcards where title='"+key+"'";
        String cmt = "";
        try {
            ResultSet rs = DBUtil.getResultSet(query);
            rs.next();
            cmt = rs.getString(1);
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Errr", ex.getCause().toString(), JOptionPane.ERROR_MESSAGE);
            cmt = ""; // Error is there.
        }   
        return cmt;     
    }

    public static Vector<String> getReadWords() {
       Vector<String> words  = new Vector<String>();
        String query = "select ans from (SELECT ans,   visited, level,\n" +
"    ROW_NUMBER() OVER(PARTITION BY ans) AS row_num  \n" +
"FROM fcards) as a where row_num=1 order by level, visited";
        ResultSet rs = DBUtil.getResultSet(query);
        try {
            while(rs.next()){
                words.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.getCause().toString() , "Error!", JOptionPane.ERROR_MESSAGE);
        }
        //System.out.println(words);
        
        
        
        
        return words;
    }

    
     
    public void addWord(String que,String ans,String hint,String comments){
        
    }
    
    public static Vector<String> tokenizeText(String text,String dilim){
        Vector<String> vec = new Vector();
        StringTokenizer part = new StringTokenizer(text,dilim);
        while (part.hasMoreTokens()) {
            vec.add(part.nextToken().trim());
        }
        return vec;
    }
  
    public static int processLine(Vector<String> line){
        int n = 0;
        try {
        while(line.size()<4){//0 , 1, 2,3
            line.add("");
        }

        //MySQL Query
        String query = "insert into fcards(que,ans,hint,comm) values(?,?,?,?)";
        
        String word = line.get(0);
        String ans = line.get(1);
        String hint = line.get(2);
        String comments = line.get(3);
        System.out.println(word + " ; " + ans + " ; " + hint + " ; " + comments + " ; ");
    
        PreparedStatement preStmt = DBUtil.getPreStmt(query);
        
        
            preStmt.setString(1, word);
            preStmt.setString(2, ans);
            preStmt.setString(3, hint);
            preStmt.setString(4, comments);
            
           n  = preStmt.executeUpdate();
            
        } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error!", ex.getCause().toString() , JOptionPane.ERROR_MESSAGE);
        }
        
        return n;
    }  
    
    public static void showVector(Vector<String> vec){
        System.out.println("Vector:");
        for (int i = 0; i < vec.size(); i++) {
            System.out.println(i+" : "+vec.get(i));
        }
    }
    
    
    
}
