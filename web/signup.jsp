<%-- 
    Document   : signup
    Created on : 12-Feb-2024, 12:49:56 pm
    Author     : Prasant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <main class="d-flex align-items-center p-5" style="height: 80vh">
            <div class="container">
                <div class="row">
                    <div class="col-md-5 offset-md-4">
                        <div class="card">
                            <div class="primary-background card-header text-white" style="text-align: center; font-size:1.5rem;">
                                Register
                            </div>
                            <div class="card-body">
                                <form id="reg-form" action="Registration" method="POST">
                                        <div class="form-group">
                                            <label for="signupName">Name</label>
                                            <input name="name" required="true" type="text" class="form-control" id="signupName" placeholder="Full Name">
                                        </div>
                                        <div class="form-group">
                                          <label for="exampleInputEmail1">Email address</label>
                                          <input  name="email" required="true" type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
                                        </div>
                                        <div class="form-group">
                                          <label for="exampleInputPassword1">Password</label>
                                          <input  name="password" required="true" type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                                        </div>
                                        <div class="form-group">
                                            <small>Already a member?<a href="login.jsp" target="_parent"> Login in</a></small>
                                        </div>
                                        <div class="container text-center" id="submit-data-spin" style="display: none">
                                            <i class="fa fa-refresh fa-spin fa-1x" aria-hidden="true"></i>
                                            <span>Please wait...</span>
                                        </div>
                                        <button id="submit-button" type="submit" class="btn">Submit</button>
                                    </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <!-- Scripts -->
    
    <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
    <script src="JS/signupJS.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    
    </body>
</html>
