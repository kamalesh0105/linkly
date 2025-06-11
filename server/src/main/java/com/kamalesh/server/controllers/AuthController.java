package com.kamalesh.server.controllers;

import com.kamalesh.server.dtos.AuthRequest;
import com.kamalesh.server.dtos.RegisterRequest;
import com.kamalesh.server.models.User;
import com.kamalesh.server.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/auth/")
@AllArgsConstructor
public class AuthController {
    private UserService userService;
    @PostMapping("/public/register")
  public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest){
    User user=new User();
    user.setUsername(registerRequest.getUsername());
    user.setPassword(registerRequest.getPassword());
    user.setEmail(registerRequest.getEmail());
    user.setRole("ROLE_USER");
    userService.registerUser(user);
    return ResponseEntity.ok("User Registration Success");
  }
    @PostMapping("/public/login")
    public ResponseEntity<?> AuthenticateUser(@RequestBody AuthRequest authRequest){
        if(userService.authenticate(authRequest)){
            return ResponseEntity.ok("Login Success");
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

}
