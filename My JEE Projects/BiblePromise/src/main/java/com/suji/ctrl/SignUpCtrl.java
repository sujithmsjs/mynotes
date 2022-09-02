package com.suji.ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.suji.util.DBUtil;
import com.suji.util.DateUtil;

@MultipartConfig
public class SignUpCtrl extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String dob = request.getParameter("dob");
		String marks = request.getParameter("marks");
		Part part = request.getPart("file");
		
		PrintWriter out = response.getWriter();
		out.write("<h1>"+name+"</h1>");
		out.write("<h1>"+password+"</h1>");
		out.write("<h1>"+dob+"</h1>");
		out.write("<h1>"+marks+"</h1>");
		out.write("<h1>"+part+"</h1>");
		
		Connection conn = DBUtil.getAWSConnection();
		try {
			
			
			
			PreparedStatement ps = conn.prepareStatement("insert into person(name,password,dob,marks,image) value(?,?,?,?,?);");
			
			ps.setString(1, name);
			ps.setString(2, password);
			ps.setDate(3, DateUtil.toSQLDate(dob));
			ps.setDouble(4, Double.parseDouble(marks));
			ps.setBlob(5, part.getInputStream());
		
			if(ps.executeUpdate()>0) {
				out.write("<h1> Congrats... Data uploaded successfully. </h1>");
			}else {
				out.write("<h1> Something went wrong unable to save data. </h1>");
			}
			
		
		} catch (SQLException e) {
			out.write("<h1> Exception: "+e.getMessage()+"</h1>");
			e.printStackTrace();
		}

	}

}
