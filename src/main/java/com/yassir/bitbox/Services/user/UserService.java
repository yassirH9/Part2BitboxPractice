package com.yassir.bitbox.Services.user;

import com.yassir.bitbox.dto.user.UserDTO;


public interface UserService {
    void save(UserDTO user);
    void delete(String username);
    boolean isValid(UserDTO user);
    UserDTO getUser(String username);
}
