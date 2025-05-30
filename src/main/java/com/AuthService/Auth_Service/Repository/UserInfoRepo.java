package com.AuthService.Auth_Service.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.AuthService.Auth_Service.Entities.Userinfo;

@Repository
public interface UserInfoRepo extends CrudRepository<Userinfo, String> {
    public Userinfo findByUsername(String username);

}