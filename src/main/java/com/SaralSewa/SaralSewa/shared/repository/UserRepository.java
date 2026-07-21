package com.SaralSewa.SaralSewa.shared.repository;

import com.SaralSewa.SaralSewa.shared.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface  UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    boolean existsByEmail(String email);
    User findByUserId(Integer id);

    @Modifying
    @Query("UPDATE User u SET u.lastLoggedInTime = :time WHERE u.email = :email")
    void updateLastLoggedInTime(@Param("email") String email, @Param("time") LocalDateTime time);

    @Modifying
    @Query("UPDATE User u SET u.wrongPasswordAttemptCount = :count WHERE u.email = :email")
    void updateWrongPasswordAttemptCount(@Param("email") String email, @Param("count") Integer count);


}
