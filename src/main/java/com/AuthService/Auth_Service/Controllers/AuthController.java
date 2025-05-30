package com.AuthService.Auth_Service.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AuthService.Auth_Service.Entities.RefreshToken;
import com.AuthService.Auth_Service.Response.JwtResponseDto;
import com.AuthService.Auth_Service.Services.JwtService;
import com.AuthService.Auth_Service.Services.RefreshTokenService;
import com.AuthService.Auth_Service.Services.Impl.UserInfoImpl;
import com.AuthService.Auth_Service.model.UserInfoDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth/v1")
public class AuthController {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private UserInfoImpl userDetailsService;

    @PostMapping("/signup")
    public ResponseEntity SignUp(@Valid @RequestBody UserInfoDto userInfoDto) {
        try {
            Boolean isSignUped = userDetailsService.signUpUser(userInfoDto);
            if (Boolean.FALSE.equals(isSignUped)) {
                return new ResponseEntity<>("Already Exist", HttpStatus.BAD_REQUEST);
            }
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(userInfoDto.getUsername());
            String jwtToken = jwtService.GenerateToken(userInfoDto.getUsername());
            return new ResponseEntity<>(
                    JwtResponseDto.builder().accessToken(jwtToken).token(refreshToken.getToken()).build(),
                    HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>("Exception in User Service", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
