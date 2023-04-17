package com.web.novel.novel.chapter;

import com.web.novel.annotations.PersistenceAdapter;
import com.web.novel.exception.CanNotRegisterFavoriteNovelException;
import com.web.novel.exception.InvalidParamException;
import com.web.novel.exception.NovelNotFoundException;
import com.web.novel.novel.Novel.NovelId;
import com.web.novel.novel.chapter.entity.ChapterJpaEntity;
import com.web.novel.novel.chapter.mapper.ChapterMapper;
import com.web.novel.novel.chapter.port.out.ChapterLoadPort;
import com.web.novel.novel.chapter.port.out.ChapterRegisterPort;
import com.web.novel.novel.chapter.repository.ChapterRepository;
import com.web.novel.novel.repository.NovelRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.PageRequest;

@PersistenceAdapter
public class ChapterPersistenceAdapter implements ChapterRegisterPort, ChapterLoadPort {

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

    @Override
    public Chapter getLastUploadedChapterByNovelId(NovelId novelId) {
        return chapterRepository.findChapterOrderByIdLimitOne()
            .map(chapterMapper::mapToDomain)
            .orElseThrow(CanNotRegisterFavoriteNovelException::new);
    }

    @Override
    public List<Chapter> getChapterOrderByUploadedAtDESCWithPage(NovelId novelId, Integer page) {
        var pageRequest = PageRequest.of(page, 10);
        return chapterRepository.findChapterByNovelIdOrderByOrderingDESC(novelId.getValue(), pageRequest)
            .stream()
            .map(chapterMapper::mapToDomain)
            .collect(Collectors.toList());
    }

    @Override
    public Chapter getByChapterId(Long chapterId) {
        var chapterJpaEntity = chapterRepository.findById(chapterId)
            .orElseThrow(InvalidParamException::new);
        return chapterMapper.mapToDomain(chapterJpaEntity);
    }
}
