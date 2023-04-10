package com.web.novel.novel.favorite;

import com.web.novel.annotations.PersistenceAdapter;
import com.web.novel.exception.MemberNotFoundException;
import com.web.novel.exception.NovelNotFoundException;
import com.web.novel.member.Member.MemberId;
import com.web.novel.member.repository.MemberRepository;
import com.web.novel.novel.Novel.NovelId;
import com.web.novel.novel.favorite.Favorite.FavoriteId;
import com.web.novel.novel.favorite.entity.FavoriteJpaEntity;
import com.web.novel.novel.favorite.mapper.FavoriteMapper;
import com.web.novel.novel.favorite.port.out.FavoriteDeletePort;
import com.web.novel.novel.favorite.port.out.FavoriteLoadPort;
import com.web.novel.novel.favorite.port.out.FavoriteRegisterPort;
import com.web.novel.novel.favorite.repository.FavoriteRepository;
import com.web.novel.novel.repository.NovelRepository;
import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
public class FavoritePersistenceAdapter implements FavoriteRegisterPort,
    FavoriteDeletePort, FavoriteLoadPort {

    private final FavoriteRepository favoriteRepository;
    private final NovelRepository novelRepository;
    private final MemberRepository memberRepository;
    private final FavoriteMapper favoriteMapper;

    public FavoritePersistenceAdapter(
            final FavoriteRepository favoriteRepository,
            final NovelRepository novelRepository,
            final MemberRepository memberRepository,
            final FavoriteMapper favoriteMapper) {
        this.favoriteRepository = favoriteRepository;
        this.novelRepository = novelRepository;
        this.memberRepository = memberRepository;
        this.favoriteMapper = favoriteMapper;
    }

    @Override
    public void register(final Favorite favorite) {
        Long memberId = favorite.getMemberId().getValue();
        Long novelId = favorite.getNovelId().getValue();

        novelRepository.findById(novelId)
            .orElseThrow(() -> new NovelNotFoundException(novelId));

        memberRepository.findById(memberId)
            .orElseThrow(() -> new MemberNotFoundException(memberId));

        FavoriteJpaEntity initEntity = FavoriteJpaEntity.init(memberId, novelId);
        favoriteRepository.save(initEntity);
    }

    @Override
    public void deleteById(final FavoriteId favoriteId) {
        favoriteRepository.deleteById(favoriteId.getValue());
    }

    @Override
    public Favorite getByMemberIdAndNovelId(MemberId memberId, NovelId novelId) {
        return favoriteRepository.findByMemberIdAndNovelId(memberId.getValue(), novelId.getValue())
            .map(favoriteMapper::mapToDomain)
            .orElse(null);
    }

    @Override
    public List<Favorite> getAllByMemberId(final MemberId memberId) {
        return favoriteRepository.findAllByMemberId(memberId.getValue()).stream()
            .map(favoriteMapper::mapToDomain)
            .collect(Collectors.toList());
    }
}
