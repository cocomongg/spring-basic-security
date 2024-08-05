package com.andrew.springsecuritybasic.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class OAuth2SecurityConfig {

    @Bean
    @ConditionalOnProperty(value = "service.security.type", havingValue = "oauth2", matchIfMissing = false)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> requests.anyRequest().authenticated())
                .oauth2Login(Customizer.withDefaults());

        return http.build();
    }

    // to property
//    @Bean
//    public ClientRegistrationRepository clientRepository() {
//        ClientRegistration clientReg = this.clientRegistration();
//        return new InMemoryClientRegistrationRepository(clientReg);
//    }
//
//    private ClientRegistration clientRegistration() {
//        return CommonOAuth2Provider.GITHUB.getBuilder("github")
//                .clientId("clientId")
//                .clientSecret("clientSecret")
//                .build();
//    }
}
