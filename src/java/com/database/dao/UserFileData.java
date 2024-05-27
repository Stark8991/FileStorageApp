/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.database.dao;

import com.database.entites.*;
import java.sql.*;

public class UserFileData {
    private final Connection con;
    
    public UserFileData(Connection con){
        this.con = con;
    }
    
    public Message saveFileData(UserFile file){
        Message msg = null;
        
        try{
            String query = "INSERT INTO files(user_id,file_name,file_size) VALUES(?,?,?)";
            PreparedStatement smt = con.prepareStatement(query);
            smt.setInt(1,file.getUser_id());
            smt.setString(2, file.getFile_name());
            smt.setString(3, file.getFile_size());
            smt.executeUpdate();
            msg = new Message("File uploaded","success");
        }catch(SQLException e){
            msg = new Message(e.getMessage(),"error");
            e.printStackTrace();
        }catch(Exception e){
            msg = new Message("Some error occuerd! try again!","error");
            e.printStackTrace();
        }
        return msg;
    }
    public ResultSet getFiles(int id){
        ResultSet files = null;
        
        try{
            
            String query = "SELECT * FROM files WHERE user_id=?";
            PreparedStatement psmt = con.prepareStatement(query);
            psmt.setInt(1, id);
            files = psmt.executeQuery();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return files;
    }
       
}
