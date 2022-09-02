package com.suji.cookie;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieDemo extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		
		Cookie ck = new Cookie("username",uname);
		response.addCookie(ck);
		
		response.sendRedirect("welcome.jsp");
	}
}
