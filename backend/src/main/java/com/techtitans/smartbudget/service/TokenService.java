package com.techtitans.smartbudget.service;

import com.techtitans.smartbudget.security.config.JwtClaim;
import com.techtitans.smartbudget.security.dto.Token;
import com.techtitans.smartbudget.security.dto.UserPrincipal;
import com.techtitans.smartbudget.util.ReflectionUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

import static com.techtitans.smartbudget.security.constants.SecurityConstants.*;
import static java.nio.charset.StandardCharsets.UTF_8;

@Service
public class TokenService {
    /**
     * Generate a JWT based on user data, with the validity set for 1 month.
     * * @param user the user whose credentials are to be used in generating the token
     *
     * @return a JWT for the specific user
     */
    public Token generate(UserPrincipal user, String tokenSecret) {
        var expirationTime = LocalDateTime.now().plusMonths(1);
        return encode(user, expirationTime, tokenSecret);
    }

    public Token generateRefresh(Token token, String tokenSecret) {
        var expirationTime = LocalDateTime.now().plusMonths(3);
        return encode(token.getUserId(), expirationTime, tokenSecret);
    }

    /**
     * Regenerates the JWT, with the validity set for 1 month.
     * * @param token the existent token
     *
     * @return a new JWT for the specific user
     */
    public Token refresh(Token token, String tokenSecret) {
        var expirationTime = LocalDateTime.now().plusMonths(1);
        return encode(token.getUserId(), expirationTime, tokenSecret);
    }

    /**
     * Generate a JWT based on user data, with the validity specified by the {@code expirationTime} param.     *
     *
     * @param user the user whose credentials are to be used in generating the token     * @param expirationTime the lifetime of the token
     * @return a JWT for the specific user
     */
    private Token encode(UserPrincipal user, LocalDateTime expirationTime, String tokenSecret) {
        var token = new Token();
        var lifetime = ZonedDateTime.of(expirationTime, ZoneId.systemDefault());
        var expiration = Date.from(lifetime.toInstant());
        var claims = getClaims(token, user);
        claims.put("role", user.getRoleName());
        String jwt = Jwts.builder()
                .setClaims(claims).signWith(Keys.hmacShaKeyFor(tokenSecret.getBytes(UTF_8)), SignatureAlgorithm.HS512)
                .setHeaderParam(TOKEN_TYPE_PARAM_NAME, TOKEN_TYPE).setExpiration(expiration)
                .compact();
        token.setAccessToken(jwt);
        return token;
    }

    private HashMap<String, Object> getClaims(Token token, UserPrincipal user) {
        token.setUserId(user.getUser_id());
        return ReflectionUtils.getMapFromObject(token, JwtClaim.class);
    }

    /**
     * Generate a JWT based on user data, with the validity specified by the {@code expirationTime} param.
     * * @param userId         the user id of the jwt owner
     *
     * @param expirationTime the lifetime of the token     * @return a JWT for the specific user
     */
    private Token encode(Integer userId, LocalDateTime expirationTime, String tokenSecret) {
        return encode((UserPrincipal) null, expirationTime, tokenSecret);
    }

    /**
     * @param jwt the jason web token
     * @return the Jws instance that reflects the specified compact Claims JWS string.
     * @throws io.jsonwebtoken.UnsupportedJwtException     if the {@code jwt} argument does not contain jwt claims
     * @throws io.jsonwebtoken.MalformedJwtException       if the {@code jwt} string is not a valid JWT
     * @throws io.jsonwebtoken.security.SignatureException if the {@code claimsJws} JWS signature validation fails
     * @throws io.jsonwebtoken.ExpiredJwtException         if the specified JWT is a Claims JWT and the Claims has an expiration time                                                       before the time this method is invoked.
     * @throws IllegalArgumentException                    if the {@code claimsJws} string is {@code null} or empty or only whitespace
     */
    public Token decode(String jwt, String tokenSecret) {
        var tokenClaims = Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(jwt.replace(TOKEN_PREFIX, ""));
        var userId = Optional.ofNullable(tokenClaims.getBody().getSubject())
                .map(Integer::valueOf)
                .orElse(null);
        var expiration = tokenClaims.getBody().getExpiration();
        return new Token(userId, expiration, jwt);
    }
}