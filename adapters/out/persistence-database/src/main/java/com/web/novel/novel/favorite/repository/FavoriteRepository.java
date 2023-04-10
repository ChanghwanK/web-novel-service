package com.web.novel.novel.favorite.repository;

import com.web.novel.novel.favorite.entity.FavoriteJpaEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FavoriteRepository extends JpaRepository<FavoriteJpaEntity, Long> {

    /**
     * memberId = 논리적인 FK이기 때문에 성능 문제가 생길 수 있음
     */
    @Query("select favorite from FavoriteJpaEntity favorite where favorite.memberId = :memberId")
    List<FavoriteJpaEntity> findAllByMemberId(@Param("memberId") long memberId);

    @Query("")
    Optional<FavoriteJpaEntity> findByMemberId(long memberId);

    @Query("select favorite from FavoriteJpaEntity  favorite where favorite.memberId = :memberId and favorite.novelId = :novelId")
    Optional<FavoriteJpaEntity> findByMemberIdAndNovelId(@Param("memberId") long memberId, @Param("novelId") long novelId);
}
