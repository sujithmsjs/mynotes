package com.suji.demo1;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.WriteAbortedException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Signup extends HttpServlet {

	private PrintWriter out;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Initializing Instance variable
		out = response.getWriter();

		out.append("<h1>Sujith</h1>Served at: ").append(request.getContextPath());

		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		write("Uname: " + uname);
		write("Password: " + pwd);

		// Servlet Config
		ServletConfig sConfig = getServletConfig();
		String db = sConfig.getInitParameter("db");
		String ip = sConfig.getInitParameter("ip");
		String sname = sConfig.getServletName();
		write(db);
		write(ip);
		write(sname);
		
		// Servlet Context
		ServletContext context1 = request.getServletContext();
		ServletContext context2 = getServletContext();
		ServletContext context3 = sConfig.getServletContext();
		
		if(context1 == context2 && context2 == context3) {
			write("True");
		}
		try {
			//String attname = context1.getAttribute("myname").toString();
			String initname = context1.getInitParameter("myname");
			String initname2 = context1.getInitParameter("hey");
			write("Context1: "+context1);
			
			
		//	write("Attribute name: "+attname);
			write("Init name: "+initname);
			write("Init name2: "+initname2);
			
			System.out.println("This is working without error!");
			
		}catch(Exception e) {
			write(e.toString());
		}
		write("End");
		out.close();
	}

	private void write(String str) {
		out.println("<h1>" + str + "</h1>");
	}

}
