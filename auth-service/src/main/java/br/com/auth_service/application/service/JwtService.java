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

    private final static long EXPIRES_AT = 3600L;
    private final static Instant NOW = Instant.now();

    public JwtService(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    public String generateToken(User user) {
        String scopes = user.getRoles()
                .stream()
                .map(role -> role.getName().name())
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("auth-service-jwt")
                .issuedAt(NOW)
                .expiresAt(NOW.plusSeconds(EXPIRES_AT))
                .subject(user.getUsername())
                .claim("scope", scopes)
                .build();

        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
