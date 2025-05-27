package com.AuthService.Auth_Service.Request;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class RefreshTokenRequestDto {
    private String Token;

}
