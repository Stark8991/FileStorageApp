/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.helper.PasswordHash;
import com.database.entites.User;
import com.database.connections.ConnectionProvider;
import com.database.dao.UserData;
import java.sql.Connection;
import com.database.entites.Message;
import com.google.gson.Gson;
import jakarta.servlet.annotation.MultipartConfig;
import java.io.File;

@MultipartConfig
public class Registration extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String hashPassword = PasswordHash.hashPassword(password);
            
            User userData = new User(name,email,hashPassword);
            Connection con = ConnectionProvider.getConnection();
            UserData data = new UserData(con);
            Message success = data.saveData(userData);
            User userbyEmail = data.getUserByEmail(email);
            String id =Integer.toString(userbyEmail.getUser_id());
            String uploadPath = getServletContext().getRealPath("") + File.separator+"user_files"+File.separator+id;
            
            System.out.println("User Created:- "+ uploadPath);
            
            File file = new File(uploadPath);
            file.mkdir();
            // Convert the success object to JSON
            String json = new Gson().toJson(success);
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
