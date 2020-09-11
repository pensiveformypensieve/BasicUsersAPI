package com.horcrux.ravenclaw.service.dto;

import java.io.Serializable;
import java.util.List;

public class UsersResponse implements Serializable {

    private static final long serialVersionUID = 2L;

    String result;
    List<String> roles;
    String errorMessage;

    public UsersResponse(){}

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "UsersResponse{" +
                "result=" + getResult() +
                "roles=" + getRoles() +
                "errorMessage=" + getErrorMessage() +
                "}";
    }
}
