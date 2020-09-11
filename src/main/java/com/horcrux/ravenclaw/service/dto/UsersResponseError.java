package com.horcrux.ravenclaw.service.dto;

import java.io.Serializable;
import java.util.List;

public class UsersResponseError implements Serializable {

    private static final long serialVersionUID = 3L;

    String result;
    String errorMessage;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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
                "errorMessage=" + getErrorMessage() +
                "}";
    }
}
