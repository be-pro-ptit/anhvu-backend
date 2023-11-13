package org.proptit.social_media.controller;

import org.proptit.social_media.base.BaseResponse;
import org.proptit.social_media.dto.UserInputDto;
import org.proptit.social_media.dto.UserOutputDto;
import org.proptit.social_media.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
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
    BaseResponse<List<UserOutputDto>> getAllUser() {
        return BaseResponse.success(userService.getAllUser());
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
