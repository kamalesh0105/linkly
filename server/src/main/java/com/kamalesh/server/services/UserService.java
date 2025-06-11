package com.kamalesh.server.services;

import com.kamalesh.server.dtos.AuthRequest;
import com.kamalesh.server.dtos.RegisterRequest;
import com.kamalesh.server.models.User;
import com.kamalesh.server.repository.UserRepository;
import com.kamalesh.server.security.jwt.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class UserService {
    private BCryptPasswordEncoder passwordEncoder;
    private UserRepository userRepo;
    private  JwtUtils jwt;
    public User registerUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }
    public boolean authenticate(AuthRequest authRequest){
        String username =authRequest.getUsername();
        String password =authRequest.getPassword();
        Optional<User> userObj=userRepo.findByUsername(username);
        if(userObj.isPresent()){
            return passwordEncoder.matches(password,userObj.get().getPassword());
        }
        return false;
    }

}
