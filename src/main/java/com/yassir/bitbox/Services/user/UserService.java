package com.yassir.bitbox.Services.user;

import com.yassir.bitbox.dto.user.UserDTO;


public interface UserService {
    void save(UserDTO userDTO);
    void delete(String username);
    boolean isValid(UserDTO userDTO);
    UserDTO getUser(String username);
}
