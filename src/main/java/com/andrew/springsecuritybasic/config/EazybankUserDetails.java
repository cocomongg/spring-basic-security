package com.andrew.springsecuritybasic.config;

import com.andrew.springsecuritybasic.mdoel.Customer;
import com.andrew.springsecuritybasic.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EazybankUserDetails implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Customer> customers = customerRepository.findByEmail(username);
        if(customers.isEmpty()) {
            throw new UsernameNotFoundException("User details not found for user: " + username);
        }

        Customer customer = customers.get(0);

        return new User(
                customer.getEmail(),
                customer.getPwd(),
                List.of(new SimpleGrantedAuthority(customer.getRole()))
        );
    }
}
