package com.epex.empdashboard.repository.auth;

import lib.shared.enums.ERole;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<RoleEntity, String> {

    Optional<RoleEntity> findByRole(ERole role);
}
