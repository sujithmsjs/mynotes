package com.suji.ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.suji.mod.User;
import com.suji.util.SessionUtil;



public class LogoutCtrl extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		User user = SessionUtil.getUser(session);
		if(user != null) {
			String msg = "You're logged out successfully...";
			request.setAttribute(AttrNames.MESSAGE, msg);
		}
		
		session.invalidate();
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}


}
