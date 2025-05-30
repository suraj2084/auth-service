package com.AuthService.Auth_Service.Services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.AuthService.Auth_Service.Entities.UserRole;
import com.AuthService.Auth_Service.Entities.Userinfo;

public class CoustomUserDetails extends Userinfo implements UserDetails {

    private String userName;
    private String password;

    Collection<? extends GrantedAuthority> grantedAuthorities;

    public CoustomUserDetails(Userinfo byUserName) {
        this.userName = byUserName.getUsername();
        this.password = byUserName.getPassword();

        List<GrantedAuthority> auths = new ArrayList<>();

        for (UserRole roles : byUserName.getUserRoles()) {
            auths.add(new SimpleGrantedAuthority(roles.getName().toUpperCase()));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

}
