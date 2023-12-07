package org.proptit.social_media.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.proptit.social_media.base.BaseResponse;
import org.proptit.social_media.base.LoadMore;
import org.proptit.social_media.base.Pagination;
import org.proptit.social_media.dto.user.UserInputDto;
import org.proptit.social_media.dto.user.UserOutputDto;
import org.proptit.social_media.service.user.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@SecurityRequirement(name = "Bearer authentication")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    BaseResponse<UserOutputDto> getUserById(@PathVariable Long id) {
        return BaseResponse.success(userService.getUserById(id));
    }

    @PostMapping
    BaseResponse<UserOutputDto> createUser(@RequestBody UserInputDto userInputDto) {
        return BaseResponse.success(userService.createUser(userInputDto));
    }

    @GetMapping
    BaseResponse<Pagination<UserOutputDto>> getAllUser(Pageable pageable) {
        return BaseResponse.success(userService.getAllUser(pageable));
    }
    @GetMapping("/load-more")
    BaseResponse<LoadMore<UserOutputDto>> getAllUser(@RequestParam Long lastId, @RequestParam int limit) {
        return BaseResponse.success(userService.getAllUser(lastId, limit));
    }

    @PutMapping("/{id}")
    BaseResponse<UserOutputDto> updateUser(@PathVariable Long id, @RequestBody UserInputDto userInputDto) {
        return BaseResponse.success(userService.updateUser(id, userInputDto));
    }

    @DeleteMapping("/{id}")
    BaseResponse deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return BaseResponse.success();
    }

    @DeleteMapping
    BaseResponse deleteAllUserByIds(@RequestParam List<Long> ids) {
        userService.deleteAllUserByIds(ids);
        return BaseResponse.success();
    }
}
