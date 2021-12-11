package com.alayon.msbank.security.dto;

import java.io.Serializable;

public class AuthRequest implements Serializable {

    private String userName;
    private String password;

    public AuthRequest() { }

    public AuthRequest(String userName, String password) {
        this.setUserName(userName);
        this.setPassword(password);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

