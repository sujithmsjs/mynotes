package note.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBUtil {

    private static Connection con;

    static {
        if (con == null) {
            try {
                  Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db7", "root", "apple");
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection c = DBUtil.getConnection();
       // System.out.println(getResultSet("select * from org"));
        System.out.println(c);
    }

    public static ResultSet getResultSet(String query) {
        ResultSet resSet = null;
        try {
            PreparedStatement preStmt = con.prepareStatement(query);
            resSet = preStmt.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resSet;
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        return con;
    }

    public static PreparedStatement getPreStmt(String sqlQuery) {
        PreparedStatement preStmt = null;
        try {
            preStmt = con.prepareStatement(sqlQuery);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return preStmt;
    }

    public static void showMetaData(ResultSet rs) {
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int colCount = rsmd.getColumnCount();
            // System.out.println(rsmd.getTableName(0));
            for (int i = 1; i <= colCount; i++) {
                System.out.println(
                        rsmd.getTableName(i) + "\t"
                        + rsmd.getColumnName(i) + "\t"
                        + rsmd.getColumnType(i) + "\t"
                        + rsmd.getColumnTypeName(i));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void showMetaData(String query) {
        ResultSet rs = getResultSet(query);
        showMetaData(query);
    }

    public static void showRS(ResultSet rs) {
        try {

            ResultSetMetaData rsmd = rs.getMetaData();
            int colCount = rsmd.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= colCount; i++) {
                    String colType = rsmd.getColumnTypeName(i);
                    String res = null;
                    if (colType.equals("INT")) {
                        res = rs.getInt(i) + "";
                    }
                    if (colType.equals("VARCHAR")) {
                        res = rs.getString(i) + "";
                    }
                    if (colType.equals("DATE")) {
                        res = rs.getDate(i) + "";
                    }
                    System.out.print(res + "\t");
                }
                System.out.println();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void showRS(String query) {
        ResultSet rs = getResultSet(query);
        showRS(rs);
    }

    public static int updateQueryValue(String sqlQuery) {
        int result = 0;
        PreparedStatement preStmt = getPreStmt(sqlQuery);
        try {
            result = preStmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
    /*
        getColumnValues menthod provides single oolumn values in Arraylist
    */
    public static ArrayList<String> getColumnValues(String sqlQuery) {
        ArrayList<String> al = new ArrayList();
        try {
            ResultSet rs = getResultSet(sqlQuery);
            ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next()) {
                String colType = rsmd.getColumnTypeName(1);
                String res = null;
                if (colType.equals("INT")) {
                    res = rs.getInt(1) + "";
                    al.add(res);
                }else
                if (colType.equals("VARCHAR")) {
                    res = rs.getString(1) + "";
                    al.add(res);
                }else
                if (colType.equals("DATE")) {
                    res = rs.getDate(1) + "";
                    al.add(res);
                }else
                if (colType.equals("BIGINT")) {
                    res = rs.getInt(1) + "";
                    al.add(res);
                }else{
                    System.out.print("Unknown datatype: "+colType);
                    res = null;
                }
                //System.out.print(res);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return al;
    }
    
    //This method is to provide exception free code.
    public static Connection getConn(){
        Connection con =null;
        try {
            con = DBUtil.getConnection();
            
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return con;
    }

}
