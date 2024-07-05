package com.andrew.springsecuritybasic.config;

import com.andrew.springsecuritybasic.mdoel.Authority;
import com.andrew.springsecuritybasic.mdoel.Customer;
import com.andrew.springsecuritybasic.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class CustomUsernamePwdAuthenticationProvider implements AuthenticationProvider {

    private final CustomerRepository customerRepository;
    private final  PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException  {
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        List<Customer> customers = customerRepository.findByEmail(username);

        if(customers.isEmpty()) {
            throw new BadCredentialsException("No user registered with this details");
        }

        Customer customer = customers.get(0);
        boolean isPwdMatches = passwordEncoder.matches(pwd, customer.getPwd());
        if(!isPwdMatches) {
            throw new BadCredentialsException("Invalid Password");
        }

        return new UsernamePasswordAuthenticationToken(
                username, pwd, this.getGrantedAuthorities(customer.getAuthorities()));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

    private List<GrantedAuthority> getGrantedAuthorities(Set<Authority> authorities) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for(Authority authority: authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
        }

        return grantedAuthorities;
    }
}
