package com.web.novel.novel.viewhistory;

import com.web.novel.annotations.PersistenceAdapter;
import com.web.novel.member.Member.MemberId;
import com.web.novel.novel.Novel.NovelId;
import com.web.novel.novel.port.out.ViewHistorySavePort;
import com.web.novel.novel.viewhistory.entity.ViewHistoryJpaEntity;
import com.web.novel.novel.viewhistory.repository.ViewHistoryRepository;
import java.time.LocalDateTime;

@PersistenceAdapter
public class ViewHistoryJpaPersistenceAdapter implements ViewHistorySavePort {

    private final ViewHistoryRepository viewHistoryRepository;

    public ViewHistoryJpaPersistenceAdapter(final ViewHistoryRepository viewHistoryRepository) {
        this.viewHistoryRepository = viewHistoryRepository;
    }

    @Override
    public void up(MemberId memberId, NovelId novelId) {
        viewHistoryRepository.save(
            new ViewHistoryJpaEntity(
                null,
                novelId.getValue(),
                memberId.getValue(),
                LocalDateTime.now()));
    }
}
