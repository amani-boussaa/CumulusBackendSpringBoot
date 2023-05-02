package com.example.cumulusspringboot.security;

import com.example.cumulusspringboot.entities.Role;
import com.example.cumulusspringboot.entities.User;
import com.example.cumulusspringboot.repositories.UserRepository;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//
//    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
//          User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
//                 .orElseThrow(() ->
//                         new UsernameNotFoundException("User not found with username or email: "+ usernameOrEmail));
//
//        Set<GrantedAuthority> authorities = user
//                .getRoles()
//                .stream()
//                .map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
//
//        return new org.springframework.security.core.userdetails.User(user.getEmail(),
//                user.getPassword(),
//                authorities);
//    }
@Override
public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
    User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with username or email: " + usernameOrEmail));

    Role role = user.getRole();
    if (role == null) {
        throw new UsernameNotFoundException("User has no role assigned");
    }

    Set<GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority(role.toString()));

    return new org.springframework.security.core.userdetails.User(user.getEmail(),
            user.getPassword(),
            authorities);
}

}
