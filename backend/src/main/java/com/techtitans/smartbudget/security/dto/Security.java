package com.techtitans.smartbudget.security.dto;

public class Security {
    private Auth auth = new Auth();
    private Cors cors = new Cors();
    private Encryption encryption = new Encryption();

    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    public Cors getCors() {
        return cors;
    }

    public void setCors(Cors cors) {
        this.cors = cors;
    }

    public Encryption getEncryption() {
        return encryption;
    }

    public void setEncryption(Encryption encryption) {
        this.encryption = encryption;
    }
}
