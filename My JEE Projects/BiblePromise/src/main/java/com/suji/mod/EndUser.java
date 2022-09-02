package com.suji.mod;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.suji.util.DBUtil;
import com.suji.util.DateUtil;

public class EndUser {

	private String name;
	private java.sql.Date dob;
	private int vno;
	private String ipAddr;

	public EndUser(String name,String ipAddr ,Date dob, int vno) {
		this.name = name;
		this.dob = dob;
		this.vno = vno;
	}
	

	public boolean save() {
		boolean save = false;
		try {

			Connection con = DBUtil.getAWSConnection();
			PreparedStatement ps = con.prepareStatement("insert into users(name,dob,ip,vno) values(?,?,?,?)");
			
			ps.setString(1, name);
			ps.setDate(2, dob );
			ps.setString(3, ipAddr);
			ps.setInt(4, vno);
			
			save = ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return save;
	}

	public static EndUser parse(String name, String ipAddr, String dob, int magicNum) {
		
		EndUser user = null;
		try {
			user = new EndUser(name,ipAddr ,DateUtil.toSQLDate(dob), magicNum );
		}catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public static ResultSet getResultSet() {
		ResultSet rs = null;
		try {

			Connection con = DBUtil.getAWSConnection();
			PreparedStatement ps = con.prepareStatement("select name, dob, time  from users");
			
			
		 rs =	ps.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}


}
