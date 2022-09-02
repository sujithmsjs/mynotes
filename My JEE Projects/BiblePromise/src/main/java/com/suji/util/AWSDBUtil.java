package com.suji.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AWSDBUtil {
	private static Connection con;

	static {
		if (con == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://mysqldb.c7iowgwompfy.ap-south-1.rds.amazonaws.com:3306/suji", "admin", "NvrStle#22");
			} catch (SQLException ex) {
				ex.printStackTrace();
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection c = getConnection();
		// System.out.println(getResultSet("select * from org"));
		System.out.println("Check 1");
		System.out.println(c);
	}

	private static Connection getConnection() {
		
		return con;
	}
}
