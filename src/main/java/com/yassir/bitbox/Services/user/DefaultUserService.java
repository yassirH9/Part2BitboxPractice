package com.yassir.bitbox.Services.user;

import com.yassir.bitbox.dto.user.UserDTO;
import com.yassir.bitbox.enums.UserPrivilegesEnum;

import com.yassir.bitbox.models.user.User;
import com.yassir.bitbox.repositories.IUserRepository;
import org.hibernate.HibernateException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService{
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper mmapper;

    @Override
    public void save(UserDTO user) {
        if(user!=null){
            //bcript the password before create the user
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            //while the moment this isn't a full example every registered user will be designated with USER role
            user.setPrivileges(UserPrivilegesEnum.USER);
            userRepository.save(mmapper.map(user,User.class));
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
    public boolean isValid(UserDTO user) {
        return !user.getUserName().isEmpty() ||
                user.getPrivileges() != null || !user.getPassword().isEmpty();
    }

    @Override
    public UserDTO getUser(String username) {
        UserDTO user = mmapper.map(userRepository.findByuserName(username),UserDTO.class);
        if(user != null){
            return user;
        }else{
            throw new HibernateException("Not user find with name: "+username);
        }
    }
}
