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
                        <li><a href="user_rec.jsp">Back</a></li>
                    </ul>
                </div>
            </div>
            <div id="site_content">

                <center>
                    <div>
                        <%
                            try {
                                 AprioriFrequentItemsetGenerator<String> generator
                                             = new AprioriFrequentItemsetGenerator<>();

                                     List<Set<String>> itemsetList = new ArrayList<>();

                                     Connection conn = DBUtil.getConnection();
                                     PreparedStatement ps1 = conn.prepareStatement("select rate_id,user_id,ratings.book_id,rating,name from ratings join books on ratings.book_id=books.book_id order by user_id");
                                     PreparedStatement ps2 = conn.prepareStatement("select user_id,count(rating)  from ratings group by user_id");
                                     ResultSet rs1 = ps1.executeQuery();
                                     ResultSet rs2 = ps2.executeQuery();

                                     while (rs2.next()) {
                                         int n = rs2.getInt(2);
                                         //     String[] s = new String[n];
                                         Set<String> s = new TreeSet<String>();
                                         // System.out.print(n+" ::");
                                         for (int i = 0; i < n; i++) {
                                             rs1.next();
                                             int book_id = rs1.getInt(3);
                                             int rate = rs1.getInt(4);
                                             if (rate >= 3) {
                                                 s.add(rs1.getString(5));
                                                 // System.out.print(book_id+",");
                                             }
                                         }
                                         itemsetList.add(new HashSet<>(s));
                                         //    System.out.println(s);
                                     }

                                     FrequentItemsetData<String> data = generator.generate(itemsetList, 0.2);
                                     int i = 1;

                                     
                              //  FrequentItemsetData<String> data =  CF.getBestCF();
                        %>
                        <table id="t01">
                            <tr>
                                <th>Frequent Set Items</th>
                                 <th>Frequency</th>

                            </tr>
                            <%
                                for (Set<String> itemset : data.getFrequentItemsetList()) {
                            %>
                            <tr>
                                <td><%= itemset%></td>
                                <td><%= data.getSupport(itemset) %></td>
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

