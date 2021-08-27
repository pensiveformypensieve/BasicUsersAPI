package com.horcrux.ravenclaw.service.dto;

import java.io.Serializable;

public class UsersRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    String userId;
    String password;

    public UsersRequest(){}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UsersRequest{" +
                "userId=" + getUserId() +
                "password=" + getPassword() +
                "}";
    }
}
