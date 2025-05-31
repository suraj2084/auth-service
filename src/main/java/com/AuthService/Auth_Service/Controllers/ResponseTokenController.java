package com.AuthService.Auth_Service.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AuthService.Auth_Service.Entities.RefreshToken;
import com.AuthService.Auth_Service.Entities.Userinfo;
import com.AuthService.Auth_Service.Request.RefreshTokenRequestDto;
import com.AuthService.Auth_Service.Response.JwtResponseDto;
import com.AuthService.Auth_Service.Services.JwtService;
import com.AuthService.Auth_Service.Services.RefreshTokenService;
import com.AuthService.Auth_Service.model.AuthRequestDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth/v1")
public class ResponseTokenController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity AuthenticateAndGetToken(@Valid @RequestBody AuthRequestDTO authRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
        System.out.println("authentication");
        if (authentication.isAuthenticated()) {
            // RefreshToken refreshToken =
            // refreshTokenService.createRefreshToken(authRequestDTO.getUsername());
            // System.out.println("Token Created:" + refreshToken.toString());
            return new ResponseEntity<>(JwtResponseDto.builder()
                    .accessToken(jwtService.GenerateToken(authRequestDTO.getUsername()))
                    .token(refreshTokenService.getOrCreateRefreshToken(authRequestDTO.getUsername()).getToken())
                    .build(), HttpStatus.OK);

        } else {
            return new ResponseEntity<>("Exception in User Service", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/refreshToken")
    public JwtResponseDto refreshToken(@RequestBody RefreshTokenRequestDto refreshTokenRequestDTO) {
        return refreshTokenService.findByToken(refreshTokenRequestDTO.getToken())
                .map(refreshToken -> {
                    RefreshToken refreshToken1 = refreshTokenService.verifyExpiration(refreshToken);
                    Userinfo userinfo = refreshToken1.getUserinfo();
                    String accessToken = jwtService.GenerateToken(userinfo.getUsername());

                    return JwtResponseDto.builder()
                            .accessToken(accessToken)
                            .token(refreshTokenRequestDTO.getToken())
                            .build();
                }).orElseThrow(() -> new RuntimeException("Refresh Token is not in DB..!!"));
    }
}
