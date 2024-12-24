package br.com.auth_service.domain.service;

import br.com.auth_service.domain.entity.User;

import java.util.Optional;

public interface IUserService {

    Optional<User> findByEmail(String email);
}
