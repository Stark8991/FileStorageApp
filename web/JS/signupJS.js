/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


/* global swal */

$(document).ready(function(){
    console.log("Loaded....");
    
    $("#reg-form").on("submit",function(event){
       event.preventDefault();
       let form = new FormData(this);
       $("#submit-button").hide();
       $("#submit-data-spin").show();
        console.log(form);
       // Send data to Register servlet
       $.ajax({
          url:"Registration",
          type:"POST",
          data: form,
//          dataType:"json",
          success: function(data, textStatus, jqXHR){

              $("#submit-button").show();
              $("#submit-data-spin").hide();
              var message = data.message;
              var type = data.type;
              if(type==="success"){
                  swal( "Great!" ,  message ,  type).then((value)=>{
                        if (value === null || value === false) {
                            document.getElementById("reg-form").reset();
                        }
                        else{
                            window.location = "login.jsp";
                        }
                  });
              }
              else{
                  swal("Oops",message, type).then(()=>{
                     document.getElementById("reg-form").reset(); 
                  });
              }
          },
          error: function(jqXHR, textStatus, errorThrown){
              $("#submit-button").show();
              $("#submit-data-spin").hide();
              swal( "Oops!" ,  "Something went wrong!" ,  "error");
          },
          processData:false,
          contentType:false
       });
    });
});