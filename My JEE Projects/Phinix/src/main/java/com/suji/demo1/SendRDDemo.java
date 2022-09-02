package com.suji.demo1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendRDDemo extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sec = request.getParameter("word");
		
		response.sendRedirect("https://www.oxfordlearnersdictionaries.com/definition/english/"+sec);
		//request.getRequestDispatcher("dsipatcherTest.jsp").forward(request, response);
		
		
	}
}
