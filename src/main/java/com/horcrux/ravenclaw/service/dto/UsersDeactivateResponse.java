package com.horcrux.ravenclaw.service.dto;

import java.io.Serializable;

public class UsersDeactivateResponse implements Serializable {

    private static final long serialVersionUID = 4L;

    String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "UsersDeactivateResponse{" +
                "result=" + getResult() +
                "}";
    }
}
