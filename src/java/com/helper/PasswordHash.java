/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.helper;

import org.mindrot.jbcrypt.BCrypt;


public class PasswordHash {
    public static String hashPassword(String plainTextPassword) {
        // Generate a salt for the hash
        String salt = BCrypt.gensalt();

        // Hash the password with the salt
        String hashedPassword = BCrypt.hashpw(plainTextPassword, salt);

        return hashedPassword;
    }

    public static boolean verifyPassword(String plainTextPassword, String hashedPassword) {
        // Check if the provided plain-text password matches the hashed password
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
}
