package br.com.auth_service.domain.service;

import br.com.auth_service.domain.entity.User;
import org.springframework.security.core.Authentication;

public interface IJwtService {

    String generateToken(User user);
}
