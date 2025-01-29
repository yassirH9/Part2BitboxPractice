package com.yassir.bitbox.repositories;

import com.yassir.bitbox.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String userName);
}
