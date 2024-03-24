//package com.techtitans.smartbudget.security.filter;
//
//import com.techtitans.smartbudget.repository.UsersRepository;
//import com.techtitans.smartbudget.security.config.properties.AppProperties;
//import com.techtitans.smartbudget.security.dto.Token;
//import com.techtitans.smartbudget.security.dto.UserPrincipal;
//import com.techtitans.smartbudget.service.TokenService;
//import com.techtitans.smartbudget.service.UsersService;
//import io.micrometer.common.util.StringUtils;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.Optional;
//
//import static com.techtitans.smartbudget.security.constants.SecurityConstants.TOKEN_HEADER;
//import static com.techtitans.smartbudget.security.constants.SecurityConstants.TOKEN_PREFIX;
//
//@Service
//public class JwtTokenFilter extends OncePerRequestFilter {
//
//    private AppProperties appProperties;
//    private TokenService tokenService;
//    private UsersService usersService;
//
//    public JwtTokenFilter(AppProperties appProperties, TokenService tokenService, UsersService usersService) {
//        this.appProperties = appProperties;
//        this.tokenService = tokenService;
//        this.usersService = usersService;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String jwt = getJwtFromHeader(request);
//        UsernamePasswordAuthenticationToken authentication = getAuthentication(jwt);
//
//        if (authentication != null) {
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }
//
//        filterChain.doFilter(request, response);
//    }
//
//    private UsernamePasswordAuthenticationToken getAuthentication(String jwt) {
//        if (StringUtils.isNotEmpty(jwt)) {
//            String tokenSecret = appProperties.getSecurity().getAuth().getToken().getApiSecret();
//            Token token = tokenService.decode(jwt, tokenSecret);
//            if (!token.isExpired()) {
//                UserPrincipal userPrincipal = usersService.generateUserPrincipal(token.getUserId());
//                if (userPrincipal != null) {
//                    return new UsernamePasswordAuthenticationToken(userPrincipal, userPrincipal.getPassword(), userPrincipal.getAuthorities());
//                }
//            }
//        }
//
//        return null;
//    }
//
//    private String getJwtFromHeader(HttpServletRequest request) {
//        return Optional.ofNullable(request.getHeader(TOKEN_HEADER))
//                .filter(this::isValidBearerToken)
//                .map(this::extractBearerFromToken)
//                .orElse(null);
//    }
//
//    private boolean isValidBearerToken(String token) {
//        return StringUtils.isNotBlank(token) && token.startsWith(TOKEN_PREFIX);
//    }
//
//    private String extractBearerFromToken(String bearerToken) {
//        return bearerToken.substring(TOKEN_PREFIX.length());
//    }
//}
