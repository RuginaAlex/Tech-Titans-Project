package com.techtitans.smartbudget.security.constants;

import org.springframework.http.HttpHeaders;

public final class SecurityConstants {
    // JWT token defaults
    public static final String TOKEN_HEADER = HttpHeaders.AUTHORIZATION;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE_PARAM_NAME = "typ";
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_ISSUER = "-";
    public static final String TOKEN_AUDIENCE = "-";

    private SecurityConstants() {
        throw new IllegalStateException("Cannot create instance of static util class.");
    }
}
