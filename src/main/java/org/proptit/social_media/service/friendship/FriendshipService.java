package org.proptit.social_media.service.friendship;

import org.proptit.social_media.base.Pagination;
import org.proptit.social_media.dto.friend.FriendOutputDto;
import org.proptit.social_media.entity.FriendshipEntity;
import org.proptit.social_media.entity.UserEntity;
import org.springframework.data.domain.Pageable;

public interface FriendshipService {
    Pagination<FriendOutputDto> getFriends(Pageable pageable, Long userId);

    FriendOutputDto unfriend(Long id, UserEntity userEntity);

    FriendOutputDto getFriendOutputDtoFromFriendshipEntity(FriendshipEntity friendshipEntity, Long userId);
}
