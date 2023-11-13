package org.proptit.social_media.service;

import org.proptit.social_media.dto.UserInputDto;
import org.proptit.social_media.dto.UserOutputDto;

import java.util.List;

public interface UserService {
    UserOutputDto getUserById(Long id);

    List<UserOutputDto> getAllUser();

    UserOutputDto createUser(UserInputDto userInputDto);

    UserOutputDto updateUser(Long id, UserInputDto userInputDto);

    void deleteUser(Long id);

    void deleteAllUserByIds(List<Long> ids);
}
