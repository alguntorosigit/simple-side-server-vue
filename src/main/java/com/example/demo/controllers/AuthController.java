package com.example.demo.controllers;

import com.example.demo.Utils.JwtUtil;
import com.example.demo.models.AuthRequest;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@Log4j2
@CrossOrigin
@RestController
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
@PostMapping("/auth")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
    log.info("auth : {}",authRequest);
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
                    );
        }catch (Exception e){
            throw  new Exception("invalid username/password");
        }

        return jwtUtil.generateToken(authRequest.getUserName());
    }
}
