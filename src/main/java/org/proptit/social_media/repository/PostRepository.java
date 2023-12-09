package org.proptit.social_media.repository;

import org.proptit.social_media.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
    @Query(value = "SELECT p FROM PostEntity p WHERE p.timestamp < :timestamp ORDER BY p.timestamp DESC")
    List<PostEntity> loadMorePost(Time timestamp, Pageable pageable);

    Page<PostEntity> findByUserId(Long userId, Pageable pageable);
}
