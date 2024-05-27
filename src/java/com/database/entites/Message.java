package com.database.entites;


public class Message {
    private String message;
    private String type;

    public Message(String message, String error) {
        this.message = message;
        this.type = error;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
