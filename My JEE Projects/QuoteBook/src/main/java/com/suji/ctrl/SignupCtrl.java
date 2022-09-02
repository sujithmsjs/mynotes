package com.suji.ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.suji.mod.User;
import com.suji.mod.UserDao;



public class SignupCtrl extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String fname = request.getParameter("fname");
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		
		User user = new User(fname, uname, pwd);
		boolean isInserted;
		try {
			isInserted = UserDao.insert(user);
			if(isInserted) {
				request.setAttribute(AttrNames.MESSAGE, "You're account has created successfully.");
				response.sendRedirect("login.jsp");
			}else {
				request.setAttribute(AttrNames.MESSAGE, "You're account has created successfully.");
				response.sendRedirect("login.jsp");
			}
		} catch (SQLException e) {
			
			request.setAttribute(AttrNames.MESSAGE, e);
			request.getRequestDispatcher("signup.jsp").forward(request, response);
			
		}
		
		
		
		
	}


}
