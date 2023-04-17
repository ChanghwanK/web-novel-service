package com.web.novel.novel.chapter.service;

import com.web.novel.aop.annotation.DistributeLock;
import com.web.novel.member.Member.MemberId;
import com.web.novel.member.PointBalance;
import com.web.novel.member.port.out.MemberLoadPort;
import com.web.novel.member.port.out.UpdateMemberPointBalancePort;
import com.web.novel.novel.ChapterPriceInfo;
import com.web.novel.novel.chapter.ChapterPurChaseLog;
import com.web.novel.novel.chapter.port.in.ChapterPurchaseUseCase;
import com.web.novel.novel.chapter.port.out.ChapterPurchaseLogSavePort;
import com.web.novel.novel.port.out.NovelLoadPort;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@Service
public class ChapterPurchaseService implements ChapterPurchaseUseCase {

    private final static String LOCK_KEY = "chapter_purchase:";

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

        purchaseChapter(memberId, chapterPrice, pointBalance);

        var chapterPurchaseLong = new ChapterPurChaseLog(memberId, novelId, chapterId, chapterPrice);
        chapterPurchaseLogSavePort.save(chapterPurchaseLong);
    }

    @DistributeLock(key = LOCK_KEY)
    private void purchaseChapter(MemberId memberId, ChapterPriceInfo chapterPrice, PointBalance pointBalance) {
        var remainPoint = pointBalance.purchase(chapterPrice.getValue());
        updateMemberPointBalancePort.updateBalance(memberId.getValue(), remainPoint.getValue());
    }
}
