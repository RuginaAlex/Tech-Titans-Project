package com.techtitans.smartbudget.security.authprovider;

import com.techtitans.smartbudget.security.dto.AppProperties;
import com.techtitans.smartbudget.repository.UsersRepository;
import com.techtitans.smartbudget.security.dto.UserPrincipal;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationProvider implements org.springframework.security.authentication.AuthenticationProvider {
    private final UsersRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AppProperties appProperties;

    public AuthenticationProvider(UsersRepository userRepository, PasswordEncoder passwordEncoder, AppProperties appProperties) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.appProperties = appProperties;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        return authenticate(email, password);
    }

    public Authentication authenticate(String email, String password) {
        return userRepository.findByEmail(email)
//                .filter(user -> Objects.equals(user.getPassword_hash(), password))
                .filter(user -> passwordEncoder.matches(password, user.getPassword_hash()))
                .map(UserPrincipal::new)
                .map(userDetails -> new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities()))
                .orElseThrow(() -> new UsernameNotFoundException("Invalid login"));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // replace with decision-based logic
        return true;
    }
}
