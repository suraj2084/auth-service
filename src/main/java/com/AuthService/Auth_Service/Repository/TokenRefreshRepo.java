package com.AuthService.Auth_Service.Repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.AuthService.Auth_Service.Entities.RefreshToken;

@Repository
public interface TokenRefreshRepo extends CrudRepository<RefreshToken, Integer> {
    Optional<RefreshToken> findByToken(String token);

    Optional<RefreshToken> findByUserinfo_Username(String username);

}
