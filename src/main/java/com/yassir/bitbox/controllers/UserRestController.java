package com.yassir.bitbox.controllers;

import com.yassir.bitbox.Services.user.DefaultUserService;

import com.yassir.bitbox.dto.auth.AuthRequestDTO;
import com.yassir.bitbox.dto.auth.AuthResponseDTO;
import com.yassir.bitbox.dto.user.UserDTO;
import com.yassir.bitbox.models.user.User;
import com.yassir.bitbox.repositories.IUserRepository;
import com.yassir.bitbox.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
    @Autowired private DefaultUserService userService;
    @Autowired private JwtUtils jwtUtils;
    @Autowired private BCryptPasswordEncoder passwordEncoder;
    @Autowired private IUserRepository userRepository;
    /*
    * ABLE FOR USER PRIVILEGES ONLY FOR DEBUG FURTHER CHANGES ON SecurityConfig.java
    * */
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO){
        try{
            userService.save(userDTO);
            return new ResponseEntity<>("saved", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Something went wrong with the request and the user was unable to be created: ", HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponseDTO> loginUser(@RequestBody AuthRequestDTO authRequestDTO) {
        User user = userRepository.findByUserName(authRequestDTO.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));

        if (passwordEncoder.matches(authRequestDTO.getPassword(), user.getPassword())) {
            UserDetails userDetails = org.springframework.security.core.userdetails.User
                    .withUsername(user.getUserName())
                    .password(user.getPassword())
                    .authorities(user.getPrivileges().name())
                    .build();

            AuthResponseDTO response = new AuthResponseDTO(
                    jwtUtils.generateToken(userDetails),
                    user.getUserName(),
                    user.getPrivileges().name()
            );
            return ResponseEntity.ok(response);
        } else {
            throw new BadCredentialsException("Invalid username or password");
        }
    }


    //-----------------------------------------------------------------------------------------------------------------------------------------------------------
    @PutMapping("/admin/privileges")
    public ResponseEntity<String> changeUserPrivileges(@RequestBody UserDTO userDTO){
        try{
            userService.changePrivilegesUser(userDTO.getUserName(),userDTO.getPrivileges());
            return new ResponseEntity<>("Changed user: "+userDTO.getUserName()+" it's now: "+userDTO.getPrivileges(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Something went wrong changin the privileges of the user ERROR: "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/admin/delete/{userid}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userid){
        try{
            userService.delete(userid);
            return new ResponseEntity<>("The user has been deleted successfully", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Something went wrong changin the privileges of the user ERROR: "+e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }
    @GetMapping("/admin/users")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        try{
            return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
