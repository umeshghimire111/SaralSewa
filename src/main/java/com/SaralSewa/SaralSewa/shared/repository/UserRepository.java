package com.SaralSewa.SaralSewa.shared.repository;

import com.SaralSewa.SaralSewa.shared.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    User findByEmail(String email);
    Optional<User> findByEmailAndIsDeletedFalse(String email);

    User findByPhone(String phone);

    Optional<User> findByPhoneAndIsDeletedFalse(String phone);

    boolean existsByEmail(String email);

    boolean existsByEmailAndIsDeletedFalse(String email);

    boolean existsByPhone(String phone);

    boolean existsByPhoneAndIsDeletedFalse(String phone);


    List<User> findByRoleCode(String roleCode);

    List<User> findByRoleCodeAndIsActiveTrue(String roleCode);

    List<User> findByStatusCode(String statusCode);

    List<User> findByStatusCodeAndIsActiveTrue(String statusCode);

    List<User> findByFirstNameContainingIgnoreCase(String firstName);

    List<User> findByLastNameContainingIgnoreCase(String lastName);

    @Query("SELECT u FROM User u WHERE LOWER(CONCAT(u.firstName, ' ', u.lastName)) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<User> searchByName(@Param("name") String name);


    List<User> findByIsActiveTrue();

    List<User> findByIsActiveTrueAndIsDeletedFalse();

    List<User> findByIsDeletedTrue();


    @Query("SELECT u FROM User u WHERE u.role.code = :roleCode AND u.isActive = true AND u.isDeleted = false")
    List<User> findActiveUsersByRole(@Param("roleCode") String roleCode);

    @Query("SELECT COUNT(u) FROM User u WHERE u.isActive = true AND u.isDeleted = false")
    long countActiveUsers();

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.lastLoggedInTime = :time WHERE u.email = :email")
    void updateLastLoggedInTime(@Param("email") String email, @Param("time") LocalDateTime time);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.wrongPasswordAttemptCount = :count WHERE u.email = :email")
    void updateWrongPasswordAttemptCount(@Param("email") String email, @Param("count") Integer count);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.isActive = false WHERE u.lastLoggedInTime < :dateTime")
    int deactivateInactiveUsers(@Param("dateTime") LocalDateTime dateTime);


    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.isDeleted = true, u.deletedAt = CURRENT_TIMESTAMP WHERE u.id = :id")
    void softDeleteById(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.isDeleted = false, u.deletedAt = null WHERE u.id = :id")
    void restoreById(@Param("id") Integer id);
}