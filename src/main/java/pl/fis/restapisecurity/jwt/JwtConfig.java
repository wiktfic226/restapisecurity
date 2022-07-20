package pl.fis.restapisecurity.jwt;

import io.jsonwebtoken.security.Keys;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@ConfigurationProperties(prefix = "application.jwt")
@Component
public class JwtConfig {

    private String key;
    private String tokenPrefix;
    private Integer tokenExpirationAfterDays;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    public Integer getTokenExpirationAfterDays() {
        return tokenExpirationAfterDays;
    }

    public void setTokenExpirationAfterDays(Integer tokenExpirationAfterDays) {
        this.tokenExpirationAfterDays = tokenExpirationAfterDays;
    }

    @Bean
    public SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(key.getBytes());
    }

    public String getAuthorizationHeader() {
        return "Authorization";
    }
}
