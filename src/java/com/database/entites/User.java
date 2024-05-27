/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.database.entites;

import java.sql.Timestamp;

public class User {
    private int user_id;
    private String user_name;
    private String user_email;
    private String password;
    private Timestamp registration_date;
    
    
    //Constructor

    public User(int user_id, String user_name, String user_email, String password, Timestamp registration_date) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_email = user_email;
        this.password = password;
        this.registration_date = registration_date;
    }

    public User(int user_id, String user_name, String user_email, String password) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_email = user_email;
        this.password = password;
    }

    public User(String user_name, String user_email) {
        this.user_name = user_name;
        this.user_email = user_email;
    }

    public User(String user_name, String user_email, String password) {
        this.user_name = user_name;
        this.user_email = user_email;
        this.password = password;
    }
    
    public User(){}
    
   
    //Getter and Setter

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(Timestamp registration_date) {
        this.registration_date = registration_date;
    }
    
    
    
    
    
}
