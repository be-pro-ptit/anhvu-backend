package org.proptit.social_media.service.post;

import org.proptit.social_media.base.Pagination;
import org.proptit.social_media.dto.post.PostInputDto;
import org.proptit.social_media.dto.post.PostOutputDto;
import org.proptit.social_media.entity.PostEntity;
import org.proptit.social_media.entity.UserEntity;
import org.springframework.data.domain.Pageable;

public interface PostService {
    PostOutputDto createPost(PostInputDto postEntity, UserEntity userEntity);

    PostOutputDto updatePost(Long id, PostInputDto postEntity, UserEntity userEntity);

    void deletePost(Long id, UserEntity userEntity);

    PostOutputDto getPost(Long id);

    Pagination<PostOutputDto> getListPostByUserId(Long userId, Pageable pageable);

    PostOutputDto getPostOutputDtoFromPostEntity(PostEntity postEntity);

    Pagination<PostOutputDto> getListPost(Pageable pageable);
}
