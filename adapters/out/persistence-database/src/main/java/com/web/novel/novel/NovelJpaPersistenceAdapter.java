package com.web.novel.novel;

import com.web.novel.annotations.PersistenceAdapter;
import com.web.novel.exception.GenreNotFoundException;
import com.web.novel.exception.MemberNotFoundException;
import com.web.novel.member.repository.MemberRepository;
import com.web.novel.novel.mapper.NovelMapper;
import com.web.novel.novel.port.out.NovelRegisterPort;
import com.web.novel.novel.repository.GenreRepository;
import com.web.novel.novel.repository.NovelRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
public class NovelJpaPersistenceAdapter implements NovelRegisterPort {

    private final NovelMapper novelMapper;
    private final NovelRepository repository;
    private final GenreRepository genreRepository;
    private final MemberRepository memberRepository;

    @Override
    public void store(Novel novel) {
        var genreId = novel.getGenre().getGenreId().getValue();
        var authorId = novel.getAuthorInfo().getAuthorId().getValue();

        var genreJpaEntity = genreRepository.findById(genreId)
            .orElseThrow(() -> new GenreNotFoundException(genreId));

        var memberJpaEntity = memberRepository.findById(authorId)
            .orElseThrow(() -> new MemberNotFoundException(authorId));

        repository.save(novelMapper.mapToJpaEntity(novel, memberJpaEntity.getNickName(), genreJpaEntity.getId()));
    }
}
