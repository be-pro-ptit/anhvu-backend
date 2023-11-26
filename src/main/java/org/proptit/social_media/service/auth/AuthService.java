package org.proptit.social_media.service.auth;

import org.proptit.social_media.dto.auth.login.LoginInputDto;
import org.proptit.social_media.dto.auth.login.LoginOutputDto;
import org.proptit.social_media.dto.auth.register.RegisterInputDto;
import org.proptit.social_media.dto.auth.register.RegisterOutputDto;

public interface AuthService {
    LoginOutputDto login(LoginInputDto loginInputDto);
    RegisterOutputDto register(RegisterInputDto registerInputDto);
}
