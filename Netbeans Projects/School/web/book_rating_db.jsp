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
    try {
        String uname = "";
        String user_id = "";
        
        
        String book_id = request.getParameter("book_id");
        String rate = request.getParameter("rating");
        
        
        if (session.getAttribute("uname") != null) {
            uname = session.getAttribute("uname").toString();
            user_id = session.getAttribute("user_id").toString();
        } else {
            request.getRequestDispatcher("index.jsp").include(request, response);
%>
<h3 style="color: red">Sorry, Username is wrong..."</h3>
<%
    }

    String sql1 = "select rating from ratings where user_id=? and book_id=?";
    String sql2 = "insert into ratings(user_id,book_id,rating) values(?,?,?)";
    String sql3 = "update ratings set rating=? where user_id=? and book_id=?";

    int u_id = Integer.parseInt(user_id);
    int b_id = Integer.parseInt(book_id);
    int r = Integer.parseInt(rate);

    Connection conn = DBUtil.getConnection();
    PreparedStatement ps1 = conn.prepareStatement(sql1);
    PreparedStatement ps2 = conn.prepareStatement(sql2);
    PreparedStatement ps3 = conn.prepareStatement(sql3);

    ps1.setInt(1, u_id);
    ps1.setInt(2, b_id);
    //Check rating existed or not
    if (ps1.executeQuery().next()) {
        //If the rating is already existed, Update it
        ps3.setInt(1, r);
        ps3.setInt(2, u_id);
        ps3.setInt(3, b_id);
        if (ps3.executeUpdate() > 0) {
            response.sendRedirect("user_rated_books.jsp");
        } else {
            %>
            <p>Rating is not able to possible</p>
            <%
        }
    } else { 
        //If the rating is not existed, Insert it
        ps2.setInt(1, u_id);
        ps2.setInt(2,b_id);
        ps2.setInt(3, r);
        if (ps2.executeUpdate() > 0) {
            response.sendRedirect("user_rated_books.jsp");
        } else {
            %>
            <p>Rating is not able to possible</p>
            <%
        }
    }
} catch (Exception e) {
e.printStackTrace();
%>
<h style="color: red" ><%=e%></h>
<%
    }
%>