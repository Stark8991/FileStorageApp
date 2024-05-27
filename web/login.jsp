<%-- 
    Document   : login
    Created on : 12-Feb-2024, 1:02:25 pm
    Author     : Prasant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.database.entites.Message"%>
<%@page import="com.database.entites.User" %>
<% 
   Message message=(Message)session.getAttribute("msg");
   User user = (User)session.getAttribute("currentUser");
   if(user!=null){
        response.sendRedirect("profile.jsp");
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/homeStyle.css" type="text/css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>NoteVault</title>
    </head>
    <body>
    <div style="height: 10vh"></div>
        <main class="d-flex align-items-center" style="height: 60vh">
                <div class="container">
                    <div class="row">
                        <div class="col-md-4 offset-md-4">
                            <div class="card">
                                <div class="primary-background card-header text-white" style="text-align: center; font-size:1.5rem;">Login</div>
                            <% if(message!=null) { %>
                            <div class="alert alert-danger" role="alert"> <%=message.getMessage()%></div>
                            <% session.removeAttribute("msg"); } %>
                                <div class="card-body">
                                        <form action="Login" method="POST">
                                            <div class="form-group">
                                              <label for="exampleInputEmail1">Email address</label>
                                              <input name="email" type="email" required="true" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
                                            </div>
                                            <div class="form-group">
                                              <label for="exampleInputPassword1">Password</label>
                                              <input name="password" type="password" required="true" class="form-control" id="exampleInputPassword1" placeholder="Password">
                                            </div>
                                            <div class="form-group">
                                                <small>Don't have an account?<a href="signup.jsp" target="_parent"> Register</a></small>
                                            </div>
                                            <button type="submit" class="btn">Submit</button>
                                        </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
        <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
        <script src="JS/loginjs.js"></script>
    </body>
</html>
