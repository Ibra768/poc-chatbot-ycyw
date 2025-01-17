package com.ycyw.chatbot.repository;

import com.ycyw.chatbot.model.DBUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DBUserRepository extends JpaRepository<DBUser, Long> {
    public Optional<DBUser> findByEmail(String email);
}