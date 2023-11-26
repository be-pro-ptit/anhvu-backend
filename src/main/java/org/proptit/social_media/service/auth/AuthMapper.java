package org.proptit.social_media.service.auth;

import org.mapstruct.Mapper;
import org.proptit.social_media.dto.auth.register.RegisterInputDto;
import org.proptit.social_media.dto.auth.register.RegisterOutputDto;
import org.proptit.social_media.dto.user.UserInputDto;
import org.proptit.social_media.dto.user.UserOutputDto;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    UserInputDto getUserInputDtoFromRegisterInputDto(RegisterInputDto registerInputDto);
    RegisterOutputDto getRegisterOutputDtoFromUserOutputDto(UserOutputDto userOutputDto);
}
