package com.example.cumulusspringboot.services;

import com.example.cumulusspringboot.entities.Role;
import com.example.cumulusspringboot.entities.User;
import com.example.cumulusspringboot.interfaces.IUserService;
import com.example.cumulusspringboot.provider.JwtTokenProvider;
import com.example.cumulusspringboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
     UserRepository userRepository;

    @Autowired
     AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public User register(User user) {
        // Check if user with same username or email already exists
        if (userRepository.findByUsername(user.getUsername()) != null ||
                userRepository.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("User with same username or email already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

    @Override
    public String login(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRole());
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }
}
