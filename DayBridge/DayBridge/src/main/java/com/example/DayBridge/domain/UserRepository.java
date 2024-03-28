package com.example.DayBridge.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    boolean existByUserID(String userID);
    boolean existByNickName(String nickName);
    Optional<Users> findByUserID(String userID);
}
