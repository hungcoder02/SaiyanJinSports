package com.example.saiyanjinsports.sencurity.service;

import com.example.saiyanjinsports.Entities.User;
import com.example.saiyanjinsports.Repository.UserRepository;
import com.example.saiyanjinsports.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user with that email");
        }
        UserDetailsImpl myUserDetails = new UserDetailsImpl(user);
        return myUserDetails;
    }

}
