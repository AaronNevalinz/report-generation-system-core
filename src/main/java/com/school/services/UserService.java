package com.school.services;

import com.school.Repository.SystemUserRepository;
import com.school.models.SystemUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final SystemUserRepository userRepository;


    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByUsername(username)
                        .orElseThrow(()-> new UsernameNotFoundException("User not Found"));
            }
        };
    }

    public List<SystemUser> getActiveUsers() {
        return userRepository.findByActive(1);
    }
}