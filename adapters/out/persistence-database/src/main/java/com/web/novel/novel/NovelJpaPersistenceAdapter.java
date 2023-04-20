package com.web.novel.novel;

import com.web.novel.annotations.PersistenceAdapter;
import com.web.novel.exception.GenreNotFoundException;
import com.web.novel.exception.InvalidParamException;
import com.web.novel.exception.MemberNotFoundException;
import com.web.novel.exception.NovelNotFoundException;
import com.web.novel.member.repository.MemberRepository;
import com.web.novel.novel.Novel.NovelId;
import com.web.novel.novel.entity.NovelJpaEntity;
import com.web.novel.novel.mapper.NovelMapper;
import com.web.novel.novel.port.out.NovelDeletePort;
import com.web.novel.novel.port.out.NovelExistsCheckPort;
import com.web.novel.novel.port.out.NovelLoadPort;
import com.web.novel.novel.port.out.NovelRegisterPort;
import com.web.novel.novel.repository.GenreRepository;
import com.web.novel.novel.repository.NovelRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
public class NovelJpaPersistenceAdapter implements NovelRegisterPort, NovelDeletePort, NovelLoadPort,
    NovelExistsCheckPort {

    private final NovelMapper novelMapper;
    private final NovelRepository novelRepository;
    private final GenreRepository genreRepository;
    private final MemberRepository memberRepository;

    @Override
    public void register(final Novel novel) {
        var genreId = novel.getGenre().getGenreId().getValue();
        var authorId = novel.getAuthorInfo().getAuthorId().getValue();

        var genreJpaEntity = genreRepository.findById(genreId)
            .orElseThrow(() -> new GenreNotFoundException(genreId));

        var memberJpaEntity = memberRepository.findById(authorId)
            .orElseThrow(() -> new MemberNotFoundException(authorId));

        var data = novelMapper.mapToJpaEntity(novel, memberJpaEntity.getNickName(), genreJpaEntity.getId());
        novelRepository.save(data);
    }

    @Override
    public Novel getById(Long novelId) {
        var novelJpaEntity = novelRepository.findById(novelId)
            .orElseThrow(() -> new NovelNotFoundException(novelId));

        var genreJpaEntity = genreRepository.findById(novelJpaEntity.getGenreId())
            .orElseThrow(InvalidParamException::new);

        return novelMapper.mapToDomain(novelJpaEntity, genreJpaEntity);
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
    public void checkExistsNovelId(Long novelId) {
        if(! novelRepository.existsById(novelId))
            throw new NovelNotFoundException(novelId);
    }
}
