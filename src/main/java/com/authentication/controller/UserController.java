package com.authentication.controller;

import com.authentication.dto.AuthRequestDTO;
import com.authentication.dto.UserFlagRequestDTO;
import com.authentication.entity.UserFlag;
import com.authentication.service.UserService;
import com.authentication.service.impl.JwtServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private JwtServiceImpl jwtServiceImpl;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping("/newUser")
    public String addNewUser(@RequestBody AuthRequestDTO authRequestDTO) {
        return userService.saveUser(authRequestDTO);
    }
    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequestDTO authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtServiceImpl.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user");
        }
    }

    @PostMapping("/saveUserFlag")
    public String saveUserFlag(@RequestBody UserFlagRequestDTO userFlagRequestDTO) {
        return userService.saveUserFlag(userFlagRequestDTO);
    }

    @GetMapping("/getUserFlag")
    public List<UserFlag> getUserFlag(@PathVariable("id") String id, @PathVariable("type") String type) {
        return userService.getUserFlag(id, type);
    }
}
