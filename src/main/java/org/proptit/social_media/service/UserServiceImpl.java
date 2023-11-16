package org.proptit.social_media.service;

import org.proptit.social_media.base.LoadMore;
import org.proptit.social_media.base.Pagination;
import org.proptit.social_media.dto.UserInputDto;
import org.proptit.social_media.dto.UserOutputDto;
import org.proptit.social_media.entity.UserEntity;
import org.proptit.social_media.exeption.NotFoundException;
import org.proptit.social_media.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserOutputDto getUserById(Long id) {
        Optional<UserEntity> userEntity = userRepository.findByUserId(id);
        if (userEntity.isEmpty()) throw new NotFoundException("User not found");
        return userMapper.getUserOutputDtoFromUserEntity(userEntity.get());
    }

    @Override
    public Pagination<UserOutputDto> getAllUser(Pageable pageable) {
        Pagination<UserOutputDto> pagination = new Pagination<>();
        Page<UserEntity> userEntities = userRepository.findAll(pageable);
        pagination.setElements(userRepository.findAll()
                                             .stream()
                                             .map(userMapper::getUserOutputDtoFromUserEntity)
                                             .toList())
                  .setTotalElement(userEntities.getTotalElements())
                  .setTotalPage(userEntities.getTotalPages())
                  .setPage(pageable.getPageNumber());
        return pagination;
    }

    @Override
    public UserOutputDto createUser(UserInputDto userInputDto) {
        return userMapper.getUserOutputDtoFromUserEntity(userRepository.save(userMapper.getUserEntityFromUserInputDto(userInputDto)));
    }

    @Override
    public UserOutputDto updateUser(Long id, UserInputDto userInputDto) {
        Optional<UserEntity> userEntity = userRepository.findByUserId(id);
        if (userEntity.isEmpty()) throw new NotFoundException("User not found");
        UserEntity newUserEntity = userMapper.getUserEntityFromUserInputDto(userInputDto);
        newUserEntity.setUserId(id);
        return userMapper.getUserOutputDtoFromUserEntity(userRepository.save(newUserEntity));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteByUserId(id);
    }

    @Override
    public void deleteAllUserByIds(List<Long> ids) {
        userRepository.deleteAllByUserIdIn(ids);
    }

    @Override
    public LoadMore<UserOutputDto> getAllUser(Long lastId, int limit) {
        LoadMore<UserOutputDto> loadMore = new LoadMore<>();
        List<UserEntity> userEntities = userRepository.findAllAfterId(lastId, Pageable.ofSize(limit));
        if (userEntities.isEmpty()) return loadMore.setLimit(limit)
                                                   .setLastId(lastId)
                                                   .setElements(List.of());
        loadMore.setElements(userEntities.stream()
                                         .map(userMapper::getUserOutputDtoFromUserEntity)
                                         .toList())
                .setLastId(userEntities.get(userEntities.size() - 1)
                                       .getUserId())
                .setLimit(limit);
        return loadMore;
    }
}
