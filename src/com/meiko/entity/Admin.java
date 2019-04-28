package com.meiko.entity;

/**
 * 1.实体设计javabean模式
 * @author Meiko on 2019/4/28 11:31
 */
public class Admin {
    private int id;
    private String userName;
    private String pwd;

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
