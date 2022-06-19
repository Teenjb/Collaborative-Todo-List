package com.collaborative.todoapp.model;

/**
 * This model is used to represent a user record from backend
 */
public class User {
    private int userid;
    private String username;
    private String password;

    /**
     * this method is used to return username
     * @return username
     */
    public String getUsername() {
        return username;
    }
}
