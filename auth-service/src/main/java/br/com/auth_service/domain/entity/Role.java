package br.com.auth_service.domain.entity;

import br.com.auth_service.domain.enums.ERoleName;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "tb_roles")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ERoleName name;

    public Long getId() {
        return id;
    }

    public ERoleName getName() {
        return name;
    }

    public void setName(ERoleName name) {
        this.name = name;
    }
}
