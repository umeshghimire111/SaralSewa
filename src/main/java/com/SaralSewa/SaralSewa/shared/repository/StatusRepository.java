package com.SaralSewa.SaralSewa.shared.repository;


import com.SaralSewa.SaralSewa.shared.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Integer> {
    Status findByName(String code);
    Status findByCode(String code);


    boolean existsByName(String name);
}
