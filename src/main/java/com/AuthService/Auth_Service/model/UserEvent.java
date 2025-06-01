package com.AuthService.Auth_Service.model;

import java.io.Serializable;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEvent implements Serializable {
    private String userId;
    // private String email;
    private String eventType; // SIGNUP or LOGIN
    private Instant timestamp;
}
