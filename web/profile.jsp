<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.database.entites.User"%>
<%@page import="com.database.dao.UserFileData"%>
<%@page import="com.database.connections.ConnectionProvider" %>
<%@page import="java.sql.*" %>
<%@page import="java.text.SimpleDateFormat" %>
<%
    User user = (User)session.getAttribute("currentUser");
    if(user==null){
        System.out.println(user);
        response.sendRedirect("login.jsp");
    }
    Connection con = ConnectionProvider.getConnection();
    UserFileData fileCon = new UserFileData(con);
    ResultSet files =fileCon.getFiles((int)user.getUser_id());
    int count = 0;
    int len=0;
    if(files!=null)count=1;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date uploadedAt;
    
//    System.out.println("Date:-"+files.getDate("uploaded_at"))

     // Format the date into the desired format
    String formattedDate;
%>
<!DOCTYPE html>
<html>
    <head>
        <link href="css/profile.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>NoteVault</title>
    </head>
    <body>
        <!-- Navbar Starts here -->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="dropdown">
                <div class="circular-image" id="dropdownMenuButton">
                  <img src="web_images/profile_image.png" alt="profile" class="img-fluid">
                </div>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuButton" style="min-width: 350px;">
                  <div class="row">
                    <div class="col">
                      <p class="dropdown-item-text mb-1"><strong>Name:</strong> <% if(user!=null) { %> <%= user.getUser_name() %> <% } %></p>
                      <p class="dropdown-item-text mb-1"><strong>Email:</strong> <% if(user!=null) { %> <%= user.getUser_email() %> <% } %></p>
                    </div>
                  </div>
                  <div class="dropdown-divider"></div>
                  <div class="row">
                    <div class="col">
                        <div class="p-2 button-success">
                            <button id="logout" class="dropdown-item btn btn-block btn-success" type="button">Logout</button>
                        </div>
                    </div>
                    <div class="col">
                        <div class="p-2 button-danger">
                            <button class="dropdown-item btn btn-block btn-danger" type="button">Delete Account</button>
                        </div>
                    </div>
                  </div>
                </div>
            </div>
         
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <form class="form-inline mx-auto">
              <input class="form-control mr-sm-2" style="width: 300px;" type="search" placeholder="Search" aria-label="Search">
              <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
             <span class="navbar-brand">NoteVault</span> 
          </div>
        </nav>
        <!-- Navbar Ends here -->
        
        <!-- Main div starts here -->
        <main>
            <!-- container div -->
            <div class="container-main container col"style="border: 0px solid black;">
                <!-- add post -->
                <div id="AddPost"><!-- Add post div-1 -->
                    <button id="upload-btn" class=" actions-button"><i class="fa fa-upload" style="font-size:48px;color:black"></i></button>
                    <input type="file" id="file-input" style="display:none">
                    <div class="container text-center" id="submit-data-spin" style="display:none">
                    <i class="fa fa-refresh fa-spin fa-1x" aria-hidden="true"  style="font-size:38px"></i>
                    <span>Uploading...</span>
                    </div>  
                </div>
                <div class="container">
                    <div class="row" ">
                        <!-- File Name div -->
                        <div id="fileName" class="col-sm-6 col-md-3 mgl fileName">
                            <div id="divName" class=""><span>Name</span></div>
                            <!--<div class="files border-top"><p>bookdsfsdfsdafdsafdsafsd.pdf</p></div>-->
                                <% 
                                if(count==1) {
                                    // Reset the cursor to before the first row of the ResultSet

                                    while(files.next()) { 
                                %>
                            <div class="files border-top" id="<%=files.getString("file_name")%>">
                                <p><%= files.getString("file_name") %></p>
                            </div>
                                <% 
                                    }
                                } 
                                %>
                        </div>
                        <div class="dropdown-divider"></div>
                        <!-- Upload Date div -->
                        <div id="uploadDate" class="col-sm-2 col-md-1 mgl headingName">
                            <div id="divUpload" class="headingName"><span>Upload</span></div>
                            <!--<div class="files border-top"><p>12/05/2023</p></div>-->
                                <% if (count == 1) { %>
                                    <% files =fileCon.getFiles((int)user.getUser_id()); %>
                                    <% while (files.next()) {  len++; %>
                                        <div class="files border-top " id="<%= files.getString("file_name") %>">
                                            <p><%= dateFormat.format(files.getDate("uploaded_at")) %></p>
                                        </div>
                                    <% } %>
                                <% } %>
                        </div>
                        
                        <!-- File Size div -->
                        <div id="fileSize" class="col-sm-2 col-md-1 mgl headingName">
                            <div id="divSize" class="headingName"><span>Size</span></div>
                            <!--<div class="files border-top"><p>10MB</p></div>-->
                            <% if(count==1) { %>
                                <% files =fileCon.getFiles((int)user.getUser_id()); %>
                                <%
                                    while(files.next()) { %>
                                    <div class="files border-top fileSizediv" id="<%=files.getString("file_name")%>"><p style="font-size:14px"><%= files.getString("file_size") %></p></div>
                                <% } %>
                            <% } %>
                        </div>
                        
                        <!-- Options div -->
                        <div id="options" class="col-sm-2 col-md-1 mgl headingName">
                            <div id="divOptions" class="headingName"><span>Actions</span></div>
                            <% files =fileCon.getFiles((int)user.getUser_id());
                                while(len-->0 & files.next()){ 
                              %>
                            <div class="files actions dropdown show">
                                <button class="actions-button btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="fa fa-ellipsis-h"></i>
                                </button>
                              <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                    
                                    <button class="dropdown-item download_button" type="button" id="<%=files.getString("file_name")%>" user_id="<%=user.getUser_id()%>">Download</button>
                                    <button class="dropdown-item delete_button" type="button" id="<%=files.getString("file_name")%>" user_id="<%=user.getUser_id()%>">Delete</button>
                              </div>
                            </div>
                            <% } %>
                        </div>
                        
                        <% files.close(); %>
                        
                    </div>
                </div>
            </div>
        </main>
        <!-- Main div ends here -->


        <!-- JavaScript -->
        <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="JS/profilejs.js" type="text/javascript"></script>
    </body>
</html>
