package com.ncjdjyh.series.micoblog.security.rest;

import com.ncjdjyh.series.micoblog.security.entity.LoginDto;
import com.ncjdjyh.series.micoblog.security.jwt.JWTFilter;
import com.ncjdjyh.series.micoblog.security.jwt.TokenProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Controller to authenticate users.
 */
@RestController
@RequestMapping("/api")
public class AuthenticationRestController {

   private final TokenProvider tokenProvider;

   private final AuthenticationManager authenticationManager;

   public AuthenticationRestController(TokenProvider tokenProvider, AuthenticationManager authenticationManager) {
      this.tokenProvider = tokenProvider;
      this.authenticationManager = authenticationManager;
   }

   @PostMapping("/authenticate")
   public String authorize(@Valid LoginDto loginDto) {

      UsernamePasswordAuthenticationToken authenticationToken =
         new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

      Authentication authentication = authenticationManager.authenticate(authenticationToken);
      SecurityContextHolder.getContext().setAuthentication(authentication);

      String jwt = tokenProvider.createToken(authentication);

      HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

      return jwt;
   }
}
