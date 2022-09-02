package com.suji.cram.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBUtil {

    private static Connection con;

    static {
        if (con == null) {
            try {
                //  Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cram", "root", "apple");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static Connection getConn(){
        return con;
    }
    
  
}