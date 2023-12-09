package org.proptit.social_media.service.post;

import org.proptit.social_media.base.Pagination;
import org.proptit.social_media.dto.post.PostInputDto;
import org.proptit.social_media.dto.post.PostOutputDto;
import org.proptit.social_media.dto.user.SimpleUserOutputDto;
import org.proptit.social_media.entity.PostEntity;
import org.proptit.social_media.entity.UserEntity;
import org.proptit.social_media.exeption.NotFoundException;
import org.proptit.social_media.repository.PostRepository;
import org.proptit.social_media.service.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    private final UserService userService;

    public PostServiceImpl(PostRepository postRepository, PostMapper postMapper, UserService userService) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.userService = userService;
    }

    @Override
    public PostOutputDto createPost(PostInputDto postInputDto, UserEntity userEntity) {
        PostEntity postEntity = postMapper.getPostEntityFromPostInputDto(postInputDto);
        postEntity.setUserId(userEntity.getUserId());
        postEntity.setTimestamp(new Timestamp(Calendar.getInstance()
                                                      .getTimeInMillis()));
        return getPostOutputDtoFromPostEntity(postRepository.save(postEntity));
    }

    @Override
    public PostOutputDto updatePost(Long id, PostInputDto postInputDto, UserEntity userEntity) {
        PostEntity post = postRepository.findById(id)
                                        .orElseThrow(() -> new NotFoundException("Post not found"));
        if (post.getUserId()
                .equals(userEntity.getUserId())) {
            post.setContent(postInputDto.getContent());
            post.setTimestamp(new Timestamp(Calendar.getInstance()
                                                    .getTimeInMillis()));
            return getPostOutputDtoFromPostEntity(postRepository.save(post));
        } else {
            throw new NotFoundException("Post not found");
        }
    }

    @Override
    public void deletePost(Long id, UserEntity userEntity) {
        PostEntity post = postRepository.findById(id)
                                        .orElseThrow(() -> new NotFoundException("Post not found"));
        if (post.getUserId()
                .equals(userEntity.getUserId())) {
            postRepository.delete(post);
        } else {
            throw new NotFoundException("Post not found");
        }
    }

    @Override
    public PostOutputDto getPost(Long id) {
        return getPostOutputDtoFromPostEntity(postRepository.findById(id)
                                                            .orElseThrow(() -> new NotFoundException("Post not found")));
    }

    @Override
    public Pagination<PostOutputDto> getListPostByUserId(Long userId, Pageable pageable) {
        Page<PostEntity> postPage = postRepository.findByUserId(userId, pageable);
        return new Pagination<PostOutputDto>().setElements(postPage.stream()
                                                                   .map(this::getPostOutputDtoFromPostEntity)
                                                                   .toList())
                                              .setPage(postPage.getNumber())
                                              .setTotalElement(postPage.getTotalElements())
                                              .setTotalPage(postPage.getTotalPages());
    }

    @Override
    public PostOutputDto getPostOutputDtoFromPostEntity(PostEntity postEntity) {
        PostOutputDto postOutputDto = postMapper.getPostOutputDtoFromPostEntity(postEntity);
        SimpleUserOutputDto simpleUserOutputDto = userService.getSimpleUserById(postEntity.getUserId());
        postOutputDto.setUser(simpleUserOutputDto);
        return postOutputDto;
    }

    @Override
    public Pagination<PostOutputDto> getListPost(Pageable pageable) {
        Page<PostEntity> postPage = postRepository.findAll(pageable);
        return new Pagination<PostOutputDto>().setElements(postPage.stream()
                                                                   .map(this::getPostOutputDtoFromPostEntity)
                                                                   .toList())
                                              .setPage(postPage.getNumber())
                                              .setTotalElement(postPage.getTotalElements())
                                              .setTotalPage(postPage.getTotalPages());
    }


}
