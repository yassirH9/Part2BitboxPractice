package com.yassir.bitbox.Services.user;

import com.yassir.bitbox.models.user.User;
import com.yassir.bitbox.repositories.IUserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final IUserRepository IUserRepository;

    public CustomUserDetailsService(IUserRepository IUserRepository) {
        this.IUserRepository = IUserRepository;
    }
    public void saveUser(User user){
        IUserRepository.save(user);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User customUser = IUserRepository.findByuserName(username);
        if (customUser == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(
                customUser.getUserName(),
                customUser.getPassword(),
                getAuthorities(customUser)
        );
    }
    private Collection<? extends GrantedAuthority> getAuthorities(User customUser) {
        //the role is get and then turned into a string value to ve verified by the simple auth
        return Collections.singleton(new SimpleGrantedAuthority(customUser.getPrivileges().toString()));
    }

}
