package org.proptit.social_media.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.proptit.social_media.entity.UserEntity;
import org.proptit.social_media.repository.UserRepository;
import org.proptit.social_media.service.user.UserServiceImpl;

import java.util.List;

import static org.mockito.Mockito.when;

@DisplayName("UserServiceTest")
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserServiceImpl userService;

    @BeforeAll
    void setUp() {
        List<UserEntity> userEntities = List.of(new UserEntity().setUserId(1L)
                                                                .setUsername("username1")
                                                                .setAvatar("avatar1")
                                                                .setFullName("fullname1")
                                                                .setEmail("email1")
                                                                .setPhoneNumber("phonenumber1"),
                                                new UserEntity().setUserId(2L)
                                                                 .setUsername("username2")
                                                                 .setAvatar("avatar2")
                                                                 .setFullName("fullname2")
                                                                 .setEmail("email2")
                                                                 .setPhoneNumber("phonenumber2"),
                                                new UserEntity().setUserId(3L)
                                                              .setUsername("username3")
                                                              .setAvatar("avatar3")
                                                              .setFullName("fullname3")
                                                              .setEmail("email3")
                                                              .setPhoneNumber("phonenumber3"),
                                                new UserEntity().setUserId(4L)
                                                               .setUsername("username4")
                                                               .setAvatar("avatar4")
                                                               .setFullName("fullname4")
                                                               .setEmail("email4")
                                                               .setPhoneNumber("phonenumber4"),
                                                new UserEntity().setUserId(5L)
                                                                .setUsername("username5")
                                                                .setAvatar("avatar5")
                                                                .setFullName("fullname5")
                                                                .setEmail("email5")
                                                                .setPhoneNumber("phonenumber5"));
        when(userRepository.findAll()).thenReturn(userEntities);
    }
}
