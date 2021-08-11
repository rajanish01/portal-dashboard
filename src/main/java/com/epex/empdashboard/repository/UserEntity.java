package com.epex.empdashboard.repository;

import com.epex.empdashboard.domain.auth.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "auth_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    private String id = UUID.randomUUID().toString();

    private String username;
    private String password;

    public UserEntity(UserDTO dto) {
        this.setUsername(dto.getUsername());
        this.setPassword(dto.getPassword());
    }
}
