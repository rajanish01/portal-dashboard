package com.epex.empdashboard.repository.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "auth_privilege")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrivilegeEntity {
    @Id
    private String id;

    private String name;


}
