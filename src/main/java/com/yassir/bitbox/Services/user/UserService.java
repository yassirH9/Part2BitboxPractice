package com.yassir.bitbox.Services.user;

import com.yassir.bitbox.dto.user.UserDTO;
import com.yassir.bitbox.enums.UserPrivilegesEnum;

import java.util.List;


public interface UserService {
    void save(UserDTO userDTO);
    void delete(Long userid);
    UserDTO getUser(String username);
    List<UserDTO> getUsers();
    void changePrivilegesUser(String username, UserPrivilegesEnum privilegesEnum);
}
