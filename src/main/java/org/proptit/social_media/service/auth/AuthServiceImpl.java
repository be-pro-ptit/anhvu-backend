package org.proptit.social_media.service.auth;

import org.proptit.social_media.dto.auth.login.LoginInputDto;
import org.proptit.social_media.dto.auth.login.LoginOutputDto;
import org.proptit.social_media.dto.auth.register.RegisterInputDto;
import org.proptit.social_media.dto.auth.register.RegisterOutputDto;
import org.proptit.social_media.dto.user.UserOutputDto;
import org.proptit.social_media.entity.AccountEntity;
import org.proptit.social_media.entity.Role;
import org.proptit.social_media.service.account.AccountService;
import org.proptit.social_media.service.jwt.JwtService;
import org.proptit.social_media.service.user.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AccountService accountService;
    private final AuthenticationManager authenticationManager;

    private final UserService userService;
    private final AuthMapper authMapper;

    public AuthServiceImpl(JwtService jwtService, PasswordEncoder passwordEncoder, AccountService accountService, AuthenticationManager authenticationManager, UserService userService, AuthMapper authMapper) {
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.accountService = accountService;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.authMapper = authMapper;
    }

    @Override
    public LoginOutputDto login(LoginInputDto loginInputDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginInputDto.getUsername(), loginInputDto.getPassword()));
        AccountEntity account = accountService.getAccountByUsername(loginInputDto.getUsername());
        String accessToken = jwtService.generateAccessToken(account);
        return new LoginOutputDto(accessToken);
    }

    @Override
    public RegisterOutputDto register(RegisterInputDto registerInputDto) {
        UserOutputDto userOutputDto = userService.createUser(authMapper.getUserInputDtoFromRegisterInputDto(registerInputDto));
        AccountEntity account = new AccountEntity().setUsername(registerInputDto.getUsername())
                                                   .setPassword(passwordEncoder.encode(registerInputDto.getPassword()))
                                                   .setUserId(userOutputDto.getUserId())
                                                   .setRole(Role.USER);
        accountService.createAccount(account);
        return authMapper.getRegisterOutputDtoFromUserOutputDto(userOutputDto);
    }
}
