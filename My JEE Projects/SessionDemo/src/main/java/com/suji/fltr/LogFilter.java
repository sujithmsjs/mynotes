package com.suji.fltr;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class LogFilter implements javax.servlet.Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
		String str = request.getParameter("uname");
		if(str.length() > 4) {
			System.out.println("Hey buddy! Welcome to Login page.");
			chain.doFilter(request, response);
		}else {
			PrintWriter out = response.getWriter();
			System.out.println("init(FilterConfig)");
			out.println("<h1>Hey buddy! String length must be > 4.</h1>");
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	
		System.out.println("init(FilterConfig)");
		
	}

	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("destroy()");
	}
}
