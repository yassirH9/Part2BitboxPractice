package com.yassir.bitbox.controllers;

import com.yassir.bitbox.Services.user.DefaultUserService;

import com.yassir.bitbox.dto.user.UserDTO;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
    @Autowired()
    private DefaultUserService userService;
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
            return new ResponseEntity<>(userService.getUsers(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
