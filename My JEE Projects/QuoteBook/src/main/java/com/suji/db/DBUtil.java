
package com.suji.db;


import java.sql.*;

public class DBUtil {

	private static Connection con;

	static {
		if (con == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/qbook", "root", "apple");
			} catch (SQLException ex) {
				ex.printStackTrace();
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static Connection getConnection(){
		return con;
	}
	
	// This method is to provide exception free code.
		public static Connection getAWSConnection() {
			Connection con = null;
			if (con == null) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					String host= "mysqldb.c7iowgwompfy.ap-south-1.rds.amazonaws.com";
					
					con = DriverManager.getConnection("jdbc:mysql://"+host+":3306/suji", "admin", "NvrStle#22");
				} catch (SQLException ex) {
					ex.printStackTrace();
				} catch (ClassNotFoundException ex) {
					ex.printStackTrace();
				}
			}
			return con;
		}

}
