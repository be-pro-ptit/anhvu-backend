package org.proptit.social_media.service.auth;

import org.proptit.social_media.dto.auth.login.LoginInputDto;
import org.proptit.social_media.dto.auth.login.LoginOutputDto;
import org.proptit.social_media.dto.auth.register.RegisterInputDto;
import org.proptit.social_media.dto.auth.register.RegisterOutputDto;
import org.proptit.social_media.dto.user.UserOutputDto;
import org.proptit.social_media.entity.AccountEntity;
import org.proptit.social_media.entity.Role;
import org.proptit.social_media.exeption.IncorrectPasswordException;
import org.proptit.social_media.service.account.AccountService;
import org.proptit.social_media.service.jwt.JwtService;
import org.proptit.social_media.service.user.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AccountService accountService;

    private final UserService userService;
    private final AuthMapper authMapper;

    public AuthServiceImpl(JwtService jwtService, PasswordEncoder passwordEncoder, AccountService accountService, UserService userService, AuthMapper authMapper) {
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.accountService = accountService;
        this.userService = userService;
        this.authMapper = authMapper;
    }

    @Override
    public LoginOutputDto login(LoginInputDto loginInputDto) {
        AccountEntity account = accountService.getAccountByUsername(loginInputDto.getUsername());
        if (!passwordEncoder.matches(loginInputDto.getPassword(), account.getPassword())) {
            throw new IncorrectPasswordException();
        }
        String accessToken = jwtService.generateAccessToken(account);
        return new LoginOutputDto(accessToken);
    }

    @Override
    public RegisterOutputDto register(RegisterInputDto registerInputDto) {
        UserOutputDto userOutputDto = userService.createUser(authMapper.getUserInputDtoFromRegisterInputDto(registerInputDto));
        AccountEntity account = new AccountEntity().setUsername(registerInputDto.getUsername())
                                                   .setPassword(passwordEncoder.encode(registerInputDto.getPassword()))
                                                   .setUserId(userOutputDto.getUserId())
                                                   .setRole(Role.ROLE_USER);
        accountService.createAccount(account);
        return authMapper.getRegisterOutputDtoFromUserOutputDto(userOutputDto);
    }
}
