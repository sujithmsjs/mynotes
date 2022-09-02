<%-- 
    Document   : book_rating_db
    Created on : 8 Apr, 2018, 2:05:34 PM
    Author     : shivakrishna
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="util.DBUtil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String user_id = "";
        String book_id = request.getParameter("book_id");
    try {
        String uname = "";
        
        if (session.getAttribute("uname") != null) {
            uname = session.getAttribute("uname").toString();
            user_id = session.getAttribute("user_id").toString();
        } else {
            request.getRequestDispatcher("index.jsp").include(request, response);
%>
<h3 style="color: red">Sorry, Username is wrong..."</h3>
<%
    }

    String sql1 = "insert into cart values(?,?)";
    

    int u_id = Integer.parseInt(user_id);
    int b_id = Integer.parseInt(book_id);

    Connection conn = DBUtil.getConnection();
    PreparedStatement ps1 = conn.prepareStatement(sql1);
    
    ps1.setInt(1, u_id);
    ps1.setInt(2, b_id);
    //Check rating existed or not
     if (ps1.executeUpdate() > 0) {
            response.sendRedirect("user_buy.jsp");
        } else {
%>
<p>Rating is not able to possible</p>
<%
        
    }
} catch (Exception e) {
%>
<h1 style="color: red" ><%=e%></h1>
<h1 style="color: red" >Book id : <%=book_id%></h1>
<h1 style="color: red" >Book id : <%=user_id%></h1>

<%
    }
%>