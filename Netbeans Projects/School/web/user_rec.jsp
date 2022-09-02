<%-- 
    Document   : admin_view_books
    Created on : 7 Apr, 2018, 11:21:02 PM
    Author     : shivakrishna
--%>

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
                        <li><a href="Item_based.jsp">Item Based</a></li>
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
                                String sql = "select books.book_id,name,count(*),sum(rating),sum(rating)/count(*) from ratings inner join books on ratings.book_id=books.book_id group by ratings.book_id order by rating desc";

                                PreparedStatement ps = conn.prepareStatement(sql);
                                ResultSet rs = ps.executeQuery();
                        %>

                        <table id="t01">
                            <tr>
                                <th>Book Id</th>
                                <th>Name</th> 
                                <th>Total Ratings</th>
                                <th>User Rated</th>
                                <th>Rate</th>                                
                                <th>Visit</th>
                                
                            </tr>
                            <%
                                while (rs.next()) {
                                    int book_id = rs.getInt(1);
                                    String name = rs.getString(2);
                                    int tot_user=rs.getInt(3);
                                    int tot_rate=rs.getInt(4);
                                    double rate= rs.getDouble(5);
                            %>
                            <tr>
                                <td><%=book_id%></td>
                                <td><%=name%></td>
                                <td><%=tot_rate%></td>
                                <td><%=tot_user%></td>
                                <td><%=rate%></td>
                                <td>
                                    <form action="user_rating.jsp">
                                        <input type="hidden" value="<%=book_id%>" name="book_id" />
                                        <input   type="submit" value="View" />
                                    </form>
                                </td>
                            </tr>
                            <%

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

