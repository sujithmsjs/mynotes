# BiblePromise


### File Structure
```pre
+ BiblePromise\ 
	|---  pom.xml
	+ \src\main\java\com\suji\ctrl
		|---  ShowPromise.java
		|---  SignUpCtrl.java
	+ \src\main\java\com\suji\dao
		|---  Person.java
		|---  PromiseDao.java
	+ \src\main\java\com\suji\mod
		|---  EndUser.java
		|---  Promise.java
	+ \src\main\java\com\suji\util
		|---  AWSDBUtil.java
		|---  CalsUtil.java
		|---  ClientIP.java
		|---  DBUtil.java
		|---  DateUtil.java
	+ \src\main\java\com\suji\prac\exp1
		|---  Exp1.java
```
### Index
```pre
1. pom.xml
2. src\main\java\com\suji\ctrl\ShowPromise.java
3. src\main\java\com\suji\ctrl\SignUpCtrl.java
4. src\main\java\com\suji\dao\Person.java
5. src\main\java\com\suji\dao\PromiseDao.java
6. src\main\java\com\suji\mod\EndUser.java
7. src\main\java\com\suji\mod\Promise.java
8. src\main\java\com\suji\prac\exp1\Exp1.java
9. src\main\java\com\suji\util\AWSDBUtil.java
10. src\main\java\com\suji\util\CalsUtil.java
11. src\main\java\com\suji\util\ClientIP.java
12. src\main\java\com\suji\util\DBUtil.java
13. src\main\java\com\suji\util\DateUtil.java

```

---

### 1. pom.xml

#### pom.xml

```xml

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>com.suji</groupId>
	<artifactId>TestAws</artifactId>
	<packaging>war</packaging>
	<version>0.0.1-SNAPSHOT</version>
	<name>TestAws Maven Webapp</name>
	<url>http://maven.apache.org</url>
	<dependencies>
	
	
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>3.8.1</version>
			<scope>test</scope>
		</dependency>
		
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>8.0.27</version>
		</dependency>
		
		
	</dependencies>


	<build>
		<finalName>TestAws</finalName>
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

### 2. ShowPromise.java

#### src\main\java\com\suji\ctrl\ShowPromise.java

```java

package com.suji.ctrl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.suji.mod.EndUser;
import com.suji.util.CalsUtil;
import com.suji.util.ClientIP;
import com.suji.util.DBUtil;
import com.suji.util.DateUtil;

public class ShowPromise extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String name = request.getParameter("name");
		String dob = request.getParameter("dob");
		String ip = ClientIP.getClientIpAddress(request);
		
		
		
		
		Random ran = new Random();
		
		Connection conn = DBUtil.getAWSConnection();
		
		try {
			
			PreparedStatement selPs = conn.prepareStatement("select verse, ref from promises where vno = ?");
			//PreparedStatement insPs = conn.prepareStatement("select verse, ref from promises where vno = ?");
			
			
			//insPs.setString(1, name);
			//insPs.setDate(2, DateUtil.strToSqlDate(dob));
			
			
			
			
			int magicNum = CalsUtil.getNumber(name, dob);

			//Saving user details.
			EndUser.parse(name,ip,dob,magicNum).save();
			
			selPs.setInt(1, magicNum);
			
			if(selPs.execute()) {
			
				ResultSet rs = selPs.getResultSet();
				
				if(rs.next()) {
					
					request.setAttribute("name", name);
					request.setAttribute("verseNo",ran.nextInt(360)+1);
					request.setAttribute("verse", rs.getString(1));
					request.setAttribute("ref", rs.getString(2));
					
				}
			}	
			
			request.getRequestDispatcher("showPromise.jsp").forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
}

```

---

### 3. SignUpCtrl.java

#### src\main\java\com\suji\ctrl\SignUpCtrl.java

```java

package com.suji.ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.suji.util.DBUtil;
import com.suji.util.DateUtil;

@MultipartConfig
public class SignUpCtrl extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String dob = request.getParameter("dob");
		String marks = request.getParameter("marks");
		Part part = request.getPart("file");
		
		PrintWriter out = response.getWriter();
		out.write("<h1>"+name+"</h1>");
		out.write("<h1>"+password+"</h1>");
		out.write("<h1>"+dob+"</h1>");
		out.write("<h1>"+marks+"</h1>");
		out.write("<h1>"+part+"</h1>");
		
		Connection conn = DBUtil.getAWSConnection();
		try {
			
			
			
			PreparedStatement ps = conn.prepareStatement("insert into person(name,password,dob,marks,image) value(?,?,?,?,?);");
			
			ps.setString(1, name);
			ps.setString(2, password);
			ps.setDate(3, DateUtil.toSQLDate(dob));
			ps.setDouble(4, Double.parseDouble(marks));
			ps.setBlob(5, part.getInputStream());
		
			if(ps.executeUpdate()>0) {
				out.write("<h1> Congrats... Data uploaded successfully. </h1>");
			}else {
				out.write("<h1> Something went wrong unable to save data. </h1>");
			}
			
		
		} catch (SQLException e) {
			out.write("<h1> Exception: "+e.getMessage()+"</h1>");
			e.printStackTrace();
		}

	}

}

```

---

### 4. Person.java

#### src\main\java\com\suji\dao\Person.java

```java

package com.suji.dao;



```

---

### 5. PromiseDao.java

#### src\main\java\com\suji\dao\PromiseDao.java

```java

package com.suji.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.suji.util.DBUtil;
import com.suji.util.DateUtil;

public class PromiseDao {
	

	
	
}

```

---

### 6. EndUser.java

#### src\main\java\com\suji\mod\EndUser.java

```java

package com.suji.mod;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.suji.util.DBUtil;
import com.suji.util.DateUtil;

public class EndUser {

	private String name;
	private java.sql.Date dob;
	private int vno;
	private String ipAddr;

	public EndUser(String name,String ipAddr ,Date dob, int vno) {
		this.name = name;
		this.dob = dob;
		this.vno = vno;
	}
	

	public boolean save() {
		boolean save = false;
		try {

			Connection con = DBUtil.getAWSConnection();
			PreparedStatement ps = con.prepareStatement("insert into users(name,dob,ip,vno) values(?,?,?,?)");
			
			ps.setString(1, name);
			ps.setDate(2, dob );
			ps.setString(3, ipAddr);
			ps.setInt(4, vno);
			
			save = ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return save;
	}

	public static EndUser parse(String name, String ipAddr, String dob, int magicNum) {
		
		EndUser user = null;
		try {
			user = new EndUser(name,ipAddr ,DateUtil.toSQLDate(dob), magicNum );
		}catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public static ResultSet getResultSet() {
		ResultSet rs = null;
		try {

			Connection con = DBUtil.getAWSConnection();
			PreparedStatement ps = con.prepareStatement("select name, dob, time  from users");
			
			
		 rs =	ps.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}


}

```

---

### 7. Promise.java

#### src\main\java\com\suji\mod\Promise.java

```java

package com.suji.mod;

public class Promise {
	
	private int vno;
	private String verse;
	private String ref;
	
	
	public Promise(int vno, String verse, String ref) {
		super();
		this.vno = vno;
		this.verse = verse;
		this.ref = ref;
	}
	
	public int getVno() {
		return vno;
	}
	public void setVno(int vno) {
		this.vno = vno;
	}
	
	public String getVerse() {
		return verse;
	}
	public void setVerse(String verse) {
		this.verse = verse;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
}

```

---

### 8. Exp1.java

#### src\main\java\com\suji\prac\exp1\Exp1.java

```java

package com.suji.prac.exp1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Exp1 extends HttpServlet {
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append(" <h1> Welcome </h1>Served at: ").append(request.getContextPath());
	
		
	
	
	}


}

```

---

### 9. AWSDBUtil.java

#### src\main\java\com\suji\util\AWSDBUtil.java

```java

package com.suji.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AWSDBUtil {
	private static Connection con;

	static {
		if (con == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://mysqldb.c7iowgwompfy.ap-south-1.rds.amazonaws.com:3306/suji", "admin", "NvrStle#22");
			} catch (SQLException ex) {
				ex.printStackTrace();
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection c = getConnection();
		// System.out.println(getResultSet("select * from org"));
		System.out.println("Check 1");
		System.out.println(c);
	}

	private static Connection getConnection() {
		
		return con;
	}
}

```

---

### 10. CalsUtil.java

#### src\main\java\com\suji\util\CalsUtil.java

```java

package com.suji.util;

import java.time.LocalDate;

public class CalsUtil {
    public static int getNumber(String name, int month, int day) {
        name = name.toUpperCase().trim();
        LocalDate dob = LocalDate.of(1994, month, day);
        LocalDate now = LocalDate.of(2022, 1, 1);
        int doy = dob.getDayOfYear();
        int ny = now.getDayOfYear();

        int sum = 0;
        for (int i = 0; i < name.length(); i++) {

            sum += name.charAt(i) - 64;

        }
        int t = (sum * month) + doy + ny;//sum+m+d;
        return ((t % 335) + 1);
    }

	public static int getNumber(String name, String date) {
		
		LocalDate ld = DateUtil.toLocalDate(date, "yyyy-MM-dd");
		return getNumber(name, ld.getMonthValue(), ld.getDayOfMonth());
	}
}

```

---

### 11. ClientIP.java

#### src\main\java\com\suji\util\ClientIP.java

```java

package com.suji.util;

import javax.servlet.http.HttpServletRequest;

public class ClientIP {
	private static final String[] HEADERS_TO_TRY = { "X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP",
			"HTTP_X_FORWARDED_FOR", "HTTP_X_FORWARDED", "HTTP_X_CLUSTER_CLIENT_IP", "HTTP_CLIENT_IP",
			"HTTP_FORWARDED_FOR", "HTTP_FORWARDED", "HTTP_VIA", "REMOTE_ADDR" };

	public static String getClientIpAddress(HttpServletRequest request) {
		
		for (String header : HEADERS_TO_TRY) {
			String ip = request.getHeader(header);
			if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
				return ip;
			}
		}

		return request.getRemoteAddr();
	}
}

```

---

### 12. DBUtil.java

#### src\main\java\com\suji\util\DBUtil.java

```java


package com.suji.util;


import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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

### 13. DateUtil.java

#### src\main\java\com\suji\util\DateUtil.java

```java

package com.suji.util;

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

