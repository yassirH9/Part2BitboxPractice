package com.yassir.bitbox.repositories;

import com.yassir.bitbox.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);
}
