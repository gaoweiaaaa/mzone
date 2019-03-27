package com.song.entity;

import java.io.Serializable;

public class UserVO implements Serializable{
    private User user;
    private String responseCode;
    private String errorMsg;
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "user=" + user +
                ", responseCode='" + responseCode + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
