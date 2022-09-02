package com.suji.ctrl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.suji.mod.EndUser;
import com.suji.util.CalsUtil;
import com.suji.util.ClientIP;
import com.suji.util.DBUtil;
import com.suji.util.DateUtil;

public class ShowPromise extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String name = request.getParameter("name");
		String dob = request.getParameter("dob");
		String ip = ClientIP.getClientIpAddress(request);
		
		
		
		
		Random ran = new Random();
		
		Connection conn = DBUtil.getAWSConnection();
		
		try {
			
			PreparedStatement selPs = conn.prepareStatement("select verse, ref from promises where vno = ?");
			//PreparedStatement insPs = conn.prepareStatement("select verse, ref from promises where vno = ?");
			
			
			//insPs.setString(1, name);
			//insPs.setDate(2, DateUtil.strToSqlDate(dob));
			
			
			
			
			int magicNum = CalsUtil.getNumber(name, dob);

			//Saving user details.
			EndUser.parse(name,ip,dob,magicNum).save();
			
			selPs.setInt(1, magicNum);
			
			if(selPs.execute()) {
			
				ResultSet rs = selPs.getResultSet();
				
				if(rs.next()) {
					
					request.setAttribute("name", name);
					request.setAttribute("verseNo",ran.nextInt(360)+1);
					request.setAttribute("verse", rs.getString(1));
					request.setAttribute("ref", rs.getString(2));
					
				}
			}	
			
			request.getRequestDispatcher("showPromise.jsp").forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
}
