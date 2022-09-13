package com.example.GoCheeta.security;

import com.example.GoCheeta.model.Customer;
import com.example.GoCheeta.repository.CustomerRepository;
import com.example.GoCheeta.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceCustomUserDetails implements UserDetailsService {
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Customer> customer = customerRepository.findUserByEmail(email);
        customer.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return customer.map(CustomUserDetails:: new).get();
    }
}
