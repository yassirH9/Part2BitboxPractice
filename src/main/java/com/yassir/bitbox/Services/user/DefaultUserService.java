package com.yassir.bitbox.Services.user;

import com.yassir.bitbox.dto.user.UserDTO;
import com.yassir.bitbox.enums.UserPrivilegesEnum;

import com.yassir.bitbox.models.user.User;
import com.yassir.bitbox.repositories.IUserRepository;
import com.yassir.bitbox.utils.MapperUtility;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        User user = userRepository.findByUserName(username).orElseThrow(
                ()-> new UsernameNotFoundException("User not found with username: " + username));
        userRepository.delete(user);
    }

    @Override
    public UserDTO getUser(String username) {
        return MapperUtility.toUserDTO(userRepository.findByUserName(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found with username: " + username)));
    }
    /**
     * This method it's used to promote or degrade users, only available for user with admin privileges
     * **/
    @Override
    public void changePrivilegesUser(String username, UserPrivilegesEnum privilegesEnum) {
        User userTemp = userRepository.findByUserName(username).orElseThrow(
                ()->new UsernameNotFoundException("User not found with username: " + username));
        userTemp.setPrivileges(privilegesEnum);
        userRepository.save(userTemp);
    }

}
