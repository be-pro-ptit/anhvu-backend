package org.proptit.social_media.repository;

import org.proptit.social_media.entity.FriendRequestEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequestEntity, Long> {

    @Query(value = "SELECT  fs FROM FriendRequestEntity fs WHERE fs.userId = :userId")
    Page<FriendRequestEntity> getListFriendByUserId(Long userId, Pageable pageable);

    Page<FriendRequestEntity> findByOtherId(Long otherId, Pageable pageable);

    Boolean existsByUserIdAndOtherId(Long userId, Long otherId);

    Boolean existsByOtherIdAndUserId(Long otherId, Long userId);

    Page<FriendRequestEntity> findByUserId(Long userId, Pageable pageable);
}
