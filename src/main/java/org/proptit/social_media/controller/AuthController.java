package org.proptit.social_media.controller;

import org.proptit.social_media.dto.auth.login.LoginInputDto;
import org.proptit.social_media.dto.auth.login.LoginOutputDto;
import org.proptit.social_media.dto.auth.register.RegisterInputDto;
import org.proptit.social_media.dto.auth.register.RegisterOutputDto;
import org.proptit.social_media.service.auth.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginOutputDto login(@RequestBody LoginInputDto loginInputDto) {
        return authService.login(loginInputDto);
    }

    @PostMapping("/register")
    public RegisterOutputDto register(@RequestBody RegisterInputDto registerInputDto) {
        return authService.register(registerInputDto);
    }
}
