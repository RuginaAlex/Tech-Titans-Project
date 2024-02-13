package com.techtitans.smartbudget.security.dto;

import com.techtitans.smartbudget.model.Privilege;
import com.techtitans.smartbudget.model.Users;
import com.techtitans.smartbudget.model.enums.RoleName;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserPrincipal extends Users implements UserDetails {
    private Collection<SimpleGrantedAuthority> authorities;
    private RoleName roleName;

    public UserPrincipal() {
    }

    public UserPrincipal(Users user) {
        this.setUser_id(user.getUser_id());
        this.setUsername(user.getUsername());
        this.setPassword_hash(user.getPassword_hash());
        this.roleName = user.getRole().getName();
        this.authorities = new ArrayList<>();
        for (Privilege privilege : user.getRole().getPrivileges()) {
            this.authorities.add(new SimpleGrantedAuthority(privilege.getName().name()));
        }
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    public RoleName getRoleName() {
        return roleName;
    }
}