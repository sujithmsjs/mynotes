package com.suji.demo1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.suji.util.Student;

public class Login extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		PrintWriter out = null;

		try {
			out	= response.getWriter();
			out.append("<h1>Sujith</h1>Served at: ").append(request.getContextPath());

			String uname = request.getParameter("uname");
			String pwd = request.getParameter("pwd");
			String encode = request.getCharacterEncoding();
			
			out.append("<h1> Encode: "+encode+"</h1>");

			boolean isAthenticated =false;
			if (uname.equalsIgnoreCase("admin") && pwd.equalsIgnoreCase("1234")) {
				isAthenticated = true;
				RequestDispatcher rd = request.getRequestDispatcher("dsipatcherTest.jsp");
				
				
				Student s1 = new Student("Jake",123);
				Student s2 = new Student("John",124);
				
				request.setAttribute("s1", s1);
				request.setAttribute("s2", s2);
				
				out.print("<h1>Before Include</h1>");
				rd.forward(request, response);
				out.print("<h1>After Include</h1>");
				
				Student s3 = new Student("Mark",203);
				Student s4 = new Student("Andrew",204);
				
				
				request.setAttribute("s3", s3);
				request.setAttribute("s4", s4);
				

			} else {

				;
				RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
				out.print("<h1>Before Include</h1>");
				rd.include(request, response);
				out.print("<h1>After Include</h1>");
				out.print("<h1>Sorry UserName or Password Error!</h1>");
				
			}
			
			System.out.println("This is the END!!! "+isAthenticated);
		} catch (Exception e) {
			out.print("<h1>"+e+"</h1>");
		}
		
		out.close();
	}

}
