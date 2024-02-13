package com.techtitans.smartbudget.security.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.techtitans.smartbudget.security.config.JwtClaim;

import java.util.Date;

@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class Token {
    @JwtClaim
    private Integer userId;
    private Date expiration;
    @JsonProperty
    private String accessToken;

    public Token() {
    }

    public Token(Integer userId, Date expiration, String accessToken) {
        this.userId = userId;
        this.expiration = expiration;
        this.accessToken = accessToken;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}