package com.epex.empdashboard.rest.controller;

import com.epex.common.annotation.ReadAccess;
import com.epex.common.annotation.WriteAccess;
import com.epex.empdashboard.domain.auth.UserDTO;
import com.epex.empdashboard.rest.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/user")
public class UserRestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final UserService userService;

    @Autowired
    public UserRestController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO createUserRequest) {
        try {
            log.info("Creating User.....");
            return ResponseEntity.accepted().body(userService.save(createUserRequest));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    @ReadAccess
    public ResponseEntity<?> getUser(@RequestParam @NotEmpty final String username) {
        try {
            return ResponseEntity.ok(userService.findOne(username));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
