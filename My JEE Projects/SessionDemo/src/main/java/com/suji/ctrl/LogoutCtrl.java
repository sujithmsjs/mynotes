package com.suji.ctrl;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.suji.util.AttrNames;

public class LogoutCtrl extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		HttpSession session = request.getSession(false); 
		
		/*
		 
		 if session is null then show "You can't logout"
		 else logout session forword to login.
		 
		 
		 
		 */
		
		if (session == null) {
			
				request.setAttribute(AttrNames.MESSAGE, "You can't Logout before Login.!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			
		}else {	
			
			String uname = (String) session.getAttribute(AttrNames.USER_NAME);
			session.invalidate();
			request.setAttribute(AttrNames.MESSAGE, "Dear "+uname +" you have successfully logged out.");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			

		}

	}

}
