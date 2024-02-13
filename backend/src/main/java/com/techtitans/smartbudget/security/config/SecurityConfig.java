package com.techtitans.smartbudget.security.config;

import com.techtitans.smartbudget.security.dto.AppProperties;
import com.techtitans.smartbudget.security.dto.Security;
import com.techtitans.smartbudget.security.dto.Cors;
import com.techtitans.smartbudget.repository.UsersRepository;
import com.techtitans.smartbudget.security.authprovider.AuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;
import java.util.Optional;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final AppProperties appProperties;
    private final UsersRepository userRepository;

    @Autowired
    public SecurityConfig(AppProperties appProperties, UsersRepository userRepository) {
        this.appProperties = appProperties;
        this.userRepository = userRepository;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .cors().and()
                .httpBasic().and()
                .formLogin().disable()
                .logout().disable()
                .sessionManagement().disable()
                .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
                .authenticationManager(authenticationManager(http))
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        Optional.ofNullable(appProperties.getSecurity())
                .map(Security::getCors)
                .map(Cors::getHosts)
                .ifPresent(configuration::setAllowedOriginPatterns);
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {

        var authManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authManagerBuilder.parentAuthenticationManager(null);
        authManagerBuilder.authenticationProvider(new AuthenticationProvider(userRepository, passwordEncoder(), appProperties));
        return authManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}