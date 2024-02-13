package com.techtitans.smartbudget.api.controller;

import com.techtitans.smartbudget.security.dto.AppProperties;
import com.techtitans.smartbudget.security.dto.Token;
import com.techtitans.smartbudget.security.dto.UserPrincipal;
import com.techtitans.smartbudget.service.TokenService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AuthController {
    private final AppProperties appProperties;
    private final TokenService tokenService;

    public AuthController(AppProperties appProperties, TokenService tokenService) {
        this.appProperties = appProperties;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public Token login(Authentication authentication) {
        var userPrincipal = (UserPrincipal) authentication.getPrincipal();
        var tokenSecret = appProperties.getSecurity().getAuth().getToken().getApiSecret();
        return tokenService.generate(userPrincipal, tokenSecret);
    }
}