package pl.fis.restapisecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import pl.fis.restapisecurity.enums.Permission;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        PasswordEncoderConfig passwordEncoderConfig = new PasswordEncoderConfig();
        UserDetails user =
                User.withUsername("user")
                        .password(passwordEncoderConfig.passwordEncoder().encode("user"))
                        .authorities(Permission.USER_EDIT.getPermission(), Permission.USER_READ.getPermission())
                        .build();
        UserDetails admin =
                User.withUsername("admin")
                        .password(passwordEncoderConfig.passwordEncoder().encode("admin"))
                        .authorities(Permission.ADMIN.getPermission())
                        .build();
        UserDetails spectator =
                User.withUsername("spectator")
                        .password(passwordEncoderConfig.passwordEncoder().encode("spectator"))
                        .authorities(Permission.USER_READ.getPermission())
                        .build();
        return new InMemoryUserDetailsManager(user, admin, spectator);
    }
}

