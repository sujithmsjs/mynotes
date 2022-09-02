<%-- 
    Document   : index
    Created on : 8 Apr, 2018, 1:29:43 PM
    Author     : shivakrishna
--%>

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
        <div id="main">
            <div id="header">
                <div id="logo">
                    <div id="logo_text">
                        <!-- class="logo_colour", allows you to change the colour of the text -->
                        <h1><a href="index.html">Online<span class="logo_colour">Books Store</span></a></h1>
                        <h2>Online book recommendation system by using collaborative filtering and association mining.</h2>
                    </div>
                </div>
                <div id="menubar">
                    <ul id="menu">
                        <!-- put class="selected" in the li tag for the selected page - to highlight which page you're on -->
                        <li class="selected"><a href="index.jsp">Home</a></li>
                        <li><a href="about.html">About us</a></li>
                        <li><a href="contact.html">Contact us</a></li>
                        <li><a href="register.html">Register</a></li>
                        <li><a href="userlogin.html">Login</a></li>
                    </ul>
                </div>
            </div>
            <div id="site_content">
                <div id="banner"></div>
                <div>
                    <!-- insert the page content here -->
                    <h1>Welcome to the Recommendation systems</h1>
                    <p>Recommendation systems is used for the purpose of
                        suggesting items to purchase or to see. They direct users towards
                        those items which can meet their needs through cutting down
                        large database of Information. A various techniques have been
                        introduced for recommending items i.e. content, collaborative
                        and association mining techniques are used. This paper solves the
                        problem of data sparsity problem by combining the
                        collaborative-based filtering and association rule mining to
                        achieve better performance. The results obtained are
                        demonstrated and the proposed recommendation algorithms
                        perform better and solve the challenges such as data sparsity and
                        scalability.</p>
                </div>
            </div>
            <div id="content_footer"></div>
            <div id="footer">
                <p> &copy; All rights received.</p>
            </div>
        </div>
    </body>
</html>

