package com.web.novel.novel.chapter.service;

import com.web.novel.member.port.out.MemberLoadPort;
import com.web.novel.member.port.out.UpdateMemberPointBalancePort;
import com.web.novel.novel.chapter.ChapterPurChaseLog;
import com.web.novel.novel.chapter.port.in.ChapterPurchaseUseCase;
import com.web.novel.novel.chapter.port.out.ChapterPurchaseLogSavePort;
import com.web.novel.novel.port.out.NovelLoadPort;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@Service
public class ChapterPurchaseService implements ChapterPurchaseUseCase {

    private final MemberLoadPort memberLoadPort;
    private final NovelLoadPort novelLoadPort;
    private final UpdateMemberPointBalancePort updateMemberPointBalancePort;
    private final ChapterPurchaseLogSavePort chapterPurchaseLogSavePort;
    private final RedissonClient redissonClient;

    public ChapterPurchaseService(
            final MemberLoadPort memberLoadPort,
            final NovelLoadPort novelLoadPort,
            final UpdateMemberPointBalancePort updateMemberPointBalancePort,
            final ChapterPurchaseLogSavePort chapterPurchaseLogSavePort,
            final RedissonClient redissonClient) {
        this.memberLoadPort = memberLoadPort;
        this.novelLoadPort = novelLoadPort;
        this.updateMemberPointBalancePort = updateMemberPointBalancePort;
        this.chapterPurchaseLogSavePort = chapterPurchaseLogSavePort;
        this.redissonClient = redissonClient;
    }

    @Override
    public void command(Command command) {
        var memberId = command.getMemberId();
        var novelId = command.getNovelId();
        var chapterId = command.getChapterId();

        var member = memberLoadPort.getById(memberId.getValue());
        var novel = novelLoadPort.getById(novelId.getValue());

        var chapterPrice = novel.getChapterPriceInfo();
        var pointBalance = member.getPointBalance();

        var lock = redissonClient.getLock("chapter_purchase:");

        try {
            lock.tryLock(3,3, TimeUnit.SECONDS);

            if(! lock.isLocked()) {
                throw new RuntimeException();
            }

            var remainPoint = pointBalance.purchase(chapterPrice.getValue());
            updateMemberPointBalancePort.updateBalance(memberId.getValue(), remainPoint.getValue());
        } catch(InterruptedException ex) {
            log.error("{}", ex);
        } finally {
            lock.unlock();
        }

        var chapterPurchaseLong = new ChapterPurChaseLog(memberId, novelId, chapterId, chapterPrice);
        chapterPurchaseLogSavePort.save(chapterPurchaseLong);
    }
}
