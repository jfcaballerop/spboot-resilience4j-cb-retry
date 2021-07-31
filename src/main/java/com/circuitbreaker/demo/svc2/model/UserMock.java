package com.circuitbreaker.demo.svc2.model;

public class UserMock {
    private String user;
    private String msg;
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public UserMock() {
    }
    public UserMock(String user, String msg) {
        this.user = user;
        this.msg = msg;
    }

    
    
}
