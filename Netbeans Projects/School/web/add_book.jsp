<%-- 
    Document   : add_book
    Created on : 7 Apr, 2018, 9:27:15 PM
    Author     : shivakrishna
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="util.DBUtil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
    <head></head>
    <body>
        <%
            try {
                String bname = request.getParameter("bname");
                String author = request.getParameter("author");
                String pub = request.getParameter("pub");
                String year = request.getParameter("year");
                int cost = Integer.parseInt(request.getParameter("cost"));
                int sub, his, rel, sto, com, gk, nov, fun;
                sub = his = rel = sto = com = gk = nov = fun = 0;

                String[] types = request.getParameterValues("types");
                for (String s : types) {
                    if (s.equalsIgnoreCase("subject")) {
                        sub = 1;
                    }
                    if (s.equalsIgnoreCase("history")) {
                        his = 1;
                    }
                    if (s.equalsIgnoreCase("religion")) {
                        rel = 1;
                    }
                    if (s.equalsIgnoreCase("story")) {
                        sto = 1;
                    }
                    if (s.equalsIgnoreCase("comics")) {
                        com = 1;
                    }
                    if (s.equalsIgnoreCase("gk")) {
                        gk = 1;
                    }
                    if (s.equalsIgnoreCase("novel")) {
                        nov = 1;
                    }
                    if (s.equalsIgnoreCase("fun")) {
                        fun = 1;
                    }
                }

                Connection conn = DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement("insert into books(name,author,pub,pub_year,cost,subject,history,religion,story,comics,Gk,Novel,Fun) values(?,?,?,?,?,?,?,?,?,?,?,?,?)");

                ps.setString(1, bname);
                ps.setString(2, author);
                ps.setString(3, pub);
                ps.setString(4, year);
                ps.setInt(5, cost);

                ps.setInt(6, sub);
                ps.setInt(7, his);
                ps.setInt(8, rel);
                ps.setInt(9, sto);
                ps.setInt(10, com);
                ps.setInt(11, gk);
                ps.setInt(12, nov);
                ps.setInt(13, fun);

                if (ps.executeUpdate() > 0) {
        %>
        <h1 style="color:green">Book Added Sucessfully</h1>
        <%
        } else {
        %>
        <h1 style="color:red">Sorry! Book Added Failed</h1>
        <%
            }
        } catch (ClassNotFoundException e) {
        %>
        <h1 style="color:red">Error!</h1>
        <h1 style="color:orangered"><%=e%></h1>
        <%
        } catch (SQLException e) {
        %>
        <h1 style="color:red">Error!</h1>
        <h1 style="color:orangered"><%=e%></h1>
        <%
        } catch (Exception e) {
        %>
        <h1 style="color:red">Error!</h1>
        <h1 style="color:orangered"><%=e%></h1>
        <%
            }
        %>
    </body>