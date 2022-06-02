package com.co.indra.coinmarketcap.portafolio.restPortfolio;

public class ModelResp {
    private String userId;
    private String name;
    private String mail;
    private Long idMembership;

    public ModelResp() {
    }

    public ModelResp(String userId, String name, String mail, Long idMembership) {
        this.userId = userId;
        this.name = name;
        this.mail = mail;
        this.idMembership = idMembership;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
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

    public Long getIdMembership() {
        return idMembership;
    }

    public void setIdMembership(Long idMembership) {
        this.idMembership = idMembership;
    }
}