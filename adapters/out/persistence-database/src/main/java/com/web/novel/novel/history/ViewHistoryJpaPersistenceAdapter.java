package com.web.novel.novel.history;

import com.web.novel.annotations.PersistenceAdapter;
import com.web.novel.novel.history.entity.ViewHistoryJpaEntity;
import com.web.novel.novel.history.repository.ViewHistoryRepository;
import com.web.novel.novel.port.out.ViewHistorySavePort;

@PersistenceAdapter
public class ViewHistoryJpaPersistenceAdapter implements ViewHistorySavePort {

    private final ViewHistoryRepository viewHistoryRepository;

    public ViewHistoryJpaPersistenceAdapter(final ViewHistoryRepository viewHistoryRepository) {
        this.viewHistoryRepository = viewHistoryRepository;
    }

    @Override
    public void save(ViewHistory viewHistory) {
        var initEntity = new ViewHistoryJpaEntity(
            viewHistory.getMemberId(),
            viewHistory.getNovelId(),
            viewHistory.getChapterId());
        viewHistoryRepository.save(initEntity);
    }
}
