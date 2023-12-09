package org.proptit.social_media.service.post;

import org.mapstruct.Mapper;
import org.proptit.social_media.dto.post.PostInputDto;
import org.proptit.social_media.dto.post.PostOutputDto;
import org.proptit.social_media.entity.PostEntity;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostOutputDto getPostOutputDtoFromPostEntity(PostEntity postEntity);

    PostEntity getPostEntityFromPostInputDto(PostInputDto postInputDto);

}
