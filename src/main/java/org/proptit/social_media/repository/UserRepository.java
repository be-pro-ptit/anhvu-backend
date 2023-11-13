package org.proptit.social_media.repository;

import jakarta.transaction.Transactional;
import org.proptit.social_media.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserId(Long userId);

    void deleteByUserId(Long userId);

    void deleteAllByUserIdIn(List<Long> ids);
}
