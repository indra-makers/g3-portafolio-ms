package com.co.indra.coinmarketcap.portafolio.validation;

import java.io.Serializable;

public class UserModel implements Serializable {

    private int userId;

    private String name;

    private String mail;

    private int idMembership;

    public UserModel() {
    }

    public UserModel(int userId, String name, String mail, int idMembership) {
        this.userId = userId;
        this.name = name;
        this.mail = mail;
        this.idMembership = idMembership;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getIdMembership() {
        return idMembership;
    }

    public void setIdMembership(int idMembership) {
        this.idMembership = idMembership;
    }
}
