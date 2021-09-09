package com.epex.empdashboard.repository.auth;

import lib.shared.enums.ERole;
import lib.shared.enums.Privilege;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "auth_roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity {
    @Id
    private String id;

    private ERole role;

    private Set<Privilege> privileges;

}
