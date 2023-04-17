package com.web.novel.point.service;

import com.web.novel.member.port.out.MemberLoadPort;
import com.web.novel.member.port.out.UpdateMemberPointBalancePort;
import com.web.novel.point.Point;
import com.web.novel.point.port.in.PointChargeUseCase;
import com.web.novel.point.port.out.PointCacheCheckPort;
import com.web.novel.point.port.out.PointSavePort;
import java.util.concurrent.TimeUnit;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class PointChargeService implements PointChargeUseCase {

    private static final String LOCK_NAME = "web-point-point-lock";

    private final MemberLoadPort memberLoadPort;
    private final PointCacheCheckPort pointNumberCachePort;
    private final UpdateMemberPointBalancePort updateMemberPointBalancePort;
    private final PointSavePort pointSavePort;
    private final RedissonClient redissonClient;

    public PointChargeService(
            final MemberLoadPort memberLoadPort,
            final PointCacheCheckPort pointNumberCachePort,
            final UpdateMemberPointBalancePort updateMemberPointBalancePort,
            final PointSavePort pointSavePort,
            final RedissonClient redissonClient) {
        this.memberLoadPort = memberLoadPort;
        this.pointNumberCachePort = pointNumberCachePort;
        this.updateMemberPointBalancePort = updateMemberPointBalancePort;
        this.pointSavePort = pointSavePort;
        this.redissonClient = redissonClient;
    }

    @Override
    public void command(Command command) {
        var memberId = command.getMemberId();
        var chargeAmount = command.getChargeAmount();

        var member = memberLoadPort.getById(memberId.getValue());
        var initPoint = Point.createFromCommand(memberId, chargeAmount, command.getPayMethod());
        initPoint.createPointNumber();
        pointSave(initPoint);

        var pointBalance = member.pointCharge(chargeAmount);
        updateMemberPointBalancePort.updateBalance(memberId.getValue(), pointBalance.getValue());
    }

    private void pointSave(final Point initPoint) {
        var lock = redissonClient.getLock(LOCK_NAME);
        try {
            lock.tryLock(3,3, TimeUnit.SECONDS);

            if(! lock.isLocked()) {
                throw new RuntimeException();
            }

            pointNumberCachePort.checkDuplication(initPoint.getPointNumber());
            pointNumberCachePort.setPointNumber(initPoint.getPointNumber());
            pointSavePort.save(initPoint);
        } catch(InterruptedException ex) {
            System.out.println(ex.getMessage());
        } finally {
            lock.unlock();
        }
    }
}
