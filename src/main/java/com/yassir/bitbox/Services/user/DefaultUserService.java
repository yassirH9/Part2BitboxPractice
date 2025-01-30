package com.yassir.bitbox.Services.user;

import com.yassir.bitbox.dto.user.UserDTO;
import com.yassir.bitbox.enums.UserPrivilegesEnum;

import com.yassir.bitbox.models.user.User;
import com.yassir.bitbox.repositories.IUserRepository;
import com.yassir.bitbox.utils.MapperUtility;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService{
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void save(UserDTO userDTO) {
        if(userDTO !=null){
            //bcript the password before create the user
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            //while the moment this isn't a full example every registered user will be designated with USER role
            userDTO.setPrivileges(UserPrivilegesEnum.USER);
            userRepository.save(MapperUtility.toUserPOJO(userDTO));
        }else{
            throw new HibernateException("Invalid user credentials");
        }
    }

    @Override
    public void delete(String username) {
        User user = userRepository.findByuserName(username);
        if(user !=null){
            userRepository.delete(user);
        }else {
            throw new HibernateException("Unable to delete user with name: "+username);
        }
    }

    @Override
    public boolean isValid(UserDTO userDTO) {
        return !userDTO.getUserName().isEmpty() ||
                userDTO.getPrivileges() != null || !userDTO.getPassword().isEmpty();
    }

    @Override
    public UserDTO getUser(String username) {
        return MapperUtility.toUserDTO(userRepository.findByuserName(username));
    }
}
