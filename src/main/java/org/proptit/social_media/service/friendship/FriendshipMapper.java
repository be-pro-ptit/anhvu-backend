package org.proptit.social_media.service.friendship;

import org.mapstruct.Mapper;
import org.proptit.social_media.dto.friend.FriendOutputDto;
import org.proptit.social_media.entity.FriendshipEntity;

@Mapper(componentModel = "spring")
public interface FriendshipMapper {
    FriendOutputDto getFriendOutputDtoFromFriendshipEntity(FriendshipEntity friendshipEntity);
}
