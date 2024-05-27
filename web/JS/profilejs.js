/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


$('#dropdownMenuButton').click(function(){
   $('.dropdown-menu').toggleClass('show'); 
});

$('#logout').click(function() {
        $.ajax({
            type: 'GET',
            url: 'LogoutProfile',
            success: function(response) {

                console.log('Request sent successfully');
                window.location = "login.jsp";
            },
            error: function(xhr, status, error) {
                console.error('Error:', error);
            }
        });
    });
    $(document).ready(function() {
        $("#dropdownMenuButton").on("click", function(event) {
            event.stopPropagation(); // Prevent propagation to other dropdowns
            $(".dropdown-menu").removeClass("show"); // Close other dropdowns if open
            $(this).next(".dropdown-menu").toggleClass("show");
        });

        // Close the dropdown when clicking elsewhere on the document
        $(document).on("click", function() {
            $(".dropdown-menu").removeClass("show");
        });
    });
    

// function to handle file-upload

$(document).ready(function(){
    $("#upload-btn").click(function(){
        $("#file-input").click();
    });
    $("#file-input").change(function(){
       var file = this.files[0];
       console.log(file);
        var confirmed = confirm("Want to upload the file?");

        // Check the user's choice
        if (confirmed) {
          // If the user clicks OK, perform some function
          uploadFile(file);
        } else {
          // If the user clicks Cancel, perform some other function
          cancelUpload();
        }
    });
});
    
function uploadFile(file) {
    // Perform upload function here
    $("#upload-btn").hide();
    $("#submit-data-spin").show();

    // Create FormData object to send file data
    var formData = new FormData();
    formData.append("file", file);
    
    var size = file.size;
    var sizeUnit;
    
    var KB = size / 1024;
    var MB = KB /1024;
    
    if(MB<1){
        size = parseFloat(KB.toFixed(2));
        sizeUnit = "KB";
    }
    else {
        size =parseFloat(MB.toFixed(2));
        sizeUnit = "MB";
    }
    formData.append("size",size);
    formData.append("sizeUnit",sizeUnit);
    formData.append("name",file.name);

    $.ajax({
        url: "SaveFile", // Your servlet URL
        type: "POST",
        data: formData,
        enctype: 'multipart/form-data', // Ensure proper encoding
        processData: false, // Prevent jQuery from processing the data
        contentType: false, // Set content type to false
        dataType:"json",
        success: function (data, textStatus, jqXHR) {
            // Handle success
                $("#submit-data-spin").hide();
                $("#upload-btn").show();
                
                var msg = data.message;
                var msgType = data.type;
                var date = getDate();
                console.log(date);
                if(msgType==="success"){
                    alert("File has been Uploaded!");
                    addfileName(file.name);
                    addUploadDate(date,file.name);
                    addFileSize(size,sizeUnit,file.name);
                    addAction(file.name);
                }
                else{
                    alert(msg);
                }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            // Handle error
            console.error("Error uploading file:", errorThrown);
        }
    });
}


// Function to perform when the user clicks Cancel
function cancelUpload() {
  // Perform cancel function here
  alert("Upload cancelled.");
}


function getDate(){
    let currentDate = new Date();
    let year = currentDate.getFullYear();
    let month = ('0' + (currentDate.getMonth() + 1)).slice(-2); // Months are zero-indexed
    let day = ('0' + currentDate.getDate()).slice(-2);
    let formattedDate = day + '/' + month + '/' + year;
    
    return formattedDate;
}

function addfileName(name){
    
    var child = $("<div></div>");
    
    var paragraph = $("<p></p>");
    paragraph.text(name);
    child.addClass("files border-top");
    child.attr("id", name);
    child.append(paragraph);
    $("#fileName").append(child);
}

function addUploadDate(date,name){
    var child = $("<div></div>");
    var paragraph = $("<p></p>");
    paragraph.text(date);
    child.addClass("files border-top");
    child.attr("id",name);
    child.append(paragraph);
    $("#uploadDate").append(child);    
}

function addFileSize(fileSize, sizeUnit,name){
    var child = $("<div></div>");
    
    var paragraph = $("<p></p>");
    paragraph.text(fileSize+""+sizeUnit);
    child.addClass("files border-top");
    child.attr("id",name);
    child.append(paragraph);
    $("#fileSize").append(child);    
    
}

function addAction(name){
    var child = $("<div></div>");
    child.addClass("files actions dropdown show");
    var button = $("<button></button>");
    button.addClass("actions-button btn btn-secondary dropdown-toggle download_button");
    button.attr("type","button");
    button.attr("id","dropdownMenuButton");
    button.attr("id",name);
    button.attr("data-toggle","dropdown");
    button.attr("aria-haspopup","true");
    button.attr("aria-expanded","false");
    button.html('<i class="fa fa-ellipsis-h"></i>');
    child.append(button);
    var smallChild = $("<div></div>");
    smallChild.addClass("dropdown-menu");
    smallChild.attr("aria-labelledby","dropdownMenuButton");
    // Create and append the download link
    var downloadLink = $("<button></button>")
        .addClass("dropdown-item")
        
        .text("Download");
    smallChild.append(downloadLink);

    // Create and append the delete link
    var deleteLink = $("<button></button>")
        .addClass("dropdown-item")
        
        .text("Delete");

    smallChild.append(deleteLink);
    child.append(smallChild);
    
    $("#options").append(child);
}


$(document).ready(function() {
    // Add click event handler to the button with class 'download_button'
    $(document).on('click','.download_button',function() {
        // Get the id and user_id attributes of the clicked button
        var fileId = $(this).attr('id');
        var userId = $(this).attr('user_id');

        // Log the fileId and userId for demonstration
//        console.log("File ID:", fileId);
//        console.log("User ID:", userId);

        // Call the downloadFileFromServer function or any other action you want to perform
        
        downloadFileFromServer(fileId, userId);
    });
});




function downloadFileFromServer(fileId, userId){
        console.log("File ID:", fileId);
        console.log("User ID:", userId);
        
        var formData = new FormData();
        formData.append("fileId",fileId);
        formData.append("userId", userId);
        
        var path = 'DownloadFile?fileId=' + fileId + '&' + 'userId=' + userId;

        window.location.href = path;


        
         
}


function deleteFile(fileId, userId){
    
}







// No longer using this View file feature
//$(document).ready(function() {
//    $('.view_button').click(function() {
//        var fileId = $(this).attr('id');
//        var user_id = $(this).attr('user_id');
//
//        // Make an AJAX request to the servlet
//        $.ajax({
//            type: 'POST',
//            url: 'Viewfile', // URL of your servlet
//            data: { fileId: fileId, user_id: user_id }, // Pass any data you need to the servlet
//            success: function(response) {
//                // Handle the response here
//                if (response && response.content && response.contentType) {
//                    // Check content type
//                    if (response.contentType.startsWith('image')) {
//                        // Display image in new window
//                        window.open('data:' + response.contentType + ';base64,' + response.content);
//                    } else if (response.contentType === 'application/pdf') {
//                        // Display PDF in new window
//                        window.open('data:application/pdf;base64,' + response.content);
//                    } else {
//                        console.error('Unsupported content type:', response.contentType);
//                    }
//                } else {
//                    console.error('Invalid response from servlet:', response);
//                }
//            },
//            error: function(xhr, status, error) {
//                // Handle errors
//                console.error('Error:', error);
//            }
//        });
//    });
//});

