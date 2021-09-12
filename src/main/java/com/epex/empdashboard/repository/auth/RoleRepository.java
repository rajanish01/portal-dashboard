package com.epex.empdashboard.repository.auth;

import lib.shared.enums.ERole;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {

    Optional<Role> findByName(ERole role);

    Set<Role> findByNameIn(Set<ERole> role);
}
