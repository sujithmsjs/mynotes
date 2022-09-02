package suji.com.mod;

import com.suji.csv.Sheet;
import com.suji.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordDao {

    public static boolean insertWord(String line) throws SQLException {
        boolean isInserted = false;
        Pattern pat = Pattern.compile("[a-zA-Z\\t ]*,[a-zA-Z\\t ]*,[a-zA-Z\\t ]*");
        Matcher mat = pat.matcher(line);
        if (mat.matches()) {

            String[] words = line.split(",");
            isInserted = insertWord(words[0].trim(), words[1].trim(), words[2].trim());

        } else {
            System.out.println("Not Match");
            isInserted = false;
        }
        return isInserted;
    }

    public static boolean insertWord(String word, String syn1, String syn2) throws SQLException {

        boolean isSaved = false;

        String sqlQuery = "insert into words(word, syn1, syn2) values(?,?,?)";
        Connection con = DBUtil.getConn();
        PreparedStatement ps = con.prepareStatement(sqlQuery);
        ps.setString(1, word);
        ps.setString(2, syn1);
        ps.setString(3, syn2);

        isSaved = ps.executeUpdate() > 0;

        return isSaved;

    }

    public static boolean insertWord(Word word) throws SQLException {
        return insertWord(word.getWord(), word.getSyn1(), word.getSyn2());
    }

    public static Word getWord(String word) throws SQLException {

        Word newWord = null;

        String sqlQuery = "select * from words where word = ?";
        Connection con = DBUtil.getConn();
        PreparedStatement ps = con.prepareStatement(sqlQuery);
        ps.setString(1, word);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            newWord = new Word(rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
        }

        return newWord;
    }

    public static boolean updateWord(String line) {
        boolean isUpdated = false;
        Pattern pat = Pattern.compile("[a-zA-Z\\t ]+,[a-zA-Z\\t ]+,[a-zA-Z\\t ]+");
        Matcher mat = pat.matcher(line);
        if (mat.matches()) {

            String[] words = line.split(",");
            for (String word : words) {
                System.out.println(word.trim());
            }
            isUpdated = updateWord(words[0].trim(), words[1].trim(), words[2].trim());

        } else {
            isUpdated = false;
        }
        return isUpdated;
    }

    public static boolean updateWord(String word, String syn1, String syn2) {
        boolean isUpdated = false;
        try {
            String sqlQuery = "update words set syn1=?, syn2=? where word=?";
            Connection con = DBUtil.getConn();
            PreparedStatement ps = con.prepareStatement(sqlQuery);
            ps.setString(3, word);
            ps.setString(1, syn1);
            ps.setString(2, syn2);

            isUpdated = ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return isUpdated;
    }

    public static boolean updateWord(String word, String syn1, String syn2, int exp) {
        boolean isUpdated = false;
        try {
            String sqlQuery = "update words set syn1=?, syn2=?, exp=? where word=?";
            Connection con = DBUtil.getConn();
            PreparedStatement ps = con.prepareStatement(sqlQuery);

            ps.setString(1, syn1);
            ps.setString(2, syn2);
            ps.setInt(3, exp);
            ps.setString(4, word);

            isUpdated = ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return isUpdated;
    }

    public static boolean increseExp(String word) {
        return updateExp(word, 1);
    }

    public static boolean decreseExp(String word) {
        return updateExp(word, -1);
    }

    private static boolean updateExp(String word, int exp) {
        boolean isUpdated = false;
        try {
            String sqlQuery = "update words set exp = exp + ? where word=?";
            Connection con = DBUtil.getConn();
            PreparedStatement ps = con.prepareStatement(sqlQuery);
            ps.setInt(1, exp);
            ps.setString(2, word);

            isUpdated = ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return isUpdated;
    }

    public static boolean updateWord(Word word) {
        return updateWord(word.getWord(), word.getSyn1(), word.getSyn2());
    }

    public static boolean deleteWord(String word) {
        boolean isDeleted = false;
        try {
            String sqlQuery = "delete from words where word=?";
            Connection con = DBUtil.getConn();
            PreparedStatement ps = con.prepareStatement(sqlQuery);
            ps.setString(1, word);
            isDeleted = ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return isDeleted;
    }

    public static int insertWords(Sheet sheet) throws SQLException {
        StringBuilder sb = new StringBuilder();

        int count = 0;
        for (int i = 0; i < sheet.getRows(); i++) {

            String word = sheet.getValue(i, 0);
            String syn1 = sheet.getValue(i, 1);
            String syn2 = sheet.getValue(i, 2);

            if (insertWord(word, syn1, syn2)) {
                count++;
            }
        }
        return count;
    }
}
