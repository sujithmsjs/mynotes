package com.suji.ctrl;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.suji.mod.User;
import com.suji.mod.UserDao;
import com.suji.util.SessionUtil;


public class LoginCtrl extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		
		HttpSession session = request.getSession(false);
		if(SessionUtil.isValid(session)) {
			//If valid user try to login, previous user has get logged out automatically.
			session.invalidate();
		}
		try {
			if(UserDao.checkAthentication(name, pwd)) {
				
				User user = UserDao.getUser(name);
				HttpSession newSession = request.getSession();
				newSession.setAttribute(AttrNames.USER_OBJECT, user);
				response.sendRedirect("profile.jsp");
				
			}else {
				
				request.setAttribute(AttrNames.MESSAGE, "Incorrect Username/Password.");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				
			}
		} catch (Exception e) {
			request.setAttribute(AttrNames.MESSAGE, e);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
		
		
	}


}
