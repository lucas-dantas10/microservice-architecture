package br.com.auth_service.domain.service;

import br.com.auth_service.domain.dto.LoginUserDto;
import br.com.auth_service.domain.entity.User;

public interface IAuthenticationService {

    String authenticate(LoginUserDto loginUserDto);
}
