package org.proptit.social_media.service.friendship;

import org.proptit.social_media.base.Pagination;
import org.proptit.social_media.dto.friend.FriendOutputDto;
import org.proptit.social_media.dto.user.SimpleUserOutputDto;
import org.proptit.social_media.entity.FriendshipEntity;
import org.proptit.social_media.entity.UserEntity;
import org.proptit.social_media.exeption.ExistsException;
import org.proptit.social_media.repository.FriendshipRepository;
import org.proptit.social_media.service.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FriendshipServiceImpl implements FriendshipService {
    private final FriendshipMapper friendshipMapper;
    private final FriendshipRepository friendshipRepository;
    private final UserService userService;

    public FriendshipServiceImpl(FriendshipMapper friendshipMapper, FriendshipRepository friendshipRepository, UserService userService) {
        this.friendshipMapper = friendshipMapper;
        this.friendshipRepository = friendshipRepository;
        this.userService = userService;
    }

    @Override
    public Pagination<FriendOutputDto> getFriends(Pageable pageable, Long userId) {
        Page<FriendshipEntity> friendshipEntities = friendshipRepository.getFriendshipEntitiesByUser(userId, pageable);
        return new Pagination<FriendOutputDto>().setTotalElement(friendshipEntities.getTotalElements())
                                                .setElements(friendshipEntities.stream()
                                                                               .map(friendshipEntity -> getFriendOutputDtoFromFriendshipEntity(friendshipEntity, userId))
                                                                               .toList());
    }

    @Override
    public FriendOutputDto unfriend(Long id, UserEntity userEntity) {
        FriendshipEntity friendshipEntity = friendshipRepository.findById(id)
                                                                .orElseThrow(() -> new ExistsException("Không tồn tại bạn bè"));
        if(!friendshipEntity.getOtherId1().equals(userEntity.getUserId()) && !friendshipEntity.getOtherId2().equals(userEntity.getUserId())) {
            throw new ExistsException("Không có quyền");
        }
        friendshipRepository.delete(friendshipEntity);
        return getFriendOutputDtoFromFriendshipEntity(friendshipEntity, userEntity.getUserId());
    }

    @Override
    public FriendOutputDto getFriendOutputDtoFromFriendshipEntity(FriendshipEntity friendshipEntity, Long userId) {
        FriendOutputDto friendOutputDto = friendshipMapper.getFriendOutputDtoFromFriendshipEntity(friendshipEntity);
        Long otherId = friendshipEntity.getOtherId1()
                                       .equals(userId) ? friendshipEntity.getOtherId2() : friendshipEntity.getOtherId1();
        SimpleUserOutputDto simpleUserOutputDto = userService.getSimpleUserById(otherId);
        friendOutputDto.setUser(simpleUserOutputDto);
        return friendOutputDto;
    }
}
