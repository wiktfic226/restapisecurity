package pl.fis.restapisecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api/user").hasAnyRole("ADMIN", "USER")
                .antMatchers("/api/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        PasswordEncoderConfig passwordEncoderConfig = new PasswordEncoderConfig();
        UserDetails user =
                User.withUsername("user")
                        .password(passwordEncoderConfig.passwordEncoder().encode("pass"))
                        .roles("USER")
                        .build();
        UserDetails admin =
                User.withUsername("admin")
                        .password(passwordEncoderConfig.passwordEncoder().encode("pass"))
                        .roles("ADMIN")
                        .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}
