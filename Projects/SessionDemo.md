# SessionDemo


### File Structure
```pre
+ SessionDemo\ 
	|---  pom.xml
	+ \src\main\java\com\suji\ctrl
		|---  LoginCtrl.java
		|---  LogoutCtrl.java
	+ \src\main\java\com\suji\db
		|---  DBUtil.java
		|---  DateUtil.java
	+ \src\main\java\com\suji\fltr
		|---  LogFilter.java
	+ \src\main\java\com\suji\util
		|---  AttrNames.java
```
### Index
```pre
1. pom.xml
2. src\main\java\com\suji\ctrl\LoginCtrl.java
3. src\main\java\com\suji\ctrl\LogoutCtrl.java
4. src\main\java\com\suji\db\DBUtil.java
5. src\main\java\com\suji\db\DateUtil.java
6. src\main\java\com\suji\fltr\LogFilter.java
7. src\main\java\com\suji\util\AttrNames.java

```

---

### 1. pom.xml

#### pom.xml

```xml

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>com.suji</groupId>
	<artifactId>SessionDemo</artifactId>
	<packaging>war</packaging>
	<version>0.0.1-SNAPSHOT</version>
	<name>SessionDemo Maven Webapp</name>
	<url>http://maven.apache.org</url>
	<dependencies>


		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>3.8.1</version>
			<scope>test</scope>
		</dependency>

		
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>javax.servlet-api</artifactId>
			<version>3.1.0</version>
			<scope>provided</scope>
		</dependency>


	</dependencies>
	<build>


		<finalName>SessionDemo</finalName>


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

### 2. LoginCtrl.java

#### src\main\java\com\suji\ctrl\LoginCtrl.java

```java

package com.suji.ctrl;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.suji.util.AttrNames;

public class LoginCtrl extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		// Retriving Parameters
		String name = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		
		// Check if session exists
		if(session == null) {
			
			// Check Authentication
			if(pwd.equalsIgnoreCase("admin123")) {
				
				HttpSession newSession = request.getSession();
				
				newSession.setAttribute(AttrNames.USER_NAME, name);
				response.sendRedirect("profile.jsp");
				
	
			}else {
				
				request.setAttribute(AttrNames.MESSAGE, "Username/Password Incorrect!");
				request.getRequestDispatcher("login.jsp").include(request, response);
				
			}	
		}else {
			// Not logout
			session.setAttribute(AttrNames.MESSAGE, "You're session hasn't logged out yet.");
			response.sendRedirect("profile.jsp");
			
		}
		
		
	}
}

```

---

### 3. LogoutCtrl.java

#### src\main\java\com\suji\ctrl\LogoutCtrl.java

```java

package com.suji.ctrl;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.suji.util.AttrNames;

public class LogoutCtrl extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		HttpSession session = request.getSession(false); 
		
		/*
		 
		 if session is null then show "You can't logout"
		 else logout session forword to login.
		 
		 
		 
		 */
		
		if (session == null) {
			
				request.setAttribute(AttrNames.MESSAGE, "You can't Logout before Login.!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			
		}else {	
			
			String uname = (String) session.getAttribute(AttrNames.USER_NAME);
			session.invalidate();
			request.setAttribute(AttrNames.MESSAGE, "Dear "+uname +" you have successfully logged out.");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			

		}

	}

}

```

---

### 4. DBUtil.java

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
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db7", "root", "apple");
			} catch (SQLException ex) {
				ex.printStackTrace();
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
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

### 5. DateUtil.java

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

### 6. LogFilter.java

#### src\main\java\com\suji\fltr\LogFilter.java

```java

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

```

---

### 7. AttrNames.java

#### src\main\java\com\suji\util\AttrNames.java

```java

package com.suji.util;

public interface AttrNames {
	String MESSAGE = "msg";
	String USER_NAME = "userName";
}

```

---

