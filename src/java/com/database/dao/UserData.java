package com.database.dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import com.database.entites.User;
import java.sql.*;
import com.database.entites.Message;
public class UserData {
    private final Connection con;
    
    public UserData(Connection con){
        this.con = con;
    }
    
    @SuppressWarnings("CallToPrintStackTrace")
    public Message saveData(User userData){
        Message saved=null;
        
        try{
            String query = "INSERT INTO user_data(user_name, user_email, user_password) VALUES (?, ?, ?)";
            PreparedStatement psmt = con.prepareStatement(query);
            psmt.setString(1, userData.getUser_name());
            psmt.setString(2, userData.getUser_email());
            psmt.setString(3, userData.getPassword());
            psmt.executeUpdate();
            saved=new Message("Registerd","success");
        }catch(SQLIntegrityConstraintViolationException e){
            saved = new Message("This Email is already registerd","error");
            System.out.println(e.getMessage());
           
        }
        catch(SQLException e){
            saved = new Message("Some error has occured while accessing the database! Please try again","error");
            e.printStackTrace();

        }
        
        
        return saved;
    }
    
    public User getUserByEmail(String email){
        User thereIsAUser = null;
        try{
            String query = "SELECT * FROM user_data WHERE user_email=?";
            PreparedStatement psmt = con.prepareStatement(query);
            psmt.setString(1, email);
            ResultSet result = psmt.executeQuery();
            if(result.next()){
                
                int user_id = result.getInt("user_id");
                String user_name = result.getString("user_name");
                String user_email = result.getString("user_email");
                String user_password = result.getString("user_password");
                Timestamp registration = result.getTimestamp("registration_date");
                thereIsAUser = new User(user_id,user_name,user_email,user_password,registration);
                return thereIsAUser;
                
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return thereIsAUser;
    }
    
}
