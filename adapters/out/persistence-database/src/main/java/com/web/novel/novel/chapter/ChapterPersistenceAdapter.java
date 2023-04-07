package com.web.novel.novel.chapter;

import com.web.novel.annotations.PersistenceAdapter;
import com.web.novel.exception.NovelNotFoundException;
import com.web.novel.novel.chapter.entity.ChapterJpaEntity;
import com.web.novel.novel.chapter.mapper.ChapterMapper;
import com.web.novel.novel.chapter.port.out.ChapterRegisterPort;
import com.web.novel.novel.chapter.repository.ChapterRepository;
import com.web.novel.novel.repository.NovelRepository;

@PersistenceAdapter
public class ChapterPersistenceAdapter implements ChapterRegisterPort {

    private final ChapterMapper chapterMapper;
    private final ChapterRepository chapterRepository;
    private final NovelRepository novelRepository;

    public ChapterPersistenceAdapter(
            final ChapterRepository chapterRepository,
            final NovelRepository novelRepository,
            final ChapterMapper chapterMapper) {
        this.chapterMapper = chapterMapper;
        this.chapterRepository = chapterRepository;
        this.novelRepository = novelRepository;
    }

    @Override
    public void register(final Chapter chapter) {
        Long value = chapter.getNovelId().getValue();

        novelRepository.findById(value)
            .orElseThrow(() -> new NovelNotFoundException(value));

        Integer ordering = chapterRepository.findChapterOrderByIdLimitOne()
            .map(chapterJpaEntity -> chapterJpaEntity.getOrdering() + 1)
            .orElse(Chapter.FIRST_CHAPTER);

        ChapterJpaEntity chapterJpaEntity = chapterMapper.mapToJpaEntity(chapter, ordering);
        chapterRepository.save(chapterJpaEntity);
    }
}
