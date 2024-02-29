package com.techtitans.smartbudget.security.config.properties;

public class Security {
    private Auth auth = new Auth();
    private Cors cors = new Cors();

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
}
