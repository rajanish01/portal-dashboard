package com.epex.empdashboard.repository.auth;

import com.epex.empdashboard.domain.auth.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "auth_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    private String id;

    private String username;
    private String password;

    @DBRef(lazy = true)
    private RoleEntity role;

    public UserEntity(UserDTO dto,RoleEntity role) {
        this.setUsername(dto.getUsername());
        this.setPassword(dto.getPassword());
        this.setRole(role);
    }
}
