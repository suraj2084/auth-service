package com.AuthService.Auth_Service.Services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AuthService.Auth_Service.Entities.RefreshToken;
import com.AuthService.Auth_Service.Entities.Userinfo;
import com.AuthService.Auth_Service.Event.UserInfoproducerEvent;
import com.AuthService.Auth_Service.Repository.TokenRefreshRepo;
import com.AuthService.Auth_Service.Repository.UserInfoRepo;
import com.AuthService.Auth_Service.model.UserEvent;

@Service
public class RefreshTokenService {
    @Autowired
    TokenRefreshRepo refreshTokenService;

    @Autowired
    UserInfoRepo userInfoRepo;
    @Autowired
    UserInfoproducerEvent userInfoproducerEvent;

    public RefreshToken createRefreshToken(String userName) {
        Userinfo userinfoExtract = userInfoRepo.findByUsername(userName);
        RefreshToken refreshToken = RefreshToken.builder()
                .userinfo(userinfoExtract)
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(60000))
                .build();
        UserEvent event = UserEvent.builder()
                .userId(String.valueOf(refreshToken.getTokenid()))
                .eventType("LOGIN")
                .timestamp(Instant.now())
                .build();
        userInfoproducerEvent.sendUserEventToKafka(event);
        System.out.println("EventSend For Login");
        return refreshTokenService.save(refreshToken);

    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenService.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenService.delete(token);
            throw new RuntimeErrorException(null,
                    token.getToken() + "RefreshToken is expired. please make a new login..");
        }
        return token;
    }

    public RefreshToken getOrCreateRefreshToken(String username) {
        return refreshTokenService.findByUserinfo_Username(username)
                .orElseGet(() -> createRefreshToken(username));
    }
}
