package org.proptit.social_media.repository;

import jakarta.transaction.Transactional;
import org.proptit.social_media.entity.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserId(Long userId);

    void deleteByUserId(Long userId);

    void deleteAllByUserIdIn(List<Long> ids);
    @Query(value = "SELECT u FROM UserEntity u WHERE u.userId > :lastId OR :lastId IS NULL ORDER BY u.userId ASC")
    List<UserEntity> findAllAfterId(Long lastId, Pageable pageable);
}
