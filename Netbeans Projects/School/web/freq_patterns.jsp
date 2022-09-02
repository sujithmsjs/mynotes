<%-- 
    Document   : admin_view_books
    Created on : 7 Apr, 2018, 11:21:02 PM
    Author     : shivakrishna
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="aprior.AprioriFrequentItemsetGenerator"%>
<%@page import="java.util.TreeSet"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
<%@page import="aprior.FrequentItemsetData"%>
<%@page import="aprior.FrequentSet"%>
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
                                FrequentSet f = new FrequentSet();

                        %>

                        <table id="t01">
                            <tr>
                                <th>Frequent Set Items</th>
                               

                            </tr>
                            <%                                for (Set<String> itemset : f.getitemSets()) {
                            %>
                            <tr>
                                <td><%= itemset%></td>

                            </tr>
                            <%
                                }
                            } catch (Exception e) {
                            %>
                            <h1><%= e%></h1>
                            <%
                                }
                            %>
                        </table>
                        <form action="show_best_CF.jsp">
                            <input type="submit" value="Best patterns" />
                        </form>
                    </div>
                </center>

                <%

                %>
                <div id="content_footer"></div>
                <div id="footer">
                    <p> &copy; All rights received.</p>
                </div>
            </div>
    </body>
</html>

