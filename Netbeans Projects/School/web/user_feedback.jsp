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
                request.getRequestDispatcher("index.jsp").forward(request, response);
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
                        <li><a href="user_search.jsp">Search</a></li>
                        <li><a href="user_buy.jsp">Buy</a></li>
                        <li><a href="user_rec.jsp">Recomendation</a></li>
                        <li class="selected"><a href="user_feedback.jsp">Feedback</a></li>
                        <li><a href="user_logout.jsp">Logout</a></li>

                    </ul>
                </div>
            </div>
            <div id="site_content">
                <div id="banner"></div>
                <div>
                    <center>
                        <div>
                            <!-- insert the page content here -->
                            <form id='login-form' action="user_feedback_db.jsp" method='post' style="margin:">
                                <textarea rows="5" cols="50" style="text-align: left" placeholder="Enter your message" name="msg"></textarea>
                                <br/>
                                <button type='submit'>Send Feedback</button>
                            </form>
                        </div>
                    </center>
                </div>
            </div>
            <div id="content_footer"></div>
            <div id="footer">
                <p> &copy; All rights received.</p>
            </div>
        </div>
    </body>
</html>
