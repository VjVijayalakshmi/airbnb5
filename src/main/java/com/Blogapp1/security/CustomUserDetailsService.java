package com.Blogapp1.security;

import com.Blogapp1.entity.User;
import com.Blogapp1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository ur;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = ur.findByUsername(username);
        if(user==null) {
            throw new UsernameNotFoundException("user name is not present!!...");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),null);
    }
}
