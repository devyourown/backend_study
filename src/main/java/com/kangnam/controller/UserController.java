package com.kangnam.controller;

import com.kangnam.dto.UserDTO;
import com.kangnam.persistence.entity.UserEntity;
import com.kangnam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok().body(userService
                .createMember(userDTO.getEmail(),
                        userDTO.getPassword()));
    }


    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        UserEntity userEntity = userService
                .getByCredentials(userDTO.getEmail(), userDTO.getPassword());
        if (userEntity == null)
            throw new IllegalArgumentException("Login fail");
        return ResponseEntity.ok().body(UserDTO.entityToDTO(userEntity));
    }

}
