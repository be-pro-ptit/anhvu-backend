package org.proptit.social_media.service.user;

import org.mapstruct.Mapper;
import org.proptit.social_media.dto.user.UserInputDto;
import org.proptit.social_media.dto.user.UserOutputDto;
import org.proptit.social_media.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserOutputDto getUserOutputDtoFromUserEntity(UserEntity userEntity);
    UserEntity getUserEntityFromUserInputDto(UserInputDto userInputDto);
}
