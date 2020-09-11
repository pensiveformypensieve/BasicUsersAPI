package com.horcrux.ravenclaw.service.dto;

import java.io.Serializable;

public class UsersStatusResponse implements Serializable {

    private static final long serialVersionUID = 4L;

    String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UsersStatusResponse{" +
                "status=" + getStatus() +
                "}";
    }
}

