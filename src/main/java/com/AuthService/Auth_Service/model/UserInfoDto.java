package com.AuthService.Auth_Service.model;

import com.AuthService.Auth_Service.Entities.Userinfo;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)

public class UserInfoDto extends Userinfo {

    private String first_name;
    private String last_name;
    private String email;
    private String Address;

}
