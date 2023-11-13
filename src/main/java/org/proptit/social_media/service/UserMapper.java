package org.proptit.social_media.service;

import org.mapstruct.Mapper;
import org.proptit.social_media.dto.UserInputDto;
import org.proptit.social_media.dto.UserOutputDto;
import org.proptit.social_media.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserOutputDto getUserOutputDtoFromUserEntity(UserEntity userEntity);
    UserEntity getUserEntityFromUserInputDto(UserInputDto userInputDto);
}
