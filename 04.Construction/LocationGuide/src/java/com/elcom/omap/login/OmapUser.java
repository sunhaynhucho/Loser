/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.omap.login;

/**
 *
 * @author Pham Quang Kiem
 */
public class OmapUser {
    private String userId;
    private String userName;
    private String userPass;
    private String fullName;
    private String email;
    private String ip;
    private String phoneNumber;
    private String address;
    private String checkIp;
    private int posType;
    private String posName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCheckIp() {
        return checkIp;
    }

    public void setCheckIp(String checkIp) {
        this.checkIp = checkIp;
    }

    
    @Override
    public boolean equals(Object obj) {
        OmapUser omapUser = (OmapUser) obj;
        return userName.equals(omapUser.getUserName());
    }

    public int getPosType() {
        return posType;
    }

    public void setPosType(int posType) {
        this.posType = posType;
    }

    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    

    
}
