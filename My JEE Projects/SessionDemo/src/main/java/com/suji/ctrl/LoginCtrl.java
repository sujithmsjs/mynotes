package com.suji.ctrl;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.suji.util.AttrNames;

public class LoginCtrl extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		// Retriving Parameters
		String name = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		
		// Check if session exists
		if(session == null) {
			
			// Check Authentication
			if(pwd.equalsIgnoreCase("admin123")) {
				
				HttpSession newSession = request.getSession();
				
				newSession.setAttribute(AttrNames.USER_NAME, name);
				response.sendRedirect("profile.jsp");
				
	
			}else {
				
				request.setAttribute(AttrNames.MESSAGE, "Username/Password Incorrect!");
				request.getRequestDispatcher("login.jsp").include(request, response);
				
			}	
		}else {
			// Not logout
			session.setAttribute(AttrNames.MESSAGE, "You're session hasn't logged out yet.");
			response.sendRedirect("profile.jsp");
			
		}
		
		
	}
}
