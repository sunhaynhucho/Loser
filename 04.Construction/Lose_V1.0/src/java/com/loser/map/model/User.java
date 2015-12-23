package com.loser.map.model;

/**
 *
 * @author VinhNV
 */
public class User {

    private int userId;
    private String firstName;
    private String lastName;
    private String loginName;
    private String pass;
    private String email;
    private String joinedTime;

    public User() {
    }

    public User(int userId) {
        this.userId = userId;
    }

    public User(int userId, String firstName, String lastName, String loginName, String pass, String email, String joinedTime) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.loginName = loginName;
        this.pass = pass;
        this.email = email;
        this.joinedTime = joinedTime;
    }

    public User(int userId, String loginName, String pass) {
        this.userId = userId;
        this.loginName = loginName;
        this.pass = pass;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJoinedTime() {
        return joinedTime;
    }

    public void setJoinedTime(String joinedTime) {
        this.joinedTime = joinedTime;
    }

}
