package br.com.auth_service.application.service;

import br.com.auth_service.domain.entity.User;
import br.com.auth_service.domain.service.IJwtService;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.security.oauth2.jwt.JwtEncoder;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class JwtService implements IJwtService {

    private final JwtEncoder encoder;

    public JwtService(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    public String generateToken(User user) {
        Instant now = Instant.now();
        long expiresAt = 3600L;

        String scopes = user.getRoles()
                .stream()
                .map(role -> role.getName().name())
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("auth-service-jwt")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresAt))
                .subject(user.getUsername())
                .claim("scope", scopes)
                .build();

        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
