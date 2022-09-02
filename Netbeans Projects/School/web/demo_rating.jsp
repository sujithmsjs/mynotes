

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>

    <head>
        <title>simplestyle_banner</title>
        <meta name="description" content="website description" />
        <meta name="keywords" content="website keywords, website keywords" />
        <meta http-equiv="content-type" content="text/html; charset=windows-1252" />
        <link rel="stylesheet" type="text/css" href="style/style.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            .checked {
                color: orange;
            }

            table {
                font-family: arial, sans-serif;
                border-collapse: collapse;
            }

            td, td {
                text-align: left;
                padding: 8px;
            }
        </style>
    </head>

    <body>
        <%
            try {
                String uname = session.getAttribute("uname").toString();
                if(uname.equalsIgnoreCase("")){
                    response.sendRedirect("userlogin.html");
                }
                int book_id = Integer.parseInt(request.getParameter("book_id"));


        %>
        <div id="main">
            <div id="header">
                <div id="logo">
                    <div id="logo_text">
                        <!-- class="logo_colour", allows you to change the colour of the text -->
                        <h1><a href="index.html">Online<span class="logo_colour">Books Store</span></a><span style="font-size: 16px;color: tomato">(<%=uname%>)</span></h1>
                        <h2>Online book recommendation system by using collaborative filtering and association mining.</h2>
                    </div>
                </div>
                <div id="menubar">
                    <ul id="menu">
                        <!-- put class="selected" in the li tag for the selected page - to highlight which page you're on -->
                        <li class="selected"><a href="index.html">Home</a></li>
                        <li><a href="about.html">About us</a></li>
                        <li><a href="contact.html">Contact us</a></li>
                        <li><a href="register.html">Register</a></li>
                        <li><a href="userlogin.html">Login</a></li>
                    </ul>
                </div>
            </div>
            <div id="site_content">

                <div>
                    <!-- insert the page content here -->
                    <p style="color:#555; margin: 0px; padding: 0px">#1</p>
                    <h1 style="color:#2196F3; font-weight: bold; margin: 0px;padding: 0px;">Harry potter</h1>
                    <span class="fa fa-star checked"></span>
                    <span class="fa fa-star checked"></span>
                    <span class="fa fa-star checked"></span>
                    <span class="fa fa-star"></span>
                    <span class="fa fa-star"></span><span style="font-weight: bold">      3.7</span>

                    <table style="width: ">
                        <tr>
                            <td>Author</td>
                            <td>Author</td>
                        </tr>
                        <tr>
                            <td>Publisher</td>
                            <td>Author</td>
                        </tr>
                        <tr>
                            <td>Published</td>
                            <td>Author</td>
                        </tr>
                        <tr>
                            <td>Cost</td>
                            <td>Author</td>
                        </tr>
                    </table>
                    <form>
                        Rating:
                        <select name="rating">
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3" selected="selected">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                        </select>
                        <input type="submit" value="Submit Rate" />
                    </form>


                </div>
            </div>
            <div id="content_footer"></div>
            <div id="footer">
                <p> &copy; All rights received.</p>
            </div>
        </div>
        <%
            } catch (Exception e) {
                %>
                <h1>Error!</h1>
                <p><%=e%></p>
                <%
            }
        %>
    </body>
</html>
