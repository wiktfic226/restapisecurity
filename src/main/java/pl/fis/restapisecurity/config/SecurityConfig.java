package pl.fis.restapisecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import pl.fis.restapisecurity.enums.Permission;
import pl.fis.restapisecurity.enums.UserRole;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    @Override
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
