package com.nlu.filmweb.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String extractUsername(String jwt);

    boolean isTokenValid(String jwt, UserDetails userDetails);
}
