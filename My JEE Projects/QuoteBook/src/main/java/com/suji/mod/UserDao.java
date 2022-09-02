package com.suji.mod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import com.suji.db.DBUtil;

public class UserDao {
	
	public static boolean insert(User user) throws SQLException {
		
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement("INSERT INTO users(fname,uname,pwd) VALUES(?,?,?)");
		
		ps.setString(1, user.getFname());
		ps.setString(1, user.getUname());
		ps.setString(1, user.getPasswrod());
		
		return ps.executeUpdate()>0;
	}
	
	public static boolean update(User user) {
		
		return false;
	}

	public static boolean delete(User user) {
		
		return false;
	}
	
	public static boolean checkAthentication(String uname, String pwd) throws SQLException {
		
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM USERS WHERE UNAME=? and PWD=?");
		ps.setString(1, uname);
		ps.setString(1, pwd);
		ResultSet rs = ps.executeQuery();
		return rs.next();
	}
	
	public static int getRecordsCount() {
		return 34;
	}


	public static User getUser(String uname) {
		
		User user = new User(uname, uname, uname);
		return user;
		
	}
	
}
