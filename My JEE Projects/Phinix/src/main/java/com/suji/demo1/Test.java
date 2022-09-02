package com.suji.demo1;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.suji.util.Student;


public class Test extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		try {
			
			RequestDispatcher dispatcher =	request.getRequestDispatcher("dsipatcherTest.jsp");
			
			Student s1 = new Student("Jake",123);
			Student s2 = new Student("John",124);
			
			request.setAttribute("s1", s1);
			request.setAttribute("s2", s2);
			
			dispatcher.forward(request, response);
			
			
			
			
		} catch (Exception e) {
			
		}
	}

}
