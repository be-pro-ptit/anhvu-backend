package org.proptit.social_media.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.proptit.social_media.base.Pagination;
import org.proptit.social_media.base.RequiredAuthController;
import org.proptit.social_media.dto.friend.FriendOutputDto;
import org.proptit.social_media.service.friendship.FriendshipService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friend")
@SecurityRequirement(name = "Bearer authentication")
public class FriendController extends RequiredAuthController {
    private final FriendshipService friendshipService;
    public FriendController(FriendshipService friendshipService) {
        this.friendshipService = friendshipService;
    }

    @GetMapping("/get-friends")
    Pagination<FriendOutputDto> getFriends(Pageable pageable, @RequestParam(required = false) Long userId) {
        if(userId == null) {
            return friendshipService.getFriends(pageable, getUserEntityFromContext().getUserId());
        } else {
            return friendshipService.getFriends(pageable, userId);
        }
    }

    @GetMapping("/unfriend/{id}")
    FriendOutputDto unfriend(@PathVariable Long id) {
        return friendshipService.unfriend(id, getUserEntityFromContext());
    }
}
