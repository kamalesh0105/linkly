package com.kamalesh.server.services;

import com.kamalesh.server.dtos.AuthRequest;
import com.kamalesh.server.models.User;
import com.kamalesh.server.repository.UserRepository;
import com.kamalesh.server.security.jwt.JwtAuthenticationResponse;
import com.kamalesh.server.security.jwt.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class UserService {
    private BCryptPasswordEncoder passwordEncoder;
    private UserRepository userRepo;
    private  JwtUtils jwtUtils;
    private AuthenticationManager authenticationManager;
    public User registerUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }
    public JwtAuthenticationResponse authenticate(AuthRequest authRequest){
        String username =authRequest.getUsername();
        String password =authRequest.getPassword();
        Authentication authentication =authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username,password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails=(UserDetailsImpl) authentication.getPrincipal();
        String jwt=jwtUtils.generateToken(userDetails);
        return new JwtAuthenticationResponse(jwt);
    }
    public User findByUsername(String name) {
        return userRepo.findByUsername(name).orElseThrow(
                () -> new UsernameNotFoundException("User not found with username: " + name)
        );
    }

}
