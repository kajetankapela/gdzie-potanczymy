package com.gdziepotanczymy.config;

import com.gdziepotanczymy.model.User;
import com.gdziepotanczymy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsAdapter implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User userByLogin = userRepository.findByLogin(login);
        if (userByLogin == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(userByLogin.getLogin())
                .password("{noop}"+userByLogin.getPassword())
                .roles(userByLogin.getRole())
                .build();
    }


}
