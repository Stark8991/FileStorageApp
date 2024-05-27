package com.servlet;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import com.database.entites.Message;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.helper.PasswordHash;
import com.database.entites.User;
import com.database.connections.ConnectionProvider;
import java.sql.Connection;
import com.database.dao.UserData;
import jakarta.servlet.http.HttpSession;
public class Login extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try{
              
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            Connection con = ConnectionProvider.getConnection();
            UserData user = new UserData(con);
            User userData = user.getUserByEmail(email);
            if(userData!=null){
                boolean isSamePassword = PasswordHash.verifyPassword(password, userData.getPassword());
                if(isSamePassword){
                    HttpSession session = request.getSession();
                    session.setAttribute("currentUser", userData);
                    response.sendRedirect("profile.jsp");
                }
                else{
                Message msg = new Message("Wrong Credentials","error");
                HttpSession session = request.getSession();
                session.setAttribute("msg", msg);
                response.sendRedirect("login.jsp");                    
                }
            }else{
            Message msg = new Message("Wrong Credentials","error");
            HttpSession session = request.getSession();
            session.setAttribute("msg", msg);
            response.sendRedirect("login.jsp");
            }
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
