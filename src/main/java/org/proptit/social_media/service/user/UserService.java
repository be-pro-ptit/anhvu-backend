package org.proptit.social_media.service.user;

import org.proptit.social_media.base.LoadMore;
import org.proptit.social_media.base.Pagination;
import org.proptit.social_media.dto.user.SimpleUserOutputDto;
import org.proptit.social_media.dto.user.UserInputDto;
import org.proptit.social_media.dto.user.UserOutputDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    UserOutputDto getUserById(Long id);

    Pagination<UserOutputDto> getAllUser(Pageable pageable);

    UserOutputDto createUser(UserInputDto userInputDto);

    UserOutputDto updateUser(Long id, UserInputDto userInputDto);

    void deleteUser(Long id);

    void deleteAllUserByIds(List<Long> ids);

    LoadMore<UserOutputDto> getAllUser(Long lastId, int limit);

    SimpleUserOutputDto getSimpleUserById(Long id);
}
