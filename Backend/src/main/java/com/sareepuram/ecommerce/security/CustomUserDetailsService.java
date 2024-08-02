package com.sareepuram.ecommerce.security;

import com.sareepuram.ecommerce.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

import com.sareepuram.ecommerce.user.UserRepository;
import com.sareepuram.ecommerce.user.User;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        System.out.println("User has been found");
        return new CustomUserDetails(user.get().getUserId(), user.get().getEmail(), user.get().getPassword(),
                user.get().getName(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}