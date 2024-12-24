package br.com.auth_service.application.service;

import br.com.auth_service.domain.dto.LoginUserDto;
import br.com.auth_service.domain.entity.User;
import br.com.auth_service.domain.service.IAuthenticationService;
import br.com.auth_service.domain.service.IJwtService;
import br.com.auth_service.domain.service.IUserService;
import jakarta.ws.rs.NotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements IAuthenticationService {

    private final IJwtService jwtService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final IUserService userService;

    public AuthenticationService(IJwtService jwtService,
                                 BCryptPasswordEncoder bCryptPasswordEncoder,
                                 IUserService userService) {
        this.jwtService = jwtService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
    }

    public String authenticate(LoginUserDto loginUserDto) {
        Optional<User> user = userService.findByEmail(loginUserDto.email());

        if (user.isEmpty() || !isLoginCorrect(loginUserDto, user.get())) {
            throw new NotFoundException("User not found!");
        }

        return jwtService.generateToken(user.get());
    }

    private boolean isLoginCorrect(LoginUserDto loginUserDto, User user) {
        return bCryptPasswordEncoder.matches(loginUserDto.password(), user.getPassword());
    }
}
