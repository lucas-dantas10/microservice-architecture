package br.com.auth_service.action;

import br.com.auth_service.application.service.JwtService;
import br.com.auth_service.domain.dto.LoginResponseDto;
import br.com.auth_service.domain.dto.LoginUserDto;
import br.com.auth_service.domain.entity.User;
import br.com.auth_service.domain.service.IAuthenticationService;
import br.com.auth_service.domain.service.IJwtService;
import br.com.auth_service.domain.service.IUserService;
import jakarta.ws.rs.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path = "/login")
public class LoginPostAction {

    private final IAuthenticationService authenticationService;

    public LoginPostAction(IAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping()
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginUserDto loginUserDto) throws NotFoundException {
        return ResponseEntity.ok(new LoginResponseDto(authenticationService.authenticate(loginUserDto)));
    }
}
