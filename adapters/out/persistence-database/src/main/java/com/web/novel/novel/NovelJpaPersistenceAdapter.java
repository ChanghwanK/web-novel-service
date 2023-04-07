package com.web.novel.novel;

import com.web.novel.annotations.PersistenceAdapter;
import com.web.novel.exception.GenreNotFoundException;
import com.web.novel.exception.MemberNotFoundException;
import com.web.novel.exception.NovelNotFoundException;
import com.web.novel.member.repository.MemberRepository;
import com.web.novel.novel.Novel.NovelId;
import com.web.novel.novel.entity.NovelJpaEntity;
import com.web.novel.novel.mapper.NovelMapper;
import com.web.novel.novel.port.out.NovelDeletePort;
import com.web.novel.novel.port.out.NovelLoadPort;
import com.web.novel.novel.port.out.NovelRegisterPort;
import com.web.novel.novel.repository.GenreRepository;
import com.web.novel.novel.repository.NovelRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
public class NovelJpaPersistenceAdapter implements NovelRegisterPort, NovelDeletePort, NovelLoadPort {

    private final NovelMapper novelMapper;
    private final NovelRepository novelRepository;
    private final GenreRepository genreRepository;
    private final MemberRepository memberRepository;

    @Override
    public void store(final Novel novel) {
        var genreId = novel.getGenre().getGenreId().getValue();
        var authorId = novel.getAuthorInfo().getAuthorId().getValue();

        var genreJpaEntity = genreRepository.findById(genreId)
            .orElseThrow(() -> new GenreNotFoundException(genreId));

        var memberJpaEntity = memberRepository.findById(authorId)
            .orElseThrow(() -> new MemberNotFoundException(authorId));

        novelRepository.save(novelMapper.mapToJpaEntity(novel, memberJpaEntity.getNickName(), genreJpaEntity.getId()));
    }

    @Override
    public void deleteById(final NovelId novelId) {
        Long id = novelId.getValue();
        novelRepository.findById(id)
            .map(NovelJpaEntity::delete)
            .map(novelRepository::save)
            .orElseThrow(() -> new NovelNotFoundException(id));
    }

    @Override
    public Novel getById(NovelId novelId) {
        var id = novelId.getValue();
        return novelRepository.findById(id)
            .map(novelMapper::mapToDomain)
            .orElseThrow(() -> new NovelNotFoundException(id));
    }
}
