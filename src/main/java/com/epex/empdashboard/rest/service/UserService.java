package com.epex.empdashboard.rest.service;

import com.epex.empdashboard.domain.auth.UserDTO;
import com.epex.empdashboard.repository.UserEntity;
import com.epex.empdashboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(final UserRepository userRepository,
                       final BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserDTO createUser(UserDTO user) throws Exception {
        try {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            UserEntity persistedUser = userRepository.save(new UserEntity(user));
            return new UserDTO(persistedUser);
        } catch (Exception e) {
            throw new Exception("User Creation Failed : " + e.getMessage());
        }
    }

    public UserDTO getUser(String userName) throws Exception {
        try {
            Optional<UserEntity> systemUser = userRepository.findByUsername(userName);
            if (systemUser.isPresent()) {
                return new UserDTO(systemUser.get());
            }
            throw new Exception("User Not Found !");
        } catch (Exception e) {
            throw new Exception("Something Went Wrong  : " + e.getMessage());
        }
    }

}
