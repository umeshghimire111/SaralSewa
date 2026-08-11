package com.SaralSewa.SaralSewa.shared.repository;

import com.SaralSewa.SaralSewa.shared.entity.User;
import com.SaralSewa.SaralSewa.shared.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, Long> {
    Optional<UserToken> findByAccessToken(String accessToken);
    Optional<UserToken> findByRefreshToken(String refreshToken);
    Optional<UserToken> findByAccessTokenAndLoggedOutFalse(String token);
    List<UserToken> findByUserAndLoggedOutFalse(User user);
    boolean existsByRefreshTokenAndLoggedOutFalse(String refreshToken);
    boolean existsByAccessTokenAndLoggedOutFalse(String accessToken);
    List<UserToken> findAllByUserAndLoggedOutFalse(User user);
}
