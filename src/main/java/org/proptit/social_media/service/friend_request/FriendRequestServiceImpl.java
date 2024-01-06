package org.proptit.social_media.service.friend_request;

import org.proptit.social_media.base.Pagination;
import org.proptit.social_media.dto.friendrequest.FriendRequestInputDto;
import org.proptit.social_media.dto.friendrequest.FriendRequestOutputDto;
import org.proptit.social_media.dto.user.SimpleUserOutputDto;
import org.proptit.social_media.entity.FriendRequestEntity;
import org.proptit.social_media.entity.FriendshipEntity;
import org.proptit.social_media.entity.UserEntity;
import org.proptit.social_media.exeption.ExistsException;
import org.proptit.social_media.exeption.ForbiddenException;
import org.proptit.social_media.repository.FriendRequestRepository;
import org.proptit.social_media.repository.FriendshipRepository;
import org.proptit.social_media.service.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class FriendRequestServiceImpl implements FriendRequestService {

    private final FriendRequestRepository friendRequestRepository;
    private final FriendshipRepository friendshipRepository;
    private final UserService userService;

    public FriendRequestServiceImpl(FriendRequestRepository friendRequestRepository, FriendshipRepository friendshipRepository, UserService userService) {
        this.friendRequestRepository = friendRequestRepository;
        this.friendshipRepository = friendshipRepository;
        this.userService = userService;
    }

    @Override
    public FriendRequestOutputDto addFriend(FriendRequestInputDto friendRequestInputDto, UserEntity userEntity) {
        if(friendRequestRepository.existsByUserIdAndOtherId(userEntity.getUserId(), friendRequestInputDto.getOtherId())){
            throw new ExistsException("Đã gửi lời mời kết bạn");
        }
        if(friendRequestRepository.existsByOtherIdAndUserId(userEntity.getUserId(), friendRequestInputDto.getOtherId())) {
            throw new ExistsException("Đã có lời mời kết bạn");
        }
        if(friendshipRepository.existsByOtherId1AndOtherId2(userEntity.getUserId(), friendRequestInputDto.getOtherId())) {
            throw new ExistsException("Đã là bạn bè");
        }
        FriendRequestEntity friendRequestEntity = new FriendRequestEntity().setUserId(userEntity.getUserId())
                                                                           .setOtherId(friendRequestInputDto.getOtherId());
        friendRequestRepository.save(friendRequestEntity);
        return getFriendWaitingByFriendRequestEntity(friendRequestEntity);
    }

    @Override
    public FriendRequestOutputDto acceptFriend(Long id, UserEntity userEntity) {
        FriendRequestEntity friendRequestEntity = friendRequestRepository.findById(id)
                                                                         .orElseThrow(() -> new ExistsException("Không tồn tại lời mời kết bạn"));
        if(!friendRequestEntity.getOtherId().equals(userEntity.getUserId())) {
            throw new ForbiddenException("Không có quyền");
        }
        if(friendshipRepository.existsByOtherId1AndOtherId2(userEntity.getUserId(), friendRequestEntity.getUserId())) {
            throw new ExistsException("Đã là bạn bè");
        }
        friendRequestRepository.delete(friendRequestEntity);
        FriendshipEntity friendshipEntity = new FriendshipEntity().setOtherId1(userEntity.getUserId())
                                                                  .setOtherId2(friendRequestEntity.getUserId());
        friendshipRepository.save(friendshipEntity);
        return getFriendRequestByFriendRequestEntity(friendRequestEntity);
    }

    @Override
    public FriendRequestOutputDto cancelFriendRequest(Long id, UserEntity userEntity) {
        FriendRequestEntity friendRequestEntity = friendRequestRepository.findById(id)
                                                                         .orElseThrow(() -> new ExistsException("Không tồn tại lời mời kết bạn"));
        if(!friendRequestEntity.getUserId().equals(userEntity.getUserId())) {
            throw new ForbiddenException("Không có quyền");
        }
        friendRequestRepository.delete(friendRequestEntity);
        return getFriendRequestByFriendRequestEntity(friendRequestEntity);
    }

    @Override
    public Pagination<FriendRequestOutputDto> getListRequest(UserEntity userEntity, Pageable pageable) {
        Page<FriendRequestEntity> friendShipEntities = friendRequestRepository.findByOtherId(userEntity.getUserId(), pageable);
        return new Pagination<FriendRequestOutputDto>().setTotalElement(friendShipEntities.getTotalElements())
                                                       .setTotalPage(friendShipEntities.getTotalPages())
                                                       .setElements(friendShipEntities.get()
                                                                                      .map(this::getFriendRequestByFriendRequestEntity)
                                                                                      .toList());
    }

    @Override
    public Pagination<FriendRequestOutputDto> getListWaiting(UserEntity userEntity, Pageable pageable) {
        Page<FriendRequestEntity> friendShipEntities = friendRequestRepository.findByUserId(userEntity.getUserId(), pageable);
        return new Pagination<FriendRequestOutputDto>().setTotalElement(friendShipEntities.getTotalElements())
                                                       .setTotalPage(friendShipEntities.getTotalPages())
                                                       .setElements(friendShipEntities.get()
                                                                                      .map(this::getFriendWaitingByFriendRequestEntity)
                                                                                      .toList());
    }

    @Override
    public FriendRequestOutputDto getFriendWaitingByFriendRequestEntity(FriendRequestEntity friendRequestEntity) {
        SimpleUserOutputDto simpleUserOutputDto = userService.getSimpleUserById(friendRequestEntity.getOtherId());
        return new FriendRequestOutputDto().setId(friendRequestEntity.getId())
                                           .setOther(simpleUserOutputDto);
    }

    public FriendRequestOutputDto getFriendRequestByFriendRequestEntity(FriendRequestEntity friendRequestEntity) {
        SimpleUserOutputDto simpleUserOutputDto = userService.getSimpleUserById(friendRequestEntity.getUserId());
        return new FriendRequestOutputDto().setId(friendRequestEntity.getId())
                                           .setOther(simpleUserOutputDto);
    }
}
