package com.sareepuram.ecommerce.user;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;


public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}