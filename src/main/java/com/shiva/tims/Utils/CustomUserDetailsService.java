package com.shiva.tims.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shiva.tims.repositories.UserRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService { 

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username ) throws UsernameNotFoundException{
        
        com.shiva.tims.models.User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("USER Not Found"));
        
        
        return new CustomUserDetails(user);
        
    }
}
