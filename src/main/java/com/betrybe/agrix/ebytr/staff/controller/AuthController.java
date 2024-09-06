package com.betrybe.agrix.ebytr.staff.controller;

import com.betrybe.agrix.ebytr.staff.controller.dto.AuthDto;
import com.betrybe.agrix.ebytr.staff.controller.dto.TokenDto;
import com.betrybe.agrix.ebytr.staff.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Auth controller.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

  private final TokenService tokenService;
  private final AuthenticationManager authenticationManager;

  /**
   * Instantiates a new Auth controller.
   *
   * @param tokenService          the token service
   * @param authenticationManager the authentication manager
   */
  @Autowired
  public AuthController(
          TokenService tokenService,
          AuthenticationManager authenticationManager
  ) {
    this.tokenService = tokenService;
    this.authenticationManager = authenticationManager;
  }

  /**
   * Login token dto.
   *
   * @param authDto the auth dto
   * @return the token dto
   */
  @PostMapping("/login")
  public TokenDto login(@RequestBody AuthDto authDto) {
    UsernamePasswordAuthenticationToken usernamePassword = new
            UsernamePasswordAuthenticationToken(authDto.username(), authDto.password());

    Authentication authentication = authenticationManager.authenticate(usernamePassword);
    String token = tokenService.generateToken(authentication.getName());
    return new TokenDto(token);
  }
}
