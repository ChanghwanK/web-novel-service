package com.web.novel.member.repository;

import com.web.novel.member.entity.MemberJpaEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberJpaEntity, Long> {

    Optional<MemberJpaEntity> findByEmail(String value);
    Optional<MemberJpaEntity> findByNickName(String nickName);

    boolean existsByEmail(String email);
    boolean existsByNickName(String nickName);
}
