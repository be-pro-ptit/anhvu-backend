package org.proptit.social_media.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.proptit.social_media.base.BaseResponse;
import org.proptit.social_media.base.Pagination;
import org.proptit.social_media.base.RequiredAuthController;
import org.proptit.social_media.dto.post.PostInputDto;
import org.proptit.social_media.dto.post.PostOutputDto;
import org.proptit.social_media.service.post.PostService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
@SecurityRequirement(name = "Bearer authentication")
public class PostController extends RequiredAuthController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    BaseResponse<PostOutputDto> createPost(@RequestBody PostInputDto postInputDto) {
        return BaseResponse.success(postService.createPost(postInputDto, getUserEntityFromContext()));
    }

    @PutMapping("/{id}")
    BaseResponse<PostOutputDto> updatePost(@PathVariable Long id, @RequestBody PostInputDto postInputDto) {
        return BaseResponse.success(postService.updatePost(id, postInputDto, getUserEntityFromContext()));
    }

    @DeleteMapping("/{id}")
    BaseResponse deletePost(@PathVariable Long id) {
        postService.deletePost(id, getUserEntityFromContext());
        return BaseResponse.success();
    }

    @GetMapping("/{id}")
    BaseResponse<PostOutputDto> getPost(@PathVariable Long id) {
        return BaseResponse.success(postService.getPost(id));
    }

    @GetMapping("/user/{userId}")
    BaseResponse<Pagination<PostOutputDto>> getListPostByUserId(@PathVariable Long userId, Pageable pageable) {
        return BaseResponse.success(postService.getListPostByUserId(userId, pageable));
    }

    @GetMapping
    BaseResponse<Pagination<PostOutputDto>> getListPost(Pageable pageable) {
        return BaseResponse.success(postService.getListPost(pageable));
    }
}
