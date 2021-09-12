package com.epex.empdashboard.domain.auth;

import com.epex.empdashboard.repository.auth.Role;
import com.epex.empdashboard.repository.auth.User;
import lib.shared.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String id;

    @NotEmpty(message = "Username Can not Be Null/Empty !")
    private String username;
    @NotEmpty(message = "Password Can not Be Null/Empty !")
    private String password;

    @NotEmpty(message = "Please Specify Proper Role For User !")
    private Set<String> role;

    public UserDTO(User entity) {
        this.setId(entity.getId());
        this.setUsername(entity.getUsername());
        this.setPassword(entity.getPassword());
        this.setRole(convertEnumsToStringCollection(entity.getRoles()));
    }

    public static Set<String> convertEnumsToStringCollection(Set<Role> collection) {
        return collection
                .stream()
                .map(x -> x.getName().name())
                .collect(Collectors.toSet());
    }

    public static Set<ERole> convertStringsToEnumCollection(Set<String> collection) {
        return collection
                .stream()
                .map(ERole::valueOf)
                .collect(Collectors.toSet());
    }

}
