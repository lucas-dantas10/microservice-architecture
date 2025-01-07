package br.com.auth_service.infra.repository;

import br.com.auth_service.domain.entity.Role;
import br.com.auth_service.domain.enums.ERoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERoleName name);
}
