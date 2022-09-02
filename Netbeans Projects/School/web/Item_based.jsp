<%-- 
    Document   : admin_view_books
    Created on : 7 Apr, 2018, 11:21:02 PM
    Author     : shivakrishna
--%>

<%@page import="CF.SimTest"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="util.DBUtil"%>
<%@page import="java.sql.SQLException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>

    <head>
        <title>simplestyle_banner</title>
        <meta name="description" content="website description" />
        <meta name="keywords" content="website keywords, website keywords" />
        <meta http-equiv="content-type" content="text/html; charset=windows-1252" />
        <link rel="stylesheet" type="text/css" href="style/style.css" />
    </head>

    <body>

        <%
            String uname = "";
            String user_id = "";
            if (session.getAttribute("uname") != null) {
                uname = session.getAttribute("uname").toString();
                user_id = session.getAttribute("user_id").toString();

            } else {
                request.getRequestDispatcher("index.jsp").include(request, response);
        %>
        <h3 style="color: blue">You have to login again!!</h3>
        <%
            }
        %>


        <div id="main">
            <div id="header">
                <div id="logo">
                    <div id="logo_text">
                        <!-- class="logo_colour", allows you to change the colour of the text -->
                        <h1><a href="index.html">Online<span class="logo_colour">Books Store</span></a><span style="font-size: 16px;color: tomato">(<%=uname%>) </span></h1>
                        <h2>Online book recommendation system by using collaborative filtering and association mining.</h2>
                    </div>
                </div>
                <div id="menubar">
                    <ul id="menu">
                        <!-- put class="selected" in the li tag for the selected page - to highlight which page you're on -->

                        <li><a href="user_rec.jsp">Recomendation</a></li>
                        <li><a href="freq_patterns.jsp">Frequest Patterns</a></li>
                        <li><a href="content_based.jsp">Content Based</a></li>
                        <li><a href="Item_based.jsp">Item based</a></li>
                        <li><a href="user_home.jsp">Back</a></li>
                    </ul>
                </div>
            </div>
            <div id="site_content">

                <center>
                    <div>
                        <%
                            try {
                                Connection conn = DBUtil.getConnection();
                                String sql = "select book_id,name from books";

                                PreparedStatement ps = conn.prepareStatement(sql);
                                ResultSet rs = ps.executeQuery();
                                ArrayList<Integer> arr = new ArrayList<Integer>();
                                ArrayList<String> books = new ArrayList<String>();
                                while(rs.next()){
                                    arr.add(rs.getInt(1));
                                    books.add(rs.getString(2));
                                }
                            
                                
                        %>

                        
                        
                        <table id="t01">
                        
                            <%
                                
                            for(int i=0;i<arr.size()-1;i++){
                                    for(int j=i+1;j<arr.size();j++){
                                %>
                                <tr>
                                <td>
                                    <%= books.get(i) %>
                                </td>
                                <td>
                                    <%= books.get(j) %>
                                </td>
                                <td>
                                    <%= SimTest.getSimilarity(arr.get(i), arr.get(j)) %>
                                </td>
                                </tr>
                                <%
                                    }
                                }
                                    %>
                            
                            
                        </table>
                    </div>
                </center>

                <%
                    rs.close();
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
                <div id="content_footer"></div>
                <div id="footer">
                    <p> &copy; All rights received.</p>
                </div>
            </div>
    </body>
</html>

