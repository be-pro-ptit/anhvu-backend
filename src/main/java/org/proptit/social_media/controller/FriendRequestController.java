package org.proptit.social_media.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.proptit.social_media.base.Pagination;
import org.proptit.social_media.dto.friendrequest.FriendRequestInputDto;
import org.proptit.social_media.dto.friendrequest.FriendRequestOutputDto;
import org.proptit.social_media.entity.UserEntity;
import org.proptit.social_media.service.friend_request.FriendRequestService;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/friend-request")
@SecurityRequirement(name = "Bearer authentication")
public class FriendRequestController {

    private final FriendRequestService friendRequestService;

    public FriendRequestController(FriendRequestService friendRequestService) {
        this.friendRequestService = friendRequestService;
    }

    @GetMapping("list-request")
    Pagination<FriendRequestOutputDto> getListRequest(Pageable pageable) {
        return friendRequestService.getListRequest(getUserEntityFromContext(), pageable);
    }

    @GetMapping("list-waiting")
    Pagination<FriendRequestOutputDto> getListWaiting(Pageable pageable) {
        return friendRequestService.getListWaiting(getUserEntityFromContext(), pageable);
    }

    @PostMapping("/add")
    FriendRequestOutputDto addFriend(@RequestBody FriendRequestInputDto friendRequestInputDto) {
        return friendRequestService.addFriend(friendRequestInputDto, getUserEntityFromContext());
    }

    @PutMapping("/accept/{id}")
    FriendRequestOutputDto acceptFriend(@PathVariable Long id) {
        return friendRequestService.acceptFriend(id, getUserEntityFromContext());
    }

    @PutMapping("/cancel/{id}")
    FriendRequestOutputDto cancelFriendRequest(@PathVariable Long id) {
        return friendRequestService.cancelFriendRequest(id, getUserEntityFromContext());
    }

    private UserEntity getUserEntityFromContext() {
        return (UserEntity) SecurityContextHolder.getContext()
                                                 .getAuthentication()
                                                 .getCredentials();
    }
}
