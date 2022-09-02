package mypro.util;

import mypro.dbutil.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;

public class Dict3 {

    public static void main(String[] args) {
        try {

            Connection conn = DBUtil.getConnection();

            String insertQuery = "insert into dict.dictionary(scut,word) value(?,?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertQuery);

            String retriveQuery = "select scut,word from dict.dict";
            PreparedStatement preStmt = conn.prepareStatement(retriveQuery);

            if (preStmt.execute()) {
                ResultSet resSet = preStmt.executeQuery();
                while (resSet.next()) {
                    String shortCut = resSet.getString(1);
                    String word = resSet.getString(2);

                    StringTokenizer st = new StringTokenizer(word, ",");

                    while (st.hasMoreTokens()) {
                        String token = st.nextToken().trim();

                        insertStmt.setString(1, shortCut);
                        insertStmt.setString(2, token);

                        if (insertStmt.executeUpdate() > 0) {
                            System.out.println(shortCut + " " + token + " Added");
                        } else {
                            System.out.println(shortCut + " " + token + " Not added");
                        }
                    }
                    // System.out.println(word);
                }
            } else {
                System.out.println("Statement not executed.");
            }
            System.out.println("Execution completed.");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
