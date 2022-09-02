package com.suji.util;

import javax.servlet.http.HttpSession;

import com.suji.ctrl.AttrNames;
import com.suji.mod.User;

public class SessionUtil {
	
	

	public static boolean isValid(HttpSession session) {
		boolean isValid = false;
		try {
			if (session == null) {
				isValid = false;
			} else {

				User user = (User) session.getAttribute(AttrNames.USER_OBJECT);

				if (user != null)
					isValid = true;
				else
					isValid = false;
			}
		} catch (Exception e) {
			isValid = false;
		}

		return isValid;
	}
	
	
	public static User getUser(HttpSession session) {
		User user = (User) session.getAttribute(AttrNames.USER_OBJECT);
		return user;
	}
}
