package com.suji.ctrl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.suji.mod.User;
import com.suji.util.SessionUtil;



public class PostQuote extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		String quote = request.getParameter("quote");
		String author = request.getParameter("author");
		HttpSession session = request.getSession(false);
		
		if(SessionUtil.isValid(session)) {
			User user = SessionUtil.getUser(session);
			
			out.println("Quote : "+ quote);
			out.println("UID : " + 	user.getUid());
			out.println("Author : "+author);
			
			session.setAttribute(AttrNames.MESSAGE, "You have posted successfully!");
			
			response.sendRedirect("walls.jsp");
			
		}else {
				
				session.invalidate();
				response.sendRedirect("login.jsp");
			
		}
		
		
	}



}
