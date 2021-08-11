package com.epex.empdashboard.domain.auth;

import com.epex.empdashboard.repository.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String id;

    @NotEmpty(message = "Username Can not Be Null/Empty !")
    private String username;
    @NotEmpty(message = "Password Can not Be Null/Empty !")
    private String password;

    public UserDTO(UserEntity entity) {
        this.setId(entity.getId());
        this.setUsername(entity.getUsername());
        this.setPassword(entity.getPassword());
    }

}
