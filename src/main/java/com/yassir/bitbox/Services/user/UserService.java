package com.yassir.bitbox.Services.user;

import com.yassir.bitbox.dto.user.UserDTO;
import com.yassir.bitbox.enums.UserPrivilegesEnum;


public interface UserService {
    void save(UserDTO userDTO);
    void delete(String username);
    UserDTO getUser(String username);
    void changePrivilegesUser(String username, UserPrivilegesEnum privilegesEnum);
}
