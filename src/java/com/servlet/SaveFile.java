package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.io.*;
import com.database.entites.UserFile;
import com.database.dao.UserFileData;
import com.database.entites.User;
import java.sql.Connection;
import com.database.connections.ConnectionProvider;
import com.database.entites.Message;
import com.google.gson.Gson;

@MultipartConfig
public class SaveFile extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            Part filePart=request.getPart("file");
            String size = request.getParameter("size")+" "+request.getParameter("sizeUnit");
            String fileName = request.getParameter("name");
            User user =(User)request.getSession().getAttribute("currentUser");
            int user_id = (int)user.getUser_id();
            UserFile fileInfo = new UserFile((int)user.getUser_id(),fileName, size);
            
            // Getting database connection

            Connection con = ConnectionProvider.getConnection();
            
            UserFileData userFiledata = new UserFileData(con);
            // Get filename
            String uploadPath = getServletContext().getRealPath("") + File.separator + "user_files" + File.separator +user_id;
            System.out.println(uploadPath);
            // Get input stream for the file
            InputStream fileContent = filePart.getInputStream();
            File outputFile = new File(uploadPath,fileName);
            Message msg = userFiledata.saveFileData(fileInfo);
//            System.out.println()
            if(msg.getType().equals("success")){
                byte[] buffer = new byte[4096]; // Adjust buffer size as needed

                OutputStream outputStream = new FileOutputStream(outputFile);
                int i=1;
                int bytesRead;
                while ((bytesRead = fileContent.read(buffer)) != -1) {
                    i+=1;
                    outputStream.write(buffer, 0, bytesRead);
                }
//                System.out.println("Total:-"+ i);
                fileContent.close();
                outputStream.flush();
                outputStream.close();
            }
            String json = new Gson().toJson(msg);
            out.write(json);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
