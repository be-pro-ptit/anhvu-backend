package org.proptit.social_media.service;

import org.proptit.social_media.dto.UserInputDto;
import org.proptit.social_media.dto.UserOutputDto;
import org.proptit.social_media.entity.UserEntity;
import org.proptit.social_media.exeption.NotFoundException;
import org.proptit.social_media.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
        UserEntity userEntity = userRepository.findByUserId(id);
        if (userEntity == null) throw new NotFoundException("User not found");
        return userMapper.getUserOutputDtoFromUserEntity(userEntity);
    }

    @Override
    public List<UserOutputDto> getAllUser() {
        return userRepository.findAll().stream().map(userMapper::getUserOutputDtoFromUserEntity).toList();
    }

    @Override
    public UserOutputDto createUser(UserInputDto userInputDto) {
        return userMapper.getUserOutputDtoFromUserEntity(userRepository.save(userMapper.getUserEntityFromUserInputDto(userInputDto)));
    }

    @Override
    public UserOutputDto updateUser(Long id, UserInputDto userInputDto) {
        UserEntity userEntity = userRepository.findByUserId(id);
        if (userEntity == null) throw new NotFoundException("User not found");
        userEntity = userMapper.getUserEntityFromUserInputDto(userInputDto);
        userEntity.setUserId(id);
        return userMapper.getUserOutputDtoFromUserEntity(userRepository.save(userEntity));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteByUserId(id);
    }

    @Override
    public void deleteAllUserByIds(List<Long> ids) {
        try {
            userRepository.deleteAllByUserIdIn(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
