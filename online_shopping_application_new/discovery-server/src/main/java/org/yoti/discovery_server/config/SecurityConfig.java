package org.yoti.discovery_server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${eureka.username:eureka}")
    private String username;

    @Value("${eureka.password:password}")
    private String password;

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService(username, password);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests.anyRequest().authenticated()
                )
                .httpBasic();
        return http.build();
    }

    static class MyUserDetailsService implements UserDetailsService {

        private final String username;
        private final String password;

        MyUserDetailsService(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        public UserDetails loadUserByUsername(String username) {
            return User.withUsername(this.username)
                    .password(this.password)
                    .authorities("USER")
                    .build();
        }
    }
}
