package org.proptit.social_media.service.friend_request;

import org.proptit.social_media.base.Pagination;
import org.proptit.social_media.dto.friendrequest.FriendRequestInputDto;
import org.proptit.social_media.dto.friendrequest.FriendRequestOutputDto;
import org.proptit.social_media.entity.FriendRequestEntity;
import org.proptit.social_media.entity.UserEntity;
import org.springframework.data.domain.Pageable;

public interface FriendRequestService {
    FriendRequestOutputDto addFriend(FriendRequestInputDto friendRequestInputDto, UserEntity userEntity);

    FriendRequestOutputDto acceptFriend(Long id, UserEntity userEntity);

    FriendRequestOutputDto cancelFriendRequest(Long id, UserEntity userEntity);

    Pagination<FriendRequestOutputDto> getListRequest(UserEntity userEntity, Pageable pageable);

    Pagination<FriendRequestOutputDto> getListWaiting(UserEntity userEntity, Pageable pageable);

    FriendRequestOutputDto getFriendWaitingByFriendRequestEntity(FriendRequestEntity friendRequestEntity);

    FriendRequestOutputDto getFriendRequestByFriendRequestEntity(FriendRequestEntity friendRequestEntity);
}
