package com.yassir.bitbox.repositories;

import com.yassir.bitbox.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findByuserName(String userName);
}
