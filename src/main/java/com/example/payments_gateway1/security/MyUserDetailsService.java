package com.example.payments_gateway1.security;

import com.example.payments_gateway1.entity.Login;
import com.example.payments_gateway1.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Qualifier("a")
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<Login> login =  loginRepository.findByUsername(username);
       login.orElseThrow(()-> new UsernameNotFoundException("NOT FOUND: +"+username));

      return login.map(MyUserDetails::new).get();
    }
}
