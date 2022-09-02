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
            if (session.getAttribute("Admin") != null) {
                uname = session.getAttribute("Admin").toString();
            } else {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        %>

        <div id="main">
            <div id="header">
                <div id="logo">
                    <div id="logo_text">
                        <!-- class="logo_colour", allows you to change the colour of the text -->
                        <h1><a href="index.html">Online<span class="logo_colour">Books Store</span></a><span style="color:red"> Admin Panel</span></h1>
                        <h2>Online book recommendation system by using collaborative filtering and association mining.</h2>
                    </div>
                </div>
                <div id="menubar">
                    <ul id="menu">
                        <!-- put class="selected" in the li tag for the selected page - to highlight which page you're on -->
                        <li><a href="admin_home.jsp">Home</a></li>
                        <li><a href="admin_add_books.jsp">Add books</a></li>
                        <li><a href="admin_view_books.jsp">View books</a></li>
                        <li class="selected"><a href="admin_rec.jsp">Recomendation</a></li>
                        <li><a href="admin_feedback.jsp">Feedback</a></li>
                        <li><a href="user_logout.jsp">Logout</a></li>
                    </ul>
                </div>
            </div>
            <div id="site_content">
                <div id="banner"></div>
                <center>
                    <div>
                        <!-- insert the page content here -->
                        <form  action="add_book.jsp" method='get'>
                            <input type="text" name="bname" placeholder="Book Name" required>
                            <input type="text" name="author" placeholder="Author" required>
                            <input type="text" name="pub" placeholder="Publisher" required>
                            <input type="text" name="year" placeholder="Published Year" required>
                            <input type="text" name="cost" placeholder="Price" required>
                            <label class="container">Subject
                                <input type="checkbox" name="types" value="Subject" />
                                <span class="checkmark"></span>
                            </label>
                            <label class="container">History
                                <input type="checkbox" name="types" value="history" />
                                <span class="checkmark"></span>
                            </label>
                            <label class="container">Religion
                                <input type="checkbox" name="types" value="religion" />
                                <span class="checkmark"></span>
                            </label>
                            <label class="container">Story
                                <input type="checkbox" name="types" value="Story" />
                                <span class="checkmark"></span>
                            </label>
                            <label class="container">Comics
                                <input type="checkbox" name="types" value="Comics" />
                                <span class="checkmark"></span>
                            </label>
                            <label class="container">Gk
                                <input type="checkbox" name="types" value="Gk" />
                                <span class="checkmark"></span>
                            </label>
                            <label class="container">Novel
                                <input type="checkbox" name="types" value="Novel" />
                                <span class="checkmark"></span>
                            </label>
                            <label class="container">Fun
                                <input type="checkbox" name="types" value="Fun"/>
                                <span class="checkmark"></span>
                            </label>
                            <button type='submit'>Add Book</button>
                        </form>
                    </div>
                </center>
            </div>
            <div id="content_footer"></div>
            <div id="footer">
                <p> &copy; All rights received.</p>
            </div>
        </div>
    </body>
</html>
