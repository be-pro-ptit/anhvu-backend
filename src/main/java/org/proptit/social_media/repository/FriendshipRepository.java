package org.proptit.social_media.repository;

import org.proptit.social_media.entity.FriendshipEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendshipRepository extends JpaRepository<FriendshipEntity, Long> {
    @Query(value = "SELECT COUNT(*) > 0 FROM FriendshipEntity fs WHERE "
            + "(fs.otherId1 = :otherId1 AND fs.otherId2 = :otherId2) OR "
            + "(fs.otherId1 = :otherId2 AND fs.otherId2 = :otherId1)")
    Boolean existsByOtherId1AndOtherId2(Long otherId1, Long otherId2);

    @Query(value = "SELECT fs FROM FriendshipEntity fs WHERE "
            + "fs.otherId1 = :userId OR fs.otherId2 = :userId")
    Page<FriendshipEntity> getFriendshipEntitiesByUser(Long userId, Pageable pageable);
}
