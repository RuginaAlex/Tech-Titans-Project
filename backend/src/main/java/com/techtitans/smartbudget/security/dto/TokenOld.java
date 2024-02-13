package com.techtitans.smartbudget.security.dto;

public class TokenOld {
    private String apiSecret;
    private long expirationMsec;

    public String getApiSecret() {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }

    public long getExpirationMsec() {
        return expirationMsec;
    }

    public void setExpirationMsec(long expirationMsec) {
        this.expirationMsec = expirationMsec;
    }
}
