package com.AuthService.Auth_Service.Services.Impl;

import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.AuthService.Auth_Service.Entities.Userinfo;
import com.AuthService.Auth_Service.Repository.UserInfoRepo;
import com.AuthService.Auth_Service.Services.CoustomUserDetails;

import com.AuthService.Auth_Service.model.UserInfoDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Component
@Data
@AllArgsConstructor
public class UserInfoImpl implements UserDetailsService {

    @Autowired
    private final UserInfoRepo userInfoRepo;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    private static final Logger log = LoggerFactory.getLogger(UserInfoImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Entering in loadUserByUsername Method..");
        Userinfo user = userInfoRepo.findByUsername(username);
        if (user == null) {
            log.error("Username not found: " + username);
            throw new UsernameNotFoundException("could not found user..!!");
        }
        log.info("User Authenticated Successfully..!!!");
        return new CoustomUserDetails(user);
    }

    public Userinfo checkIfUserAlreadyExist(UserInfoDto userinfoDto) {
        return userInfoRepo.findByUsername(userinfoDto.getUsername());
    }

    public Boolean signUpUser(UserInfoDto UserInfoDto) {
        UserInfoDto.setPassword(passwordEncoder.encode(UserInfoDto.getPassword()));
        System.out.println("Password Set");
        if (Objects.nonNull(checkIfUserAlreadyExist(UserInfoDto))) {
            return false;
        }
        String userId = UUID.randomUUID().toString();
        userInfoRepo.save(new Userinfo(userId, UserInfoDto.getUsername(), UserInfoDto.getPassword(), new HashSet<>()));
        // pushEventToQueue
        return true;
    }

}
