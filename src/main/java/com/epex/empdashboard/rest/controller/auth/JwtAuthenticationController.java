package com.epex.empdashboard.rest.controller.auth;

import com.epex.empdashboard.auth.JwtTokenUtil;
import com.epex.empdashboard.auth.annotation.ReadAccess;
import com.epex.empdashboard.domain.auth.AuthToken;
import com.epex.empdashboard.domain.auth.LoginUser;
import com.epex.empdashboard.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class JwtAuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public JwtAuthenticationController(final AuthenticationManager authenticationManager,
                                       final JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> generateToken(@RequestBody LoginUser loginUser) throws Exception {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginUser.getUsername(), loginUser.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("USER DISABLED : " + e.getMessage());
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID CREDENTIALS : " + e.getMessage());
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        return ResponseEntity.ok(new AuthToken(token));
    }

    @ReadAccess
    @RequestMapping(value="/userping", method = RequestMethod.GET)
    public String userPing(){
        return "Any User Can Read This";
    }

}
