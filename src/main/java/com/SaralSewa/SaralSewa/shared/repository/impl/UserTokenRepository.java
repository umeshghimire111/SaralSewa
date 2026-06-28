package com.SaralSewa.SaralSewa.shared.repository.impl;

import com.SaralSewa.SaralSewa.shared.entity.UserToken;
import com.SaralSewa.SaralSewa.shared.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
@EnableJpaRepositories
public interface UserTokenRepository extends JpaRepository<UserToken, Long> {
    Optional<UserToken> findByAccessToken(String accessToken);
    Optional<UserToken> findByRefreshToken(String refreshToken);
    Optional<UserToken> findByAccessTokenAndLoggedOutFalse(String token);
    List<UserToken> findByUserAndLoggedOutFalse(User user);
    boolean existsByRefreshTokenAndLoggedOutFalse(String refreshToken);
    boolean existsByAccessTokenAndLoggedOutFalse(String accessToken);
    List<UserToken> findAllByUserAndLoggedOutFalse(User user);
}
