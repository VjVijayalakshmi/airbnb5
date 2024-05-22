package com.Blogapp1.controller;

import com.Blogapp1.entity.User;
import com.Blogapp1.payload.LoginDto;
import com.Blogapp1.payload.SignUpDto;
import com.Blogapp1.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private UserRepository ur;
    private PasswordEncoder ps;
    private AuthenticationManager authenticationManager;



    @PostMapping("/{sign}")
    public ResponseEntity<String> createUser(@RequestBody SignUpDto sign) {
        if (ur.existsByEmail(sign.getEmail())) {
            return new ResponseEntity<>("email id is already registered", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (ur.existsByUsername(sign.getUsername())) {
            return new ResponseEntity<>("username is already registered", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        User user = new User();
        user.setName(sign.getName());
        user.setEmail(sign.getEmail());
        user.setUsername(sign.getUsername());
        user.setPassword(ps.encode(sign.getPassword()));
        ur.save(user);
        return new ResponseEntity<>("user registered", HttpStatus.CREATED);
    }

     public ResponseEntity<String> signIn(@RequestBody LoginDto loginDto){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword());
         Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
         SecurityContextHolder.getContext().setAuthentication(authenticate);
         return new ResponseEntity<>("user is registered",HttpStatus.OK);


     }






    }








