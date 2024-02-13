package com.techtitans.smartbudget.security.dto;

public class Auth {
    private TokenOld tokenOld = new TokenOld();

    public TokenOld getToken() {
        return tokenOld;
    }

    public void setToken(TokenOld tokenOld) {
        this.tokenOld = tokenOld;
    }
}
