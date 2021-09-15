package com.epex.empdashboard.domain.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class AuthToken implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;

    private final String token;
}
