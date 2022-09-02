# QuoteBook


### File Structure
```pre
+ QuoteBook\ 
	|---  pom.xml
	+ \src\main\java\com\suji\ctrl
		|---  AttrNames.java
		|---  LoginCtrl.java
		|---  LogoutCtrl.java
		|---  PostQuote.java
		|---  SignupCtrl.java
	+ \src\main\java\com\suji\db
		|---  DBUtil.java
		|---  DateUtil.java
	+ \src\main\java\com\suji\filter
		|---  AuthFilter.java
	+ \src\main\java\com\suji\lsnr
		|---  SessionCheck.java
	+ \src\main\java\com\suji\mod
		|---  Quote.java
		|---  QuotePagition.java
		|---  User.java
		|---  UserDao.java
	+ \src\main\java\com\suji\util
		|---  SessionUtil.java
```
### Index
```pre
1. pom.xml
2. src\main\java\com\suji\ctrl\AttrNames.java
3. src\main\java\com\suji\ctrl\LoginCtrl.java
4. src\main\java\com\suji\ctrl\LogoutCtrl.java
5. src\main\java\com\suji\ctrl\PostQuote.java
6. src\main\java\com\suji\ctrl\SignupCtrl.java
7. src\main\java\com\suji\db\DBUtil.java
8. src\main\java\com\suji\db\DateUtil.java
9. src\main\java\com\suji\filter\AuthFilter.java
10. src\main\java\com\suji\lsnr\SessionCheck.java
11. src\main\java\com\suji\mod\Quote.java
12. src\main\java\com\suji\mod\QuotePagition.java
13. src\main\java\com\suji\mod\User.java
14. src\main\java\com\suji\mod\UserDao.java
15. src\main\java\com\suji\util\SessionUtil.java

```

---

### 1. pom.xml

#### pom.xml

```xml

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>com.suji</groupId>
	<artifactId>QuoteBook</artifactId>
	<packaging>war</packaging>
	<version>0.0.1-SNAPSHOT</version>
	<name>QuoteBook Maven Webapp</name>
	<url>http://maven.apache.org</url>
	<dependencies>
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>3.8.1</version>
			<scope>test</scope>
		</dependency>
	</dependencies>
	<build>
		<finalName>QuoteBook</finalName>

		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-war-plugin</artifactId>
				<version>3.3.1</version>
			</plugin>
		</plugins>


	</build>
</project>

```

---

### 2. AttrNames.java

#### src\main\java\com\suji\ctrl\AttrNames.java

```java

package com.suji.ctrl;

public interface AttrNames {
	 String USER_NAME = "userName";
	 String USER_OBJECT = "userObject";
	 String MESSAGE = "Message";
	 
}

```

---

### 3. LoginCtrl.java

#### src\main\java\com\suji\ctrl\LoginCtrl.java

```java

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

```

---

### 4. LogoutCtrl.java

#### src\main\java\com\suji\ctrl\LogoutCtrl.java

```java

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

```

---

### 5. PostQuote.java

#### src\main\java\com\suji\ctrl\PostQuote.java

```java

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

```

---

### 6. SignupCtrl.java

#### src\main\java\com\suji\ctrl\SignupCtrl.java

```java

package com.suji.ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.suji.mod.User;
import com.suji.mod.UserDao;



public class SignupCtrl extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String fname = request.getParameter("fname");
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		
		User user = new User(fname, uname, pwd);
		boolean isInserted;
		try {
			isInserted = UserDao.insert(user);
			if(isInserted) {
				request.setAttribute(AttrNames.MESSAGE, "You're account has created successfully.");
				response.sendRedirect("login.jsp");
			}else {
				request.setAttribute(AttrNames.MESSAGE, "You're account has created successfully.");
				response.sendRedirect("login.jsp");
			}
		} catch (SQLException e) {
			
			request.setAttribute(AttrNames.MESSAGE, e);
			request.getRequestDispatcher("signup.jsp").forward(request, response);
			
		}
		
		
		
		
	}


}

```

---

### 7. DBUtil.java

#### src\main\java\com\suji\db\DBUtil.java

```java


package com.suji.db;


import java.sql.*;

public class DBUtil {

	private static Connection con;

	static {
		if (con == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/qbook", "root", "apple");
			} catch (SQLException ex) {
				ex.printStackTrace();
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static Connection getConnection(){
		return con;
	}
	
	// This method is to provide exception free code.
		public static Connection getAWSConnection() {
			Connection con = null;
			if (con == null) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					String host= "mysqldb.c7iowgwompfy.ap-south-1.rds.amazonaws.com";
					
					con = DriverManager.getConnection("jdbc:mysql://"+host+":3306/suji", "admin", "NvrStle#22");
				} catch (SQLException ex) {
					ex.printStackTrace();
				} catch (ClassNotFoundException ex) {
					ex.printStackTrace();
				}
			}
			return con;
		}

}

```

---

### 8. DateUtil.java

#### src\main\java\com\suji\db\DateUtil.java

```java

package com.suji.db;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {
	
	public static java.util.Date toUtilDate(String date){
		return toUtilDate(date, "yyyy-MM-dd");
	}
	
	public static java.util.Date toUtilDate(LocalDate locDate){
		Date date = Date.from(locDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		return date;
	}
	
	public static java.util.Date toUtilDate(String date, String pattern){
		
		DateFormat formatter = new SimpleDateFormat(pattern);
		Date myDate = null;

		try {
			myDate = formatter.parse(date);
		} catch (ParseException e) {
			
			e.printStackTrace();
			myDate = new Date();
			
		}

		return myDate;
	}
	
	public static java.util.Date toUtilDate(java.sql.Date date){
		return date;
	}
	
	public static java.sql.Date toSQLDate(String date, String pattern){
		
		java.util.Date utilDate = toUtilDate(date, pattern);
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		
		return sqlDate;
	}
	
	public static java.sql.Date toSQLDate(String date){
		return	toSQLDate(date, "yyyy-MM-dd");
	}
	
	public static java.sql.Date toSQLDate(java.util.Date date){
		return new java.sql.Date(date.getTime());
	}
	
	public static java.time.LocalDate toLocalDate(String date, String pattern){
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		LocalDate locDate = LocalDate.parse(date, formatter);
		return locDate;
	}
	
	public static java.time.LocalDate toLocatDate(java.util.Date date){
		LocalDate locDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return locDate;
	}

}

```

---

### 9. AuthFilter.java

#### src\main\java\com\suji\filter\AuthFilter.java

```java

package com.suji.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;




public class AuthFilter implements Filter {
	
	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}

```

---

### 10. SessionCheck.java

#### src\main\java\com\suji\lsnr\SessionCheck.java

```java

package com.suji.lsnr;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class SessionCheck
 *
 */
public class SessionCheck implements HttpSessionListener {

	
	private int count;
    /**
     * Default constructor. 
     */
    public SessionCheck() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0)  { 
    	count++;
    	System.out.println("Session no."+count+" Created.");
    	
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
    	System.out.println("Session no."+count+" Destroyed.");
    }
	
}

```

---

### 11. Quote.java

#### src\main\java\com\suji\mod\Quote.java

```java

package com.suji.mod;

public class Quote {
	private int qid,uid;
	private String quote, authar;
	
	
	
	
	public Quote(int qid, int uid, String quote, String authar) {
		super();
		this.qid = qid;
		this.uid = uid;
		this.quote = quote;
		this.authar = authar;
	}


	@Override
	public String toString() {
		return "Quote [qid=" + qid + ", uid=" + uid + ", quote=" + quote + ", authar=" + authar + "]";
	}


	public int getQid() {
		return qid;
	}


	public void setQid(int qid) {
		this.qid = qid;
	}


	public int getUid() {
		return uid;
	}


	public void setUid(int uid) {
		this.uid = uid;
	}


	public String getQuote() {
		return quote;
	}


	public void setQuote(String quote) {
		this.quote = quote;
	}


	public String getAuthar() {
		return authar;
	}


	public void setAuthar(String authar) {
		this.authar = authar;
	}
	
	
	
	
	
}

```

---

### 12. QuotePagition.java

#### src\main\java\com\suji\mod\QuotePagition.java

```java

package com.suji.mod;

import java.util.ArrayList;
import java.util.List;

public class QuotePagition {
	
	public static List<Quote> getRecords(int page) {
		int limit = 10;
		return get(limit, (page) * limit);
	}

	public static List<Quote> get(int limit, int offset) {

		List<Quote> quotes = new ArrayList<Quote>();
		
		for (int i = offset; i < offset + limit; i++) {
			Quote q = new Quote(i, i, "Quote "+i, "Author "+i);
			quotes.add(q);
		}
		
		return quotes;
	
	}

	public static int getPages(int rec, int lim) {
		int pages = rec / lim;
		if (rec % lim > 0) {
			pages++;
		}
		return pages;
	}
}

```

---

### 13. User.java

#### src\main\java\com\suji\mod\User.java

```java

package com.suji.mod;

public class User {
	private int uid;
	String fname, uname, pwd;
	
	
	
	public User(String fname, String uname, String pwd) {
		
		this.fname = fname;
		this.uname = uname;
		this.pwd = pwd;
		
	}
	
	
	public int getUid() {
		return uid;
	}
	
	public String getPasswrod() {
		return pwd;
	}
	
	
	
	
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}


	@Override
	public String toString() {
		return "User [uid=" + uid + ", fname=" + fname + ", uname=" + uname + "]";
	}
	
	
	
}

```

---

### 14. UserDao.java

#### src\main\java\com\suji\mod\UserDao.java

```java

package com.suji.mod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import com.suji.db.DBUtil;

public class UserDao {
	
	public static boolean insert(User user) throws SQLException {
		
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement("INSERT INTO users(fname,uname,pwd) VALUES(?,?,?)");
		
		ps.setString(1, user.getFname());
		ps.setString(1, user.getUname());
		ps.setString(1, user.getPasswrod());
		
		return ps.executeUpdate()>0;
	}
	
	public static boolean update(User user) {
		
		return false;
	}

	public static boolean delete(User user) {
		
		return false;
	}
	
	public static boolean checkAthentication(String uname, String pwd) throws SQLException {
		
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM USERS WHERE UNAME=? and PWD=?");
		ps.setString(1, uname);
		ps.setString(1, pwd);
		ResultSet rs = ps.executeQuery();
		return rs.next();
	}
	
	public static int getRecordsCount() {
		return 34;
	}


	public static User getUser(String uname) {
		
		User user = new User(uname, uname, uname);
		return user;
		
	}
	
}

```

---

### 15. SessionUtil.java

#### src\main\java\com\suji\util\SessionUtil.java

```java

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

```

---

