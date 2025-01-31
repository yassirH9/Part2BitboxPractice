package com.yassir.bitbox.controllers;

import com.yassir.bitbox.Services.user.DefaultUserService;

import com.yassir.bitbox.dto.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
    @Autowired()
    private DefaultUserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO){
        try{
            userService.save(userDTO);
            return new ResponseEntity<>("saved", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Something went wrong with the request and the user was unable to be created: ", HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/admin/privileges")
    public ResponseEntity<String> changeUserPrivileges(@RequestBody UserDTO userDTO){
        try{
            userService.changePrivilegesUser(userDTO.getUserName(),userDTO.getPrivileges());
            return new ResponseEntity<>("Changed user: "+userDTO.getUserName()+" it's now: "+userDTO.getPrivileges(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Something went wrong changin the privileges of the user ERROR: "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
