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
                request.getRequestDispatcher("user_login.jsp").forward(request, response);
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
                        <li><a href="user_home.jsp">Home</a></li>
                        <li class="selected"><a href="user_search.jsp">Search</a></li>
                        <li><a href="user_buy.jsp">Buy</a></li>
                        <li><a href="user_rec.jsp">Recomendation</a></li>
                        <li><a href="user_feedback.jsp">Feedback</a></li>
                        <li><a href="user_logout.jsp">Logout</a></li>
                    </ul>
                </div>
            </div>
            <div id="site_content">
                <form  action="" method='post'>
                    <input type="text" placeholder="Book name/id" name="search" required>
                    <button type='submit'>Search</button>
                </form>
                <center>
                    <div>
                        <%
                            try {
                                String search = request.getParameter("search");
                                Connection conn = DBUtil.getConnection();
                                String sql1 = "(select books.book_id,books.name,author,pub,pub_year,cost,rating from books left join ratings on ratings.book_id=books.book_id and user_id=1 where books.name like '"+search+"%')";
                                String sql2 = "(select books.book_id,books.name,author,pub,pub_year,cost,rating from books left join ratings on ratings.book_id=books.book_id and user_id=1 where books.name like '%"+search+"%')";
                                String sql3 = "(select books.book_id,books.name,author,pub,pub_year,cost,rating from books left join ratings on ratings.book_id=books.book_id and user_id=1 where books.name like '%"+search+"')";
                                String sql = sql1 + " UNION " + sql2 + " UNION " + sql3;
                                PreparedStatement ps = conn.prepareStatement(sql);
           
                                ResultSet rs = ps.executeQuery();
                        %>

                        <table id="t01">
                            <tr>
                                <th>Book Id</th>

                                <th>Name</th> 
                                <th>Author</th>
                                <th>Publisher</th>
                                <th>Date</th>                                
                                <th>Cost</th>
                                <th>Your Rate</th>
                                <th>Visit</th>

                            </tr>
                            <%
                                while (rs.next()) {
                                    int book_id = rs.getInt(1);
                            %>
                            <tr>
                                <td><%=book_id%></td>
                                <td><%=rs.getString(2)%></td>
                                <td><%=rs.getString(3)%></td>
                                <td><%=rs.getString(4)%></td>
                                <td><%=rs.getString(5)%></td>
                                <td><%=rs.getInt(6)%></td>
                                <td><%=rs.getInt(7)%></td>
                                <td>
                                    <form action="user_rating.jsp">
                                        <input type="hidden" value="<%=book_id%>" name="book_id" />
                                        <input style="display: inline" type="submit" value="View" />
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

